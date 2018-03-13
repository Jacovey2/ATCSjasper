import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BinarySearch {
	static String DIRECTORY = "/Users/jacovey/Downloads/";
	static String FILENAME = "words";
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> words= new ArrayList<String>();
		File file = new File(DIRECTORY + FILENAME + ".txt");
		Scanner scan = new Scanner(file);
		int bufferMaxSize = 10;
		int bufferCounter = 0;
		while (scan.hasNext() && bufferCounter<bufferMaxSize) {
			String tempString = scan.nextLine();
			words.add(tempString);
			System.out.println(tempString + " ("+bufferCounter+")"); 
			bufferCounter++;
		}
		String target = "10-point";
		int foundIndex = BSearch(words,target);
		System.out.println(target +": words.get("+foundIndex + ") = \""+words.get(foundIndex)+"\"");
		System.out.println(words.get(0));
		scan.close();
	}
	public static int BSearch(ArrayList<String> stringList, String target) {
		ArrayList<String> sList = stringList;
		Collections.sort(sList, String.CASE_INSENSITIVE_ORDER);
		int topEnd=sList.size(); //top index of the new window
		int bottomEnd = 0;       //bottom index of the new window
		while (topEnd!=bottomEnd) {
			int avg = Math.round((topEnd + bottomEnd)/2);
			System.out.println("top: "+topEnd);
			System.out.println("bottom: "+ bottomEnd);
			System.out.println("avg: "+ avg +" ("+(topEnd-bottomEnd)+")\n\n");
			
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
		return topEnd;
	}
}
