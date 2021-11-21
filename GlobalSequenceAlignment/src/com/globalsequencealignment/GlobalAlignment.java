package com.globalsequencealignment;

import java.util.Scanner;

public class GlobalAlignment
{

	private final double GAP_OPEN_PENALTY = -10.0;
	private final double GAP_EXTEND_PENALTY = 0.5;

	private char[] sequence1Char;
	private char[] sequence2Char;
	Scanner scan;
	private int[][] scoringMatrix;
	private int[][] sequenceMatrix;

	public GlobalAlignment()
	{
		this.scan = new Scanner(System.in);
		this.getUserSequences();
		this.setScoringMatrix();
		this.sequenceMatrix = this.fillSequenceMatrix();
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

	protected int[][] fillSequenceMatrix()
	{
		int[][] seqMatrix = new int[this.sequence1Char.length + 1][this.sequence2Char.length + 1];

		for (int i = 0; i < seqMatrix.length; i++)
		{
			seqMatrix[i][0] = i * (int) this.GAP_OPEN_PENALTY;
		}

		for (int i = 0; i < seqMatrix[0].length; i++)
		{
			seqMatrix[0][i] = i * (int) this.GAP_OPEN_PENALTY;
		}

		for (int i = 1; i < seqMatrix.length; i++)
		{
			for (int j = 1; j < seqMatrix[0].length; j++)
			{
				int match = seqMatrix[i - 1][j - 1]
						+ this.getValues(this.sequence1Char[i - 1], this.sequence2Char[j - 1]);
				int delete = seqMatrix[i - 1][j] + (int) this.GAP_OPEN_PENALTY;
				int insert = seqMatrix[i][j - 1] + (int) this.GAP_OPEN_PENALTY;

				seqMatrix[i][j] = this.max(match, delete, insert);
			}
		}

		return seqMatrix;
	}

	protected String[] getSequences()
	{
		String returnSeq1 = "";
		String returnSeq2 = "";
		String returnMatchSymbol = "";
		int i = this.sequenceMatrix.length - 1;
		int j = this.sequenceMatrix[0].length - 1;
		while (i != 0 && j != 0)
		{
			if (this.sequenceMatrix[i - 1][j - 1] == this.sequenceMatrix[i][j]
					- getValues(this.sequence1Char[i - 1], this.sequence2Char[j - 1]))
			{
				returnSeq1 = this.sequence1Char[i - 1] + returnSeq1;
				// returnMatchSymbol += "|";
				returnSeq2 = this.sequence2Char[j - 1] + returnSeq2;
				i -= 1;
				j -= 1;
			}
			else if (this.sequenceMatrix[i][j - 1] == this.sequenceMatrix[i][j] - this.GAP_OPEN_PENALTY)
			{
				returnSeq1 = "-" + returnSeq1;
				// returnMatchSymbol += " ";
				returnSeq2 = this.sequence2Char[j - 1] + returnSeq2;
				j -= 1;
			}
			else
			{
				returnSeq1 = this.sequence1Char[i - 1] + returnSeq1;
				// returnMatchSymbol += " ";
				returnSeq2 = "-" + returnSeq2;
				i -= 1;
			}
		}
		if (i == 0)
		{
			for (int k = 0; k < j; k++)
			{
				returnSeq1 = "-" + returnSeq1;
				// returnMatchSymbol += " ";
				returnSeq2 = this.sequence2Char[j - k] + returnSeq2;
			}
		}
		else
		{
			for (int k = 0; k < i; k++)
			{
				returnSeq1 = this.sequence1Char[i - k] + returnSeq1;
				// returnMatchSymbol += " ";
				returnSeq2 = "-" + returnSeq2;
			}
		}

		return new String[]
		{ returnSeq1, returnSeq2, returnMatchSymbol };
	}

	protected int getValues(char a, char b)
	{
		String nucleotideSeq = "ATGCU";
		int index1 = nucleotideSeq.indexOf(a);
		int index2 = nucleotideSeq.indexOf(b);
		return this.scoringMatrix[index1][index2];
	}

	protected void validateSequence1()
	{
		for (int i = 0; i < this.sequence1Char.length; i++)
		{
			char tempChar = Character.toUpperCase(this.sequence1Char[i]);

			if (tempChar != 'A' && tempChar != 'C' && tempChar != 'G' && tempChar != 'T' && tempChar != 'U')
			{
				System.out.println("Invalid Sequence Entered.\n");
				this.getUserSequence1();
			}
			this.sequence1Char[i] = tempChar;
		}
	}

	protected void validateSequence2()
	{
		for (int i = 0; i < this.sequence2Char.length; i++)
		{
			char tempChar = Character.toUpperCase(this.sequence2Char[i]);
			if (tempChar != 'A' && tempChar != 'C' && tempChar != 'G' && tempChar != 'T' && tempChar != 'U')
			{
				System.out.println("Invalid Sequence Entered.\n");
				this.getUserSequence2();
			}
			this.sequence2Char[i] = tempChar;
		}
	}

	protected void setScoringMatrix()
	{
//		int[][] tempMatrix =
//		{
//				{ 0, 0, 0, 0, 0 },
//				{ 0, 2, 1, -1, -1 },
//				{ 0, 1, 2, -1, -1 },
//				{ 0, -1, -1, 2, 1 },
//				{ 0, -1, -1, 1, 2 } };
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
			System.out.printf("%10c", ' ');
			for (int i = 0; i < this.sequence2Char.length; i++)
			{
				System.out.printf("%5c", this.sequence2Char[i]);
			}
			System.out.println("");

			for (int i = 0; i < this.sequenceMatrix.length; i++)
			{
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
		System.out.printf("\nEstimated Score: %d\n",
				this.sequenceMatrix[this.sequenceMatrix.length - 1][this.sequenceMatrix[0].length - 1]);

	}

	private int max(int n1, int n2, int n3)
	{
		return Math.max(n1, Math.max(n2, n3));
	}
}
