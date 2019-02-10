import java.util.ArrayList;
/**
 * A Phrase is a group of words. Phrases can be modified
 * to add new words 
 * @author Vani Mohindra
 * @version May 22, 2017
 *
 */

public class Phrase 
{
	private ArrayList<Token> tokens;
	
	/**
	 * Sets up a new Phrase 
	 */
	public Phrase ()
	{
		tokens = new ArrayList<Token>();
	}
	
	/**
	 * Adds a token to the phrase and returns true if the operation was successful
	 * @param t: the Token to add to this Phrase
	 * @return true if the Token was successfully added; false otherwise
	 */
	public boolean add (Token t)
	{
		if (t==null) return true;
		return tokens.add(t);
	}
	
	/**
	 * Creates a new Phrase such that it is equivalent to p
	 * @param p: the phrase that this Phrase should be intialized to equal
	 */
	private Phrase (Phrase p){
		this.tokens = new ArrayList<Token>();
		for (int i=0; i<p.tokens.size(); i++)
		{
			this.tokens.add(new Token(p.tokens.get(i).getType(), p.tokens.get(i).getValue()));
		}
	}
	
	/**
	 * Returns a copy of this Phrase
	 * @return a phrase that is the equivalent of this Phrase
	 */
	public Phrase clone(){
		return new Phrase(this);
	}
	
	/**
	 * Converts this Phrase to a String
	 * @return a String representation of this Phrase
	 */
	public String toString ()
	{
		String s = "";
		for (int i=0; i<tokens.size(); i++)
		{
			s+=(tokens.get(i) + " ");
		}
		return s;
	}
	
	/**
	 * Returns the number of words in this Phrase
	 * @return the number of words in this Phrase
	 */
	public int getWordCount(){
		
		return tokens.size();		
	}	
}