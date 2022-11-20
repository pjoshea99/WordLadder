package practiceProblems;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class WordLadder 
{
	private boolean inDictionary = false; //true if word is in dictionary
	private boolean first = true; //true if first word in program, used to print the start word
	private String lastWord = "a"; //used to keep track of previous words, to prevent words from being repeated in word ladder
	private String secondToLast = null; //used to keep track of previous words, to prevent words from being repeated in word ladder
	
	/**
	 * Converts a given string into an array
	 * @param word
	 * @return xArray
	 */
	private char[] convertToArray(String word)
	{
		int xLength = word.length();
		char[] xArray = new char [xLength];
		for(int i = 0; i < xLength; i++)
		{
			xArray[i] = word.charAt(i);
		}
		return xArray;
	}
	
	/**
	 * Checks to see if provided string is in the dictionary contained in the file
	 * @param word
	 * @return inDictionary
	 * @throws FileNotFoundException
	 */
	private boolean checkDictionary(String word)
	{
		inDictionary = false;
		Scanner inputStream = null;
		try 
		{
			inputStream = new Scanner(new File("C:\\Users\\pjosh\\OneDrive\\Documents\\CSC 201\\words.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("There is something wrong with this file");
		}
		while(inputStream.hasNextLine())
		{
			String line = inputStream.nextLine();
			if(line.equalsIgnoreCase(word) && !lastWord.equalsIgnoreCase(line) && !secondToLast.equalsIgnoreCase(line))
			{
				inDictionary = true;
			}
		}	
		return inDictionary;
	}
	
	/**
	 * Takes a start and end word of equal character length and changes one letter at a time to attempt to generate a ladder of real words to the end word.
	 * Method used recursively at end of first iteration until original final word is achieved or no ladder found.
	 * @param start
	 * @param end
	 * @return start or end depending on location in word ladder. If no ladder found prints error message.
	 */
	public String findWord(String start, String end)
	{
		if (first == true)
		{
			System.out.println(start);
			first = false;
		}
		if(start.equalsIgnoreCase(end))
		{
			return end;
		}	
		else
		{
			secondToLast = lastWord; //ensure repeated words don't get accepted in dictionary
			lastWord = start;
			char [] sArray = this.convertToArray(start);
			char [] eArray = this.convertToArray(end);
			if(sArray.length != eArray.length)
			{
				System.out.println("Please enter words of the same length");
				System.exit(0);
			}
			for(int i = 0; i < sArray.length || i < eArray.length; i++ )
			{
				if(sArray[i] != eArray[i])
				{
					sArray[i] = eArray[i];
					String lastReplacement = start; //ensures repeated words don't get printed to console.
					start = String.valueOf(sArray);
					this.checkDictionary(start);
					char replacementChar = 'a';
					while(inDictionary == false && replacementChar <= 'z')
					{
						sArray[i] = replacementChar;
						start = String.valueOf(sArray);
						this.checkDictionary(start);
						replacementChar++;
					}
					if(inDictionary == false)
					{
						System.out.println("No word ladder.");
						System.exit(0);
					}
					
					if(inDictionary == true && !start.equalsIgnoreCase(lastReplacement))
					{
						System.out.println(start);
					}
				}	
			}
			this.findWord(start, end);
			return start;
		}	
	}	
	
	
	
	public static void main(String [] args)
	{
		WordLadder yeet = new WordLadder();	
		yeet.findWord("class", "using");
	}
		
}
