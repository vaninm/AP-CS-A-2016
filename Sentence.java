import java.util.ArrayList;
/**
 * A Sentence consists of a group of phrases, which in turn, consist
 * of groups of words. 
 * 
 * @author Vani Mohindra
 * @version May 22, 2017
 */

public class Sentence 
{
	private ArrayList<Phrase> phrases;
	
	/**
	 * Sets up a new sentence
	 */
	public Sentence ()
	{
		// initialize ArrayList of phrases
		phrases = new ArrayList<Phrase>();
	}

	/**
	 * Initializes phrases so that this Sentence is equal to s
	 * @param s: the sentence that this should be
	 */
	private Sentence (Sentence s){
		this.phrases = new ArrayList<Phrase>();
		for (int i=0; i<s.phrases.size(); i++)
		{
			this.phrases.add(s.phrases.get(i).clone());
		}
	}
	
	/**
	 * 
	 * @param p: the phrase to add to the sentence
	 * @return true if the phrase was added; false if it was not
	 */
	public boolean add (Phrase p)
	{
		return phrases.add(p);
	}
	
	/**
	 * Creates and returns a copy of this Snetence
	 * @return a copy of this Sentence
	 */
	public Sentence clone()
	{
		Sentence s = new Sentence(this);
		
		return s;
	}
	
	/**
	 * converts this Sentence to a String and returns the String
	 * @return the String version of this Sentence if the group of 
	 * phrases that constitute this Sentence separated by a space
	 */
	public String toString ()
	{
		String s = "";
		for (int i=0; i<phrases.size(); i++)
		{
			s+=phrases.get(i);
		}
		return s;
	}
	
	/**
	 * Returns the number of phrases in this Sentence
	 * @return the number of phrases in this Sentence
	 */
	public int getPhraseCount()
	{
		return phrases.size();
	}
	
	/**
	 * Returns the number or words in this Sentence
	 * @return the number of words in this Sentence
	 */
	public int getWordCount(){
		
		int n = 0;
		for (int i=0; i<phrases.size(); i++)
		{
			n+=phrases.get(i).getWordCount();
		}
		return n;		
	}
}
