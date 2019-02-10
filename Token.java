 /**
 * A Token contains both a string value and a type to describe the aforementioned string. Token compares methods
 * to access its information and to compare Tokens. 
 * @author Vani Mohindra
 * @version 5 February 2017
 */
public class Token 
{
	private Scanner.TOKEN_TYPE type;
	private String val;
	
	/**
	 * Constructs a Token object
	 * @param t: describes the type of s 
	 * @param s: the value stored in the token 
	 */
	public Token (Scanner.TOKEN_TYPE t, String s)
	{
		type = t;
		val = s;
	}
	
	/**
	 * Returns the string stored in the token 
	 * @return the string stored in the token 
	 */
	public String getValue ()
	{
		return val;
	}
	
	/**
	 * Returns the type of string stored in this token
	 * @return the type of string stored in this token
	 */
	public Scanner.TOKEN_TYPE getType ()
	{
		return type;
	}
	
	/**
	 * Assists in testing the Scanner and Token classes by providing a string representation
	 * of the type that describes the string stored in this token
	 * @return a string representation of the type that describes the string stored in this token
	 */
	public String getTypeString ()
	{
		if (type.equals(Scanner.TOKEN_TYPE.WORD)) return "word";
		if (type.equals(Scanner.TOKEN_TYPE.DIGIT)) return "digit";
		if (type.equals(Scanner.TOKEN_TYPE.END_OF_FILE)) return "end of file";
		if (type.equals(Scanner.TOKEN_TYPE.END_OF_PHRASE)) return "end of phrase";
		if (type.equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE)) return "end of sentence";
		return "unknown";
	}
	
	/**
	 * @return a string representation of the token- that is, the string stored in this token
	 */
	public String toString ()
	{
		return val;
	}
	
	/**
	 * Compares two Tokens and determines whether or not they are equivalent
	 * @return true if the two objects have the same type and value; false otherwise
	 */
	public boolean equals (Object obj)
	{
		Token t = (Token) obj;
		return this.getValue().equals(t.getValue());
	}
	
	/**
	 * Returns the hashcode of the Token object
	 * @return the hashcode of the Token object
	 */
	public int hashCode ()
	{
		/**int charSum = 0;
		char [] b = val.toCharArray();
		for (int i=0; i<b.length; i++)
		{
			charSum += b[i]*Math.pow(7, i);
		}
		return charSum;*/
		return val.hashCode();
	}
}