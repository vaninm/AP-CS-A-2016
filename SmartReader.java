import java.util.HashMap;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 * A SmartReader stores the data of known authors and then
 * analyzes documents to predict their authors
 * @author Vani Mohindra
 * @version May 22, 2017
 */

public class SmartReader 
{
	
	private HashMap <String, DocumentStatistics> storage;
	// name of author and author's info
	
	/**
	 * Constructs a new SmartReader that knows the statistics of all the
	 * given authors
	 * @throws FileNotFoundException the known files cannot be found
	 */
	public SmartReader () throws FileNotFoundException
	{
		storage = new HashMap <String, DocumentStatistics>();
		File f = new File("/Users/vanimo/Downloads/FindAuthorMaterial 2/SignatureFiles");
        // returns pathnames for files and directory
        File [] paths = f.listFiles();
        for (File path:paths) 
        {
			if (path.isHidden()) continue;
        	FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner scanner = new Scanner(bufferedReader);
            String author = scanner.nextLine();
            double [] info = new double [5];
            for (int i=0; i<5; i++)
            {
            	info[i] = Double.parseDouble(scanner.nextLine());
            }
            DocumentStatistics authorStats = new DocumentStatistics(info);
            storage.put(author, authorStats);
            System.out.println(author + "\n" + authorStats.printStats());
        }
	}

	/**
	 * 
	 * @param scanner: the scanner to read the Document being analyzed
	 * @return the estimate for the name of the author 
	 */
	public String findAuthor (Reader scanner)
	{
		 DocumentStatistics authorInfo = new DocumentStatistics (scanner);
		 double min = Integer.MAX_VALUE;
		 String author = "?";
		 Set<String> keys = storage.keySet();
		 for (String s: keys) 
		 {
			 DocumentStatistics other = storage.get(s);
			 double wordLength = Math.abs(other.getStats()[0]-authorInfo.getStats()[0]);
			 double typeToken = Math.abs(other.getStats()[1]-authorInfo.getStats()[1]);
			 double hapaxLeg = Math.abs(other.getStats()[2]-authorInfo.getStats()[2]);
			 double sentenceLength = Math.abs(other.getStats()[3]-authorInfo.getStats()[3]);
			 double sentenceComplexity = Math.abs(other.getStats()[4]-authorInfo.getStats()[4]);
			 double score = wordLength*11+typeToken*33+hapaxLeg*50+sentenceLength*0.4+sentenceComplexity*4;
			 if (score < min)
			 {
				 author = s;
				 min = score;
			 }
		 }
		 return author;
		 
		 /**
		  * Return a non-negative real number indicating the similarity of two 
    linguistic signatures. The smaller the number the more similar the 
    signatures. Zero indicates identical signatures.
    sig1 and sig2 are 6 element lists with the following elements
    0  : author name (a string)
    1  : average word length (float)
    2  : TTR (float)
    3  : Hapax Legomana Ratio (float)
    4  : average sentence length (float)
    5  : average sentence complexity (float)
    weight is a list of multiplicative weights to apply to each
    linguistic feature. weight[0] is ignored.

weights = [0, 11, 33, 50, 0.4, 4] 
		  */	 
	}
	
	/**
	 * Creates a new SmartReader and predicts the names of the
	 * authors of the MysterDocuments
	 * 
	 * @throws FileNotFoundException: the directory with the Mystery Texts 
	 * cannot be found
	 */
	public static void main (String [] args) throws FileNotFoundException
	{
		SmartReader scholar = new SmartReader();
		File f = new File("/Users/vanimo/Downloads/FindAuthorMaterial 2/MysteryText");
		File [] paths = f.listFiles();
		ArrayList<String> authors = new ArrayList<String> ();
		for (File file: paths)
		{
			if (file.isHidden()) continue;
			System.out.println("File " + file.getName());
			FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
          //  Scanner scanner = new Scanner(bufferedReader);            
            authors.add(scholar.findAuthor(bufferedReader));
		}
		for (String s: authors)
		{
			System.out.println(s);
		}
	}
}