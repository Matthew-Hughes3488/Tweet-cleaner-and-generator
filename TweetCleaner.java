package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TweetCleaner {

	private static ArrayList<String> raw = new ArrayList<String>();
	private static ArrayList<String> cleaned = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		new TweetCleaner();

		System.out.println("Done.");
	}

	public TweetCleaner() throws IOException {

		loadRaw();

		clean();

		saveClean();
	}

	private void clean() {

		for (String line: raw) {

			String cln = clean(line);

			if (cln != null) {

				String[] toks = cln.split(" ");

				for (String s: toks) {
					addClean(s);			
				}	
			}
		}
	}

	public String clean(String input) {


		String[] tokens = input.split(" ");
		ArrayList<String> filteredTokens = new ArrayList<String>();
		ArrayList<String> validTokens = new ArrayList<String>();
		String cleanTweet = "";

		for (int i = 0; i < tokens.length; i ++) {

			if (basicFilter(tokens[i])) {

				filteredTokens.add(tokens[i]);
			}
		}

		for (int i = 0; i < filteredTokens.size(); i++) {

			String validToken = characterFilter(filteredTokens.get(i));

			if (!validToken.isEmpty()) {

				validTokens.add(validToken);
			}
		}

		for (int i = 0; i < validTokens.size(); i++) {

			cleanTweet = cleanTweet + validTokens.get(i) + " ";
		}

		if (cleanTweet.isEmpty()) {

			return null;
		}

		return cleanTweet;
	}
	// checks for @, #, digits, rt, RT, links, and lone ! and ?
	public boolean basicFilter(String input) {

		String bannedCharacters = ("@#0123456789");
		String [] tokens = input.split("");

		if (input.equalsIgnoreCase("rt") || input.contains("https") || input.equals("!") || input.equals("?")) {

			return false;
		}

		for (int i = 0; i < tokens.length; i++) {

			if (bannedCharacters.contains(tokens[i])) {

				return false;
			}
		}
		return true;
	}
// splits up input and rebuilds it with unvalid characters removed
	public String characterFilter(String input) {

		String validCharacters = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!?'‘’“”\"");
		String cleanToken = "";
		String [] tokens = input.split("");

		for (int i = 0; i < tokens.length; i++) {

			if (validCharacters.contains(tokens[i])) {
				cleanToken = cleanToken + tokens[i];
			}
		}
		return cleanToken;
	}

	private void addClean(String clean) {

		cleaned.add(clean);
	}

	private void saveClean() throws FileNotFoundException {

		PrintWriter pw = new PrintWriter("cleaned.txt");

		for (String s: cleaned) {
			pw.print(s + " ");
		}

		pw.close();

	}

	private void loadRaw() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(new File("donald.txt")));

		String line = "";

		while ((line = br.readLine())!= null) {

			raw.add(line);

		}

		br.close();
	}
}
