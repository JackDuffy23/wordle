import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class wordle{
	private static int count;
	private static String answer;

	
	public static void wordle(String a, int c) throws IOException {

		String currentGuess = a;
		
		//read file to see if word exists
		List<String> longList =new ArrayList<>();
		FileInputStream fstream = new FileInputStream("C:\\Users\\jackd\\Documents\\Eclipse Projects\\Wordle\\allWords");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  longList.add(strLine);
		}
        
		 

		//valid input?
		if(!longList.contains(currentGuess)) {
			if(currentGuess.length() != 5) {
				System.out.println(">> Error. Must be 5 letters.");
				return; 
		    }
			else{
				System.out.println(">> That's not a recognised word. Go again.");
				return;
			}
		}
		
		//contains correct letters?
		ArrayList<String> equalLetters = new ArrayList<String>();
		ArrayList<String> containsLetters = new ArrayList<String>();
		
	    
		for(int i=0; i<currentGuess.length(); i++) {
			String x = Character.toString(currentGuess.charAt(i));
			if(answer.contains(Character.toString(currentGuess.charAt(i)))) { //answer contains the letter
				if(Character.toString(answer.charAt(i)).equals(Character.toString(currentGuess.charAt(i)))) //in correct position
					equalLetters.add(x); //green letter
				else if(!containsLetters.contains(x))			
						containsLetters.add(x); //yellow letter
				}
			}
		
	

		//correct output
		if(answer.equalsIgnoreCase(currentGuess)) {
			System.out.println(">> Congratulations!! You got it!");
			System.exit(0);
		}
		else if(!answer.equalsIgnoreCase(currentGuess) && c==5) {
			System.out.println(">> --GAME OVER--");
			System.out.println(">> The word was: " + answer);
			System.exit(0);
		}
		
		else {
			if(equalLetters.size() > 0) {
			System.out.print("> Letter(s) in correct spot: ");
			for(int i=0; i<equalLetters.size(); i++) {
				System.out.print(equalLetters.get(i));
				}
					System.out.print("\n");
			}
			if(containsLetters.size() > 0) {
			System.out.print("> Contains letter(s): ");
			for(int i=0; i<containsLetters.size(); i++) {
				System.out.print(containsLetters.get(i));
				}
					System.out.print("\n");
			}
			if(containsLetters.size()==0 && equalLetters.size()==0 && longList.contains(currentGuess))
				System.out.print("Try again.");
			count++;				
		}
		//scanner.close();
	}

	
	private static String getWord() throws IOException {
		// choose random word from txt
		List<String> hiddenList =new ArrayList<>();
		FileInputStream fs1 = new FileInputStream("C:\\Users\\jackd\\Documents\\Eclipse Projects\\Wordle\\hiddenWords");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fs1));
		String strLine1;
		while ((strLine1 = br1.readLine()) != null)   {
			hiddenList.add(strLine1);
			}

		Random rand = new Random(System.currentTimeMillis());
	    String word = hiddenList.get(rand.nextInt(hiddenList.size()));
		return word;
	}

	

	public static void main(String args[]) throws IOException
    {
		answer = getWord();
		count = 0;
		
		Scanner in = new Scanner(System.in);
		
		while(count<7) {
			System.out.print("\nEnter guess (" + (6-count) + " remain): ");
	        String a = in.nextLine();
	        wordle(a, count);
		}

        in.close();
    }
}
