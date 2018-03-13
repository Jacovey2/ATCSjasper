import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BinarySearch {
	static String DIRECTORY = "/Users/jacovey/Downloads/";
	static String FILENAME = "words";
	static String TARGET = "";
	static String SENTENCE = "hello my name is jasper";//sentence to spell check
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> words= new ArrayList<String>();
		File file = new File(DIRECTORY + FILENAME + ".txt");
		Scanner scan = new Scanner(file);
		while (scan.hasNext()) {// loading all the words in
			words.add(scan.nextLine());
		}
		Collections.sort(words, String.CASE_INSENSITIVE_ORDER);//must sort so comparator matches list
		String[] splitSentence = SENTENCE.split(" ");
		boolean[] results = new boolean[splitSentence.length];
		for (int i=0; i<splitSentence.length; i++) {
			int foundIndex = BSearch(words,splitSentence[i]);//search for the index
			results[i]=false;
			if (foundIndex >= 0) {
				System.out.println("     words.get("+foundIndex + ") = \""+words.get(foundIndex)+"\"");//printing result
				results[i]=true;
			}	
		}
		System.out.println("\n\nOriginal: "+SENTENCE);
		System.out.print("Results:");
		for (int i =0; i<results.length;i++) {//printing results, √ is correct, X is incorrect
			if (results[i])
				System.out.print(" √ ");
			else
				System.out.print(" X ");
		}
		scan.close();
	}
	
	public static int BSearch(ArrayList<String> stringList, String target) {
		ArrayList<String> sList = stringList; //list
		Collections.sort(sList, String.CASE_INSENSITIVE_ORDER); //resorting (just in case)
		int topEnd=sList.size(); //top index of the new window
		int bottomEnd = 0;       //bottom index of the new window
		while (topEnd!=bottomEnd && (topEnd-bottomEnd)>1) {
			int avg = Math.round((topEnd + bottomEnd)/2);
			System.out.println("top: "+topEnd);
			System.out.println("bottom: "+ bottomEnd);
			System.out.println("avg: "+ avg +"    difference: "+(topEnd-bottomEnd)+"\n\n");
			if (sList.get(avg).compareToIgnoreCase(target) > 0) {
				topEnd = avg;
			}
			else if (sList.get(avg).compareToIgnoreCase(target) < 0) {
				bottomEnd = avg;
			}
			else if (sList.get(avg).compareToIgnoreCase(target) == 0){
				return avg;
			}
		}
		return -1;
	}
}
