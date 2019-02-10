import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * A Document scans a document and parses it into sentences,
 * phrases, and words, while counting the number of each that
 * appears in the document
 * 
 * @author Vani Mohindra
 * @version May 22, 2017
 */
public class Document 
{
	// Document contains an arraylist of sentences.
	private ArrayList<Sentence> document;
	private Scanner scanner;
	// lookahead token
	private Token currentToken;
	// words hashmap stores all the words with their count 
	private HashMap <String, Integer> words;
	
	/**
	 * Constructs a new Document with a Scanner that 
	 * reads in input from the desired document
	 * 
	 * @param s: scans the desired document
	 */
	public Document (Scanner s)
	{
		scanner = s;
		document = new ArrayList<Sentence>();
		words = new HashMap <String, Integer>();
		currentToken = getNextToken();
	}
	
	/**
	 * If t is equal to the current Token, advance one Token ahead
	 * 
	 * @param t: the Token to check for equality with current Token
	 * @exception currentToken is not equal to t
	 */
	public void eat (Token t)
	{
		if (currentToken.equals(t))
		{
			getNextToken();
		}
	   	else
    		throw new IllegalArgumentException();		
	}
	
	/**
	 * Return a hashmap such that each key is a unique word in the 
	 * document and maps to an Integer vaue that represents the 
	 * number of times it appears in this Document
	 * @return the words hashmap.
	 */
	HashMap <String, Integer> getWords()
	{
		return words;
	}

	/**
	 * Returns the previous token and advances to the nextToken
	 * @return the previous token 
	 */
	private Token getNextToken()
	{
		currentToken = scanner.nextToken();
		return currentToken;
	}
	
	/**
	 * Adds the next Word to the Document 
	 */
	private void addWord()
	{
		// technically adds it to the hashmap
		if (currentToken.getType().equals(Scanner.TOKEN_TYPE.WORD))
		{
			if (words.containsKey(currentToken.toString()))
			{
				words.put(currentToken.toString(), words.get(currentToken.toString())+1);
			}
			else
			{
				words.put(currentToken.toString(),1);
			}
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Gets the next phrase. A phrase is a combination of word tokens. 
	 * It does not eat the phrase delimiter token.
	 *
	 * @param t
	 */
	public Phrase parsePhrase ()
	{
		Phrase phrase = new Phrase();
		while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE) &&
				!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE) &&
				!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE))
		{
			if (currentToken.getType().equals(Scanner.TOKEN_TYPE.WORD))
			{
				addWord();
				phrase.add(currentToken);				
			}
			eat(currentToken);
		}
		return phrase;
	}
	
	/**
	 * parse the sentence phrase by phrase.
	 * parseSentence doesn't eat the sentence delimiter.
	 * 
	 * @return
	 */
	public Sentence parseSentence ()
	{
		Sentence s = new Sentence();
	    
	    while ((currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE) &&
	    	   (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE))
	    {
	    	Phrase p = parsePhrase();
	    	
	    	if (p.getWordCount() != 0)
	    	{
	    		s.add(p);
	    	}
	    	// eat the phrase delimiter
	    	if (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE))
	    		eat(currentToken);
	    }
	    
	    if (s.getPhraseCount() > 0)
	    	return s;
	    else 
	    	return null;
	}

	/**
	 * parse the document sentence by sentence.
	 */
	public void parseDocument ()
	{
		Sentence s;
		
		while (currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE)
		{	
			s = parseSentence();
			if (s != null) document.add(s);
			if (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE))
				eat(currentToken);
		}
	}
	
	/**
	 * Return a copy of the ArrayList containing all the sentences in this Document
	 * @return a copy of the ArrayList containing all the sentences in this Document
	 */
	public ArrayList<Sentence> clone()
	{
		ArrayList<Sentence> copy = new ArrayList<Sentence>(document.size());
		for (Sentence s: document)
		{
			copy.add(s.clone());
		}
		return copy;
	}
	
	/**
	 * Returns the document
	 * @return the document
	 */
	public ArrayList<Sentence> getSentences(){
		return document;
	}
	
	/**
	 * @return a String representation of this document, leaving only one
	 * space between each pair of sentences
	 */
	public String toString(){
		String out = "";
		for (Sentence s: document)
		{
			out += (s + ". ");
		}
		return out;
	}
}