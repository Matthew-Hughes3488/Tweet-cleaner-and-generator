import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TweetGenerator {
    
    private static final int TWEET_LENGTH = 30;
    private static ArrayList<Word> words;
    private static Random random = new Random();
    
    public static void main(String[] args) throws IOException {
    	new TweetGenerator();
        System.out.println("Done.");
    }
    
    public TweetGenerator() throws IOException {
        ArrayList<String> cleaned = loadData();
        words = findWords(cleaned);
        System.out.println(createTweet(TWEET_LENGTH));
    }
    
    private ArrayList<String> loadData() throws IOException {  
        ArrayList<String> data = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(new File("cleaned.txt")));
        String line = "";
        
        while ((line = br.readLine())!= null) {
            String[] tokens = line.split(" ");
            for (String t: tokens) {
                data.add(t);
            }
        }
        br.close();
        return data;
    }
    
    public String createTweet(int numWords) {
    	String[] tweet = new String[numWords];
    	String returnText = "";
    	int r = random.nextInt(words.size());
    	int k = 0;
    	boolean foundIndex = false;
    	tweet[0] = words.get(r).getWord();
    	
    	
    	for (int i = 0; i < tweet.length; i++) {
    		tweet[i] = words.get(r).getRandomFollower();
    		while (!foundIndex && k < words.size()) {
    			if(words.get(k).getWord().equals(tweet[i])) 
    				r = k;
    			k++;	
    		}
    	}
    	
    	for (int i = 0; i < tweet.length; i++) 
    		returnText = returnText + tweet[i] + " ";
    	
    	
        return returnText;
    }
    
    private Word getWord(String word) {
        for (Word w: words) {
            if (w.getWord().equalsIgnoreCase(word))
                return w;
            
        }
        return null;
    }
    
    public ArrayList<Word> findWords(ArrayList<String> cleanedWords) {
    	ArrayList<Word> foundWords = new ArrayList<Word>();
    	boolean exists = false;

    	for (int i = 0; i < cleanedWords.size(); i++) {
    		exists = false;
    		for (int k = 0; k < foundWords.size(); k++) {
    			if (cleanedWords.get(i).equalsIgnoreCase(foundWords.get(k).getWord())) 
    				exists = true;
    			
    		}
    		if (!exists) 
    			foundWords.add(new Word(cleanedWords.get(i)));
    		
    	}
    	// this adds the number of times each word appears
    	for (int i = 0; i < foundWords.size(); i++ ) {
    		for (int k =0; k < cleanedWords.size(); k++) {
    			if (foundWords.get(i).getWord().equalsIgnoreCase(cleanedWords.get(k))) 
    				foundWords.get(i).incrementFrequency();	
    		}
    	}
    	// this adds the followers of each Word
    	for (int i = 0; i < foundWords.size(); i++) {
    		String word = foundWords.get(i).getWord();
    		
    		for (int k = 0; k < cleanedWords.size(); k++) {
    			if (word.equalsIgnoreCase(cleanedWords.get(k))) {
    				if (k != cleanedWords.size()-1) 
    					foundWords.get(i).addFollower(cleanedWords.get(k + 1));
    			}
    		}
    	}
        return foundWords;
    }    
}
