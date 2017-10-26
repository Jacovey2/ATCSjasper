import java.util.Scanner;
import java.util.Arrays;
class Main{
  public static void main(String[] args){
  	Scanner scan = new Scanner(System.in);
  	int TRIES=6;
  	
  	//Read in answer
  	System.out.println("What is the answer?:");
  	char[] answer=scan.next().toCharArray();
  	char[] check= new char[answer.length];
  	
  	//Clear Board
  	for(int i=0; i<50; i++) System.out.println("");
  	//Make check all underscores
  	for(int i=0; i<check.length; i++) check[i]='_';
  	
  	//stage setup
  	int stage=1;
  	dispMan(stage);
  	
  	//Initial slots printout
  	System.out.print(" Word: ");
  	outputWithSpaces(check);
  	
  	while (stage<=TRIES && !Arrays.equals(answer, check)){
  		
  		//Input 
  		System.out.print("Please enter letter: ");
  		char[] charac=scan.next().toCharArray();
  		while (charac.length>1){
  			System.out.println("Invalid entry, please put only one character");
  			charac=scan.next().toCharArray();
  		}

  		//Modify Check String and check for repeats
  		if (lookForLetterInString(answer, charac[0], check)) stage++;
   		
  		//Display Man
  		dispMan(stage);
  		
  		//Output String
  		if (stage<=TRIES){
	  	  System.out.print(" Word: ");
	  		outputWithSpaces(check);
  		}
  	}
  	
  	//Check for win
  	if (stage<=TRIES){
  		System.out.println(" Y o u  W i n ! ");
  	}
  	
  	//Print answer if lost (the "You Lose" is inside dispMan)
  	else {System.out.print("The answer was: "); System.out.println(answer);}
  	
  }
  
  public static boolean lookForLetterInString(char[] array, char charac, char[] out_array){
  	boolean add=true; //if true, the charachter was not found, if false, the charachter was found or a repeat
  	for (int i=0; i<array.length; i++){
  		if(array[i]==charac) {
  			out_array[i]=array[i]; 
  			add=false;
  		}
  	}
  	return add;
  }
  
  public static void outputWithSpaces(char[] output){
	  for(int x=0; x<output.length; x++){
	  	System.out.print(output[x]);
	  	System.out.print(" ");
	  }
	  System.out.println("\n\n\n");
  }
  
  public static void dispMan(int stage){
  	if (stage==1){
  		System.out.println("  ___________");
  		System.out.println("  |      |   ");
  		System.out.println("  |          ");
  		System.out.println("  |          ");
  		System.out.println("  |          ");
  		System.out.println("  |          ");
  		System.out.println("__|__________\n");
  	}
  	if (stage==2){
  		System.out.println("  ___________");
  		System.out.println("  |      |   ");
  		System.out.println("  |      O   ");
  		System.out.println("  |          ");
  		System.out.println("  |          ");
  		System.out.println("  |          ");
  		System.out.println("__|__________\n");
  	}
  	if (stage==3){
  		System.out.println("  ___________");
  		System.out.println("  |      |   ");
  		System.out.println("  |      O   ");
  		System.out.println("  |      |   ");
  		System.out.println("  |      |   ");
  		System.out.println("  |          ");
  		System.out.println("__|__________\n");
  	}
  	if (stage==4){
  		System.out.println("  ___________");
  		System.out.println("  |      |   ");
  		System.out.println("  |      O   ");
  		System.out.println("  |     /|   ");
  		System.out.println("  |      |   ");
  		System.out.println("  |          ");
  		System.out.println("__|__________\n");
  	}
  	if (stage==5){
  		System.out.println("  ___________");
  		System.out.println("  |      |   ");
  		System.out.println("  |      O   ");
  		System.out.println("  |     /|\\  ");
  		System.out.println("  |      |   ");
  		System.out.println("  |          ");
  		System.out.println("__|__________\n");
  	}
  	if (stage==6){
  		System.out.println("  ___________");
  		System.out.println("  |      |   ");
  		System.out.println("  |      O   ");
  		System.out.println("  |     /|\\  ");
  		System.out.println("  |      |   ");
  		System.out.println("  |     /    ");
  		System.out.println("__|__________\n");
  	}
  	if (stage==7){
  		System.out.println("  ___________");
  		System.out.println("  |      |   ");
  		System.out.println("  |      O   ");
  		System.out.println("  |     /|\\  ");
  		System.out.println("  |      |   ");
  		System.out.println("  |     / \\  ");
  		System.out.println("__|__________\n");
  		System.out.println("Y o u  L o s e");
  	}
  }
}