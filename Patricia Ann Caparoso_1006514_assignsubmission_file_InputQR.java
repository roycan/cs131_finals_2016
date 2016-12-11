import java.util.Scanner;

public class InputQR {

	private static Scanner scanner;
	
	public static String[] inputs(){
		scanner = new Scanner(System.in);
		String[] inputs = new String[2];
		
		System.out.print("Enter matrix dimension (n): ");
		inputs[0] = scanner.nextLine();
		System.out.println(inputs[0]);
		
		System.out.print("Enter matrix A [# #; # #]: ");
		inputs[1] = scanner.nextLine();
		
		return inputs;
	}
}
