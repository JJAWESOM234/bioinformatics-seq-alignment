package com.globalsequencealignment;

import java.util.Scanner;

public class GlobalAlignment
{

	private final double GAP_OPEN_PENALTY = 10.0;
	private final double GAP_EXTEND_PENALTY = 0.5;

	private char[] sequence1Char;
	private char[] sequence2Char;
	Scanner scan;

	public GlobalAlignment()
	{
		this.scan = new Scanner(System.in);
		this.getSequences();
		this.validateSequences();
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

	protected void validateSequences()
	{
		this.validateSequence1();
		this.validateSequence2();
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
}
