/**
 * 
 */


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
		// Create a new GlobalAlignment class instance to gain its functionality
		GlobalAlignment ga = new GlobalAlignment();
		
		// Print the sequence matrix to show scoring 
		ga.printSequence();
		
		// Get the optimally aligned sequences and print them
		String[] a = ga.getSequences();
		System.out.println(a[0]);
		//System.out.println(a[2]);
		System.out.println(a[1]);
	}

}
