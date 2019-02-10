import java.util.ArrayList;
import java.io.Reader;
import java.util.HashMap;

/**
 * DocumentStatistics analyzes a document and computes AverageWordLength,
 *  TypeTokenRation, HapaxLegomenaRation, AverageWordsPerSentence, 
 *  and SentenceComplexity.
 * @author Vani Mohindra
 * @version May 22, 2017
 */
public class DocumentStatistics
{
	Document document;
	double [] stats;
	
	/**
	 * Reads in the desired document and computes the statistics
	 * (AverageWordLength, TypeTokenRation, HapaxLegomenaRation, 
	 * AverageWordsPerSentence, and SentenceComplexity) of the document
	 * 
	 * @param reader: reads in the desired Document
	 */
	public DocumentStatistics (Reader reader)
	{
		document = new Document (new Scanner (reader));
		document.parseDocument();
		
		stats = new double[5];
		stats[0] = getAverageWordLength();
		stats[1] = getTypeTokenRation();
		stats[2] = getHapaxLegomanaRatio();
		stats[3] = getAverageWordsPerSentence();
		stats[4] = getSentenceComplexity();

		System.out.println( printStats() );
		
	}
	
	/**
	 * Creates a new DocumentStatistics with the following stats
	 * 
	 * @param info: the statistics for a particular Document
	 * should be formatted in the order:
	 * AverageWordLength, TypeTokenRation, HapaxLegomenaRation, 
	 * AverageWordsPerSentence, and SentenceComplexity
	 */
	public DocumentStatistics (double [] info)
	{
		stats = new double[info.length];
		for (int i=0; i<info.length; i++)
		{
			stats[i]=info[i];
		}
	}

	/**
	 * Returns the average word length in the document being
	 * analyzed
	 * 
	 * @return the average length of a word in the document
	 * being analyzed
	 */
	private double getAverageWordLength()
	{
		HashMap <String, Integer> info = document.getWords();
		if (info.size()==0) return 0.0;
		int characterCount = 0;
		int numWords = 0;
		for (String s: info.keySet())
		{
			int wc = info.get(s);
			characterCount += s.length()*wc;
			numWords += wc; 
		}
		return (double) characterCount/numWords;
	}
	
	/**
	 * Returns the average number of times each word appears in 
	 * the document being analyzed
	 * 
	 * @return the average number of times each word appears in
	 * the document being analyzed
	 */
	private double getTypeTokenRation()
	{
		HashMap <String, Integer> info = document.getWords();
		int total = 0;
		for (Integer ap: info.values()) total += ap;
		return (double) info.size()/total;
	}

	/**
	 * Return the number of words that appear only once in the 
	 * document being analyzed
	 * 
	 * @return the number of words that appear only once in the 
	 * document being analyzed
	 */
	private double getHapaxLegomanaRatio()
	{
		HashMap <String, Integer> info = document.getWords();		
		if (info.size()==0) return 0.0;
		int solo = 0;
		Object [] keys = info.keySet().toArray();
		for (Object s: keys)
		{
			if (info.get(String.valueOf(s))==1)
			{
				solo++;
			}
		}
		int total = 0;
		for (Integer ap: info.values()) total += ap;
		
		return (double)solo/total;
	}
	
	/**
	 * Returns the average number of words in a sentence
	 * in the document being analyzed
	 * 
	 * @return the average number of words in a sentence
	 * in the document being analyzed
	 */
	private double getAverageWordsPerSentence()
	{
		//number of sentences
		int numSentences = document.getSentences().size();
		if (numSentences ==0) return 0.0;
		
		// compute the total number of words in the document.
		int total = 0;
		for (Integer ap: document.getWords().values()) total += ap;
				
		return (double)total/numSentences;

	}
	
	/**
	 * Returns the average number of phrases in a sentence in the 
	 * document being analyzed
	 * 
	 * @return the average number of phrases in a sentence in the
	 * document being analyzed
	 */
	private double getSentenceComplexity()
	{
		//number of sentences
		int numSentences = document.getSentences().size();
		if (numSentences ==0) return 0.0;
		
		int phraseCnt = 0;
		for (Sentence s: document.getSentences())
		{
			phraseCnt += s.getPhraseCount();
		}	
				
		return (double)phraseCnt/numSentences;

	}
	
	/**
	 * Returns an array with the statistics for the document being analyzed
	 * 
	 * @return an array with the statistics for the document in
	 * the order:
	 * AverageWordLength, TypeTokenRation, HapaxLegomenaRation, 
	 * AverageWordsPerSentence, and SentenceComplexity
	 */
	public double [] getStats ()
	{
		double [] copy = new double[stats.length];
		for (int i=0; i<stats.length; i++)
		{
			copy[i] = stats[i];
		}
		return copy;
	}
	
	/**
	 * Returns a string with one statistic on each line
	 * 
	 * @return a String with one statistic on each line in the order:
	 * AverageWordLength, TypeTokenRation, HapaxLegomenaRation, 
	 * AverageWordsPerSentence, and SentenceComplexity
	 */
	public String printStats ()
	{
		return( "getAverageWordLength " + stats[0] + "\n"
		+ "getTypeTokenRatio " + stats[1] + "\n" 
		+ "getHapaxLegomanaRatio " + stats[2] + "\n"
		+ "getAverageWordsPerSentence " + stats[3] + "\n"
		+"getSentenceComplexity " + stats[4] + "\n");		
	}
}