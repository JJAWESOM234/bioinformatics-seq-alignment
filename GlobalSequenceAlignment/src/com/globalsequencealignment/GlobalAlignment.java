package com.globalsequencealignment;

import java.util.Scanner;

public class GlobalAlignment
{

	private final double GAP_OPEN_PENALTY = 3.0;
	private final double GAP_EXTEND_PENALTY = 0.5;

	private char[] sequence1Char;
	private char[] sequence2Char;
	Scanner scan;
	private int[][] scoringMatrix;
	private int[][] sequenceMatrix;

	public GlobalAlignment()
	{
		this.scan = new Scanner(System.in);
		this.getSequences();
		this.setScoringMatrix();
		this.sequenceMatrix = this.fillSequenceMatrix();
	}

	protected void getSequences()
	{
		this.getSequence1();
		this.getSequence2();
	}

	protected void getSequence1()
	{
		System.out.print("Enter the first DNA seqeunce: ");
		this.sequence1Char = this.scan.next().toCharArray();
		this.validateSequence1();
	}

	protected void getSequence2()
	{
		System.out.print("Enter the second DNA seqeunce: ");
		this.sequence2Char = this.scan.next().toCharArray();
		this.validateSequence2();
	}

	protected int[][] fillSequenceMatrix()
	{
		int[][] seqMatrix = new int[this.sequence1Char.length + 1][this.sequence2Char.length + 1];

		for (int i = 1; i < this.sequence1Char.length; i++)
		{
			seqMatrix[i][0] = seqMatrix[i - 1][0] + (int) this.GAP_OPEN_PENALTY;
		}

		for (int i = 1; i < this.sequence2Char.length; i++)
		{
			seqMatrix[0][i] = seqMatrix[0][i - 1] + (int) this.GAP_OPEN_PENALTY;
		}

		for (int i = 1; i < this.sequence1Char.length; i++)
		{
			for (int j = 1; j < this.sequence2Char.length; j++)
			{
				seqMatrix[i][j] = (int) Math.max(
						Math.max(seqMatrix[i][j - 1] + this.GAP_OPEN_PENALTY,
								seqMatrix[i - 1][j] + this.GAP_OPEN_PENALTY),
						seqMatrix[i - 1][j - 1] + getValues(this.sequence1Char[i - 1], this.sequence2Char[j - 1]));
			}
		}

		return seqMatrix;
	}

	protected String[] getsequences()
	{
		String outputseq1 = "";
		String outputseq2 = "";
		int i = this.sequenceMatrix.length - 1;
		int j = this.sequenceMatrix[0].length - 1;
		while (i != 0 && j != 0)
		{
			if (this.sequenceMatrix[i - 1][j - 1] == this.sequenceMatrix[i][j]
					- getValues(this.sequence1Char[i - 1], this.sequence2Char[j - 1]))
			{
				outputseq1 = this.sequence1Char[i - 1] + outputseq1;
				outputseq2 = this.sequence2Char[j - 1] + outputseq2;
				i -= 1;
				j -= 1;
			} else if (this.sequenceMatrix[i][j - 1] == this.sequenceMatrix[i][j] - this.GAP_OPEN_PENALTY)
			{
				outputseq1 = "-" + outputseq1;
				outputseq2 = this.sequence2Char[j - 1] + outputseq2;
				j -= 1;
			} else
			{
				outputseq1 = this.sequence1Char[i - 1] + outputseq1;
				outputseq2 = "-" + outputseq2;
				i -= 1;
			}
		}
		if (i == 0)
		{
			for (int k = 0; k < j; k++)
			{
				outputseq1 = "-" + outputseq1;
				outputseq2 = this.sequence2Char[j - k] + outputseq2;
			}
		} else
		{
			for (int k = 0; k < i; k++)
			{
				outputseq1 = this.sequence1Char[i - k] + outputseq1;
				outputseq2 = "-" + outputseq2;
			}
		}

		return new String[]
		{ outputseq1, outputseq2 };
	}

	protected int getValues(char a, char b)
	{
		String nucleotideSeq = "CTAG";
		int index1 = nucleotideSeq.indexOf(a);
		int index2 = nucleotideSeq.indexOf(b);
		return this.scoringMatrix[index1][index2];
	}

	protected void validateSequence1()
	{
		for (int i = 0; i < this.sequence1Char.length; i++)
		{
			char tempChar = this.sequence1Char[i];
			if (tempChar != 'A' && tempChar != 'C' && tempChar != 'G' && tempChar != 'T')
			{
				System.out.println("Invalid Sequence Entered.\n");
				this.getSequence1();
			}
		}
	}

	protected void validateSequence2()
	{
		for (int i = 0; i < this.sequence2Char.length; i++)
		{
			char tempChar = this.sequence2Char[i];
			if (tempChar != 'A' && tempChar != 'C' && tempChar != 'G' && tempChar != 'T')
			{
				System.out.println("Invalid Sequence Entered.\n");
				this.getSequence2();
			}
		}
	}

	protected void setScoringMatrix()
	{
		int[][] tempMatrix =
		{
				{ 0, 0, 0, 0, 0 },
				{ 0, 2, 1, -1, -1 },
				{ 0, 1, 2, -1, -1 },
				{ 0, -1, -1, 2, 1 },
				{ 0, -1, -1, 1, 2 } };

		this.scoringMatrix = tempMatrix;
	}

	protected void printSequence()
	{
		for (int i = 0; i < this.sequence1Char.length; i++)
		{
			for (int j = 0; j < this.sequence2Char.length; j++)
			{
				System.out.printf("%5d", this.sequenceMatrix[i][j]);
			}
			System.out.println("");
		}
	}
}
