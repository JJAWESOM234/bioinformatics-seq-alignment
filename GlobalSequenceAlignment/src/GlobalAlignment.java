import java.util.Scanner;

public class GlobalAlignment
{
	// Initilize gap penalties
	private final int GAP_OPEN_PENALTY = -10;
	private final int GAP_EXTEND_PENALTY = -1;

	// Initilize user input variables
	private char[] sequence1Char;
	private char[] sequence2Char;

	// Global Scanner for user input
	Scanner scan;

	// Matrices to track scoring of matches or mismatch and the overall score of
	// each alignment
	private int[][] scoringMatrix;
	private int[][] sequenceMatrix;

	// Matrices to track the gap penalties on the x and y axis.
	int[][] horizontalGapMatrix;
	int[][] verticalGapMatrix;

	// Public constructor
	public GlobalAlignment()
	{
		this.scan = new Scanner(System.in);

		// Prompts the user for DNA/RNA sequence
		this.getUserSequences();

		// Sets the scoring matrix to a 2 match and -1 mismatch
		this.setScoringMatrix();

		// Store the alignment matrix in the global variable for analyzation
		this.fillSequenceMatrix();
	}

	protected void getUserSequences()
	{
		this.getUserSequence1();
		this.getUserSequence2();
	}

	protected void getUserSequence1()
	{
		System.out.print("Enter the first DNA sequence: ");
		this.sequence1Char = this.scan.next().toCharArray();
		this.validateSequence1();
	}

	protected void getUserSequence2()
	{
		System.out.print("Enter the second DNA sequence: ");
		this.sequence2Char = this.scan.next().toCharArray();
		this.validateSequence2();
	}

	private void fillInitialSequenceMatrix()
	{
		// Initialize global matrices to proper length and height
		this.sequenceMatrix = new int[this.sequence1Char.length + 1][this.sequence2Char.length + 1];
		this.horizontalGapMatrix = new int[this.sequence1Char.length + 1][this.sequence2Char.length + 1];
		this.verticalGapMatrix = new int[this.sequence1Char.length + 1][this.sequence2Char.length + 1];

		// Loop through matrices to initialize the first column of each matrix
		for (int i = 1; i < this.sequenceMatrix.length; i++)
		{
			// Main scoring matrix for match/mismatch, gap penalties always applied per i
			this.sequenceMatrix[i][0] = (this.GAP_OPEN_PENALTY + (i * this.GAP_EXTEND_PENALTY));

			// Stores the score for adding a horizontal gap to the alignment
			this.horizontalGapMatrix[i][0] = ((this.GAP_OPEN_PENALTY + (this.GAP_EXTEND_PENALTY * i)));

			// Initializes the entire first column to "negative infinity"
			this.verticalGapMatrix[i][0] = Integer.MIN_VALUE;

		}

		// Loop through matrices to initialize the first row of each matrix
		for (int i = 1; i < this.sequenceMatrix[0].length; i++)
		{
			// Main scoring matrix for match/mismatch, gap penalties always applied per i
			this.sequenceMatrix[0][i] = (this.GAP_OPEN_PENALTY + (i * this.GAP_EXTEND_PENALTY));

			// Initializes the entire first row to "negative infinity"
			this.horizontalGapMatrix[0][i] = Integer.MIN_VALUE;

			// Stores the score for adding a vertical gap to the alignment
			this.verticalGapMatrix[0][i] = ((this.GAP_OPEN_PENALTY + (this.GAP_EXTEND_PENALTY * i)));
		}

	}

	protected void fillSequenceMatrix()
	{
		this.fillInitialSequenceMatrix();

		// Loop through the entire matrix, storing the individual char to a temporary
		// variable.
		for (int i = 1; i < this.sequenceMatrix.length; i++)
		{
			var aChar = this.sequence1Char[i - 1];
			for (int j = 1; j < this.sequenceMatrix[0].length; j++)
			{
				var bChar = this.sequence2Char[j - 1];
				// Set the current cells value to the maximum of the three computed scores
				this.sequenceMatrix[i][j] = this.affineRecursion(aChar, bChar, i, j);
			}
		}

	}

	private int affineRecursion(char aChar, char bChar, int i, int j)
	{
		// Match or mismatch value based on char
		int matchValue = this.getValues(aChar, bChar);

		// Find the maximum score for horizontal gaps
		this.horizontalGapMatrix[i][j] = this.hortMax(i, j, bChar);
		// Find the maximum score for vertical gaps
		this.verticalGapMatrix[i][j] = this.vertMax(i, j, aChar);

		// Returns the maximum value from direct match/mismatch, insertion, or deletion.
		return this.max(this.horizontalGapMatrix[i][j], this.sequenceMatrix[i - 1][j - 1] + matchValue,
				this.verticalGapMatrix[i][j]);
	}

	private int vertMax(int i, int j, char aChar)
	{
		// Maximum score from adding a gap or having a direct match/mismatch
		return Math.max(this.verticalGapMatrix[i - 1][j] + this.GAP_EXTEND_PENALTY,
				this.sequenceMatrix[i - 1][j] + (this.GAP_OPEN_PENALTY + this.GAP_EXTEND_PENALTY));
	}

	private int hortMax(int i, int j, char bChar)
	{
		// Maximum score from adding a gap or having a direct match/mismatch
		return Math.max(this.horizontalGapMatrix[i][j - 1] + this.GAP_EXTEND_PENALTY,
				this.sequenceMatrix[i][j - 1] + (this.GAP_OPEN_PENALTY + this.GAP_EXTEND_PENALTY));
	}

