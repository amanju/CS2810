/*A.Manjunath,CS10B001,			Date:22/1/2012
 * This is a RandomString class to generate Random Strings of any length
 * Symbols used are 0-9 and a-z
 * */


import java.util.Random;

public class RandomString {
	private static char[] alphaNumeric = new char[36];      //Arrays of symbols
	private static Random rand = new Random();    // Random variable

	/*
	 * This constructor initializes symbols in alphanumeric
	 * */
	public RandomString() {
		for (int i = 0; i < 10; i++)
			alphaNumeric[i] = (char) ('0' + i);
		for(int i=10;i<36;i++)
			alphaNumeric[i]=(char)('a'+i-10);
	}

	/*
	 * This function generates random strings by using symbols in alphaNumeric array
	 * */	
	public String nextString(int length){
		if(length<1)
			throw new IllegalArgumentException("length must be greater then 1");

		StringBuilder randString = new StringBuilder(length);
		for(int i=0;i<length;i++) 
			randString.append(alphaNumeric[rand.nextInt(36)]);

		return randString.toString();
	}
}
