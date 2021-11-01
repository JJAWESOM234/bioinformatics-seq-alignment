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
		System.out.println(ga.getsequences()[0]);
		System.out.println(ga.getsequences()[1]);
	}

}
