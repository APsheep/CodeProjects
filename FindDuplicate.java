/*************************************************************************
 *  Compilation:  javac FindDuplicate.java
 *  Execution:    java FindDuplicate
 *
 *  @author: Akshata Padalkar, akshata.padalkar@rutgers.edu, anp178
 *
 * FindDuplicate that reads n integer arguments from the command line 
 * into an integer array of length n, where each value is between is 1 and n, 
 * and displays true if there are any duplicate values, false otherwise.
 *
 *  % java FindDuplicate 10 8 5 4 1 3 6 7 9
 *  false
 *
 *  % java FindDuplicate 4 5 2 1 2
 *  true
 *************************************************************************/

public class FindDuplicate {

    public static void main(String[] args) {
	/*	autolab errors
	boolean c = false;
		int n = args.length;
		int[] entries = new int[n];

	for (int i = 0; i < entries.length; i++) {
        entries[i] = Integer.parseInt(args[i]);
	
	for (int z = 0; z < entries.length; z++) {
 	  	for (int j = z+1; j < args.length; j++) { 
	
	   			if(i != j){
					 if (entries[i] == entries[j]) {
					 //System.out.println("true"); 
					 c = true;
					 return;
						 }
					   }
	
					}
				}
				
			}	
			//System.out.println("false");
			System.out.println(c); */



		int[] entries = new int[args.length];
		boolean c = false;
	for (int i = 0; i < entries.length; i++) {
        entries[i] = Integer.parseInt(args[i]);
	}
	for (int i = 0; i < entries.length; i++) {
 	  	for (int j = i+1; j < entries.length; j++) { 
	
	   			if(i != j){
					 if (entries[i] == entries[j]) {
					
					 c = true;
					
						 }
					   }
	
					}
			}	
			
			System.out.println(c);
	}
}			