	protected String[] getSequences()
	{
		// Initialize return sequences and index variables
		String returnSeq1 = "";
		String returnSeq2 = "";
		int i = this.sequenceMatrix.length - 1;
		int j = this.sequenceMatrix[0].length - 1;

		// loop through the sequence matrix to find the optimal path (represents the
		// alignment)
		while (i != 0 && j != 0)
		{
			// Diagonal case, where both resides are kept (match/mismatch)
			if (this.sequenceMatrix[i - 1][j - 1] == this.sequenceMatrix[i][j]
					- getValues(this.sequence1Char[i - 1], this.sequence2Char[j - 1]))
			{
				returnSeq1 = this.sequence1Char[i - 1] + returnSeq1;
				returnSeq2 = this.sequence2Char[j - 1] + returnSeq2;
				i -= 1;
				j -= 1;
			}
			// Left case, where the 2nd sequence residue remains and a gap is inserted into
			// the 1st sequence position
			else if (this.sequenceMatrix[i][j - 1] == this.sequenceMatrix[i][j] - this.GAP_OPEN_PENALTY)
			{
				returnSeq1 = "-" + returnSeq1;
				returnSeq2 = this.sequence2Char[j - 1] + returnSeq2;
				j -= 1;
			}
			// Top case, where the 1st sequence residue remains and a gap is inserted into
			// the 2nd sequence position
			else
			{
				returnSeq1 = this.sequence1Char[i - 1] + returnSeq1;
				returnSeq2 = "-" + returnSeq2;
				i -= 1;
			}
		}

		// If one of the sequences is out of residue before the other this fills in the
		// rest of the sequence with gaps
		if (i == 0)
		{
			for (int k = 0; k < j; k++)
			{
				returnSeq1 = "-" + returnSeq1;
				returnSeq2 = this.sequence2Char[j - k - 1] + returnSeq2;
			}
		}
		else
		{
			for (int k = 0; k < i; k++)
			{
				returnSeq1 = this.sequence1Char[i - k] + returnSeq1;
				returnSeq2 = "-" + returnSeq2;
			}
		}

		return new String[]
		{ returnSeq1, returnSeq2 };
	}

	protected int getValues(char a, char b)
	{
		// String to show the possible nucleotides
		String nucleotideSeq = "ATGCU";

		// Getting the index which corresponds with the row/column of the scoring matrix
		int index1 = nucleotideSeq.indexOf(a);
		int index2 = nucleotideSeq.indexOf(b);
		return this.scoringMatrix[index1][index2];
	}

	protected void validateSequence1()
	{
		// Loops through the users first sequence to restrict users input
		for (int i = 0; i < this.sequence1Char.length; i++)
		{
			// Normalizing the capitalization
			char tempChar = Character.toUpperCase(this.sequence1Char[i]);

			// Check to see if the character is in the valid list
			if (tempChar != 'A' && tempChar != 'C' && tempChar != 'G' && tempChar != 'T' && tempChar != 'U')
			{
				// Make the user retry the input if validations fail
				System.out.println("Invalid Sequence Entered.\n");
				this.getUserSequence1();
			}
			this.sequence1Char[i] = tempChar;
		}
	}

	protected void validateSequence2()
	{
		// Loops through the users first sequence to restrict users input
		for (int i = 0; i < this.sequence2Char.length; i++)
		{
			// Normalizing the capitalization
			char tempChar = Character.toUpperCase(this.sequence2Char[i]);

			// Check to see if the character is in the valid list
			if (tempChar != 'A' && tempChar != 'C' && tempChar != 'G' && tempChar != 'T' && tempChar != 'U')
			{
				// Make the user retry the input if validations fail
				System.out.println("Invalid Sequence Entered.\n");
				this.getUserSequence2();
			}
			this.sequence2Char[i] = tempChar;
		}
	}

	protected void setScoringMatrix()
	{
		// This is the scoring matrix used for the program
		// ATGCU

		int[][] tempMatrix =
		{
				{ 5, -4, -4, -4, -4 },
				{ -4, 5, -4, -4, -4 },
				{ -4, -4, 5, -4, -4 },
				{ -4, -4, -4, 5, -4 },
				{ -4, -4, -4, -4, 5 } };

		this.scoringMatrix = tempMatrix;
	}

	protected void printSequence()
	{
		if (this.sequence1Char.length <= 20 && this.sequence2Char.length <= 20)
		{
			// Spacing use
			System.out.printf("%10c", ' ');

			// Loop through the 2nd sequence to show the residue with each column
			for (int i = 0; i < this.sequence2Char.length; i++)
			{
				System.out.printf("%5c", this.sequence2Char[i]);
			}
			// Spacing use
			System.out.println("");

			// Loop through the sequence matrix and print the scores
			for (int i = 0; i < this.sequenceMatrix.length; i++)
			{
				// if/else prints the 1st sequence to show the residue with each row
				if (i != 0)
				{
					System.out.printf("%5c", this.sequence1Char[--i]);
					i++;
				}
				else
				{
					System.out.printf("%5c", ' ');
				}

				for (int j = 0; j < this.sequenceMatrix[i].length; j++)
				{
					System.out.printf("%5d", this.sequenceMatrix[i][j]);
				}
				System.out.println("");
			}
		}
		// Print the score (bottom right cell value)
		System.out.printf("\nEstimated Score: %d\n",
				this.sequenceMatrix[this.sequenceMatrix.length - 1][this.sequenceMatrix[0].length - 1]);
	}

	private int max(int n1, int n2, int n3)
	{
		// Find the maximum and return it
		return Math.max(n1, Math.max(n2, n3));
	}

}
