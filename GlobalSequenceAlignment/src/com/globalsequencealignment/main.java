/**
 * 
 */
package com.globalsequencealignment;

/**
 * @author Jed
 *
 */
public class main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		GlobalAlignment ga = new GlobalAlignment();
		ga.printSequence();
		String[] a = ga.getSequences();
		System.out.println(a[0]);
		//System.out.println(a[2]);
		System.out.println(a[1]);
	}

}
