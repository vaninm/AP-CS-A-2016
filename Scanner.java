import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the single quote character "'", or the hyphen character "-". 
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 * @author Mr. Page
 * @author Vani Mohindra
 * @version 5 February 2017
 *
 */
public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;
    // define symbolic constants for each type of token
    public static enum TOKEN_TYPE{WORD, END_OF_SENTENCE, END_OF_FILE, 
        END_OF_PHRASE, DIGIT, UNKNOWN};
    
    // performance optimizations. Use string buffer to reduce string creation. Use static Token for Unknown as no one uses it.    
    private StringBuffer buf;
    private final static int INITIAL_SB_SIZE = 64;
    public final static Token Unknown_Token =  new Token(TOKEN_TYPE.UNKNOWN, "unknown");
    
    /**
     * Constructor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an InputStream
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program constructing
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
        buf = new StringBuffer(INITIAL_SB_SIZE);
    }
    /**
     * The getNextChar method attempts to get the next character from the input
     * stream.  It sets the endOfFile flag true if the end of file is reached on
     * the input stream.  Otherwise, it reads the next character from the stream
     * and converts it to a Java String object.
     * postcondition: The input stream is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String 
     * representation of the character read from the input stream.  The flag
     * endOfFile is set true if the input stream is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if(inp == -1) 
                endOfFile = true;
            else 
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * Compares currentChar against the inputted string. If they are equal, eat
     * advances the input stream by one character; otherwise, an IllegalArgumentException
     * is thrown
     * @param s: the string to compare currentChar to 
     * @throws IllegalArgumentException if the inputted string is not equal to the current character
     */
    private void eat (String s)
    {
    	if (currentChar.equals(s)) 
    	{
    		getNextChar();
    	}
    	else
    		throw new IllegalArgumentException();
    }
    
    /**
     * @precondition the given string has length 1- that is, it consists of only one character
     * Determines whether or not the given string is a letter
     * @param s: the string being checked to see if it is a letter
     * @return true if the given string is a letter; false otherwise
     */
    private boolean isLetter (String s)
    {
    	return (s.substring(0).compareTo("A")>=0 && s.substring(0).compareTo("Z")<=0 ||
    			s.substring(0).compareTo("a")>=0 && s.substring(0).compareTo("z")<=0);
    }
    
    /**
     * @precondition the given string has length 1- that is, it consists of only one character
     * Determines whether or not the given string is a digit
     * @param s: the string being checked to see if it is a digit
     * @return true if the given string is a digit; false otherwise
     */
    private boolean isDigit (String s)
    {
    	return (s.compareTo("0")>0 && s.compareTo("9")<0);
    }
    
    /**
     * @precondition the given string has length 1- that is, it consists of only one character
     * Determines whether or not the given string is a special character (a hyphen or a single quote)
     * @param s: the string being checked to see if it is a special character (a hyphen or a single quote)
     * @return true if the given string is a special character; false otherwise
     */
    private boolean isSpecialChar (String s)
    {
    	return (s.equals("-") || s.equals("'"));
    }
    
    /**
     * @precondition the given string has length 1- that is, it consists of only one character
     * Determines whether or not the given string is a phrase terminator, where a phrase terminator
     * is a colon, semicolon, or comma
     * @param s: the string being checked to see if it is a phrase terminator (colon, semicolon, or comma)
     * @return true if the given string is a phrase terminator; false otherwise
     */
    private boolean isPhraseTerminator (String s)
    {
    	return (s.equals(",") || s.equals(";") || s.equals(":"));
    }
   
    /**
     * @precondition the given string has length 1- that is, it consists of only one character
     * Determines whether or not the given string is a sentence terminator- that is, a period,
     * exclamation mark, or question mark
     * @param s: the string being checked to see if it is a sentence terminator (period, exclamation mark, or question mark)
     * @return true if the given string is a sentence terminator; false otherwise
     */
    private boolean isSentenceTerminator (String s)
    {
    	return (s.equals("!") || s.equals(".") || s.equals("?"));
    }
    
    /**
     * @precondition the given string has length 1- that is, it consists of only one character
     * Determines whether or not the given string is whitespace
     * @param s: the string being checked to see if it is a whitespace
     * @return true if the given string is whitespace; false otherwise
     */
    private boolean isWhiteSpace (String s)
    {
    	return (s.equals(" ") || s.equals("\t") || s.equals("\n")|| s.equals("\r"));
    }
    
    /**
     * Determines whether or not the Scanner has another token to read
     * @return true, if there is another Token for the Scanner to read; false otherwise
     */
    public boolean hasNextToken ()
    {
    	return !endOfFile;
    }
    
    /**
     * Returns the next token that the scanner can read
     * @return the next token
     */
    public Token nextToken()
    {
    	if (!hasNextToken())
    	{
    		return new Token(TOKEN_TYPE.END_OF_FILE, null);
    	}
    	if (isDigit(currentChar))
    	{
    		Token t = new Token(TOKEN_TYPE.DIGIT, currentChar);
    		eat(currentChar);
    		return t;
    	}
    	if (isPhraseTerminator(currentChar))
    	{
    		Token t = new Token(TOKEN_TYPE.END_OF_PHRASE, currentChar);
    		eat(currentChar);
    		return t;
    	}
    	if (isSentenceTerminator(currentChar))
    	{
    		Token t = new Token(TOKEN_TYPE.END_OF_SENTENCE, currentChar);
    		eat(currentChar);
    		return t;
    	}
/*    	if (isWhiteSpace(currentChar) || isSpecialChar(currentChar))
    	{
    		eat(currentChar);
    		return null;
    	}
*/    	if (isLetter(currentChar))
    	{
			buf.setLength(0);
    		buf.append(currentChar);
    		eat(currentChar);
    		while (hasNextToken() && (isLetter(currentChar) || isSpecialChar(currentChar) || isDigit(currentChar)))
        	{
        		buf.append(currentChar.toLowerCase());
        		eat(currentChar);
        	}
    			
    		return new Token(TOKEN_TYPE.WORD, buf.toString());
    	}

		// Dont generate and send the Unknow token with valid data as callers are not using it.
    	// Token t =  new Token(TOKEN_TYPE.UNKNOWN, currentChar);
		Token t = Unknown_Token;
		eat(currentChar);
    	return t;
    }
}