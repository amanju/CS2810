import java.util.Scanner;
public class NearestPrime {
	
	/** 
	 * Check if a number is prime
	 * @param n
	 * @return
	 */
	public static boolean isPrime( int n )
	{
		for(int i=2;i<=Math.sqrt(n);i++){
			if(n%i==0)
			return false;
		}
		return true;
	}
	
	/**
	 * Search numbers near X and return nearest prime
	 * @param X
	 * @return
	 */
	public static int nearestPrime( int X )
	{
		int big,small;
		if(isPrime(X)) return X;
	
		for(big=X;big>=X;big++)
			if(isPrime(big)) break;
		
		for(small=X-1;small>=2;small--)
			if(isPrime(small)) break;
		
		if((X-small)>(big-X)) return big;
		else return small;
	}

	/**
	 * Accept an integer 'X' as input, and print the prime nearest to it.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int X = in.nextInt();
		System.out.println(nearestPrime(X));
	}
}
