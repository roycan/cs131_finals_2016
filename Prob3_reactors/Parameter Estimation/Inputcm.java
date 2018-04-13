import java.util.Scanner;

public class Inputcm {

	private static Scanner scanner;

	public static String[] inputs(){
		scanner = new Scanner(System.in);
		String[] inputs = new String[2];

		System.out.print("Enter number of datapoints: ");
		inputs[0] = scanner.nextLine();
		System.out.println(inputs[0]);

		System.out.print("Enter measurements (time, value): ");
		inputs[1] = scanner.nextLine();

		return inputs;
	}
}
