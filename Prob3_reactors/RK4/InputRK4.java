import java.util.Scanner;

public class InputRK4 {
	private static Scanner scanner;
	//This function is for getting the inputs
	public static double[] inputs(){
		double[] inputs = new double[7];
		
		scanner = new Scanner(System.in);
		System.out.print("Enter initial value of t: ");
		inputs[0] = scanner.nextDouble();
		System.out.print("Enter step size (h): ");
		inputs[1] = scanner.nextDouble();
		System.out.print("Enter initial value of c1: ");
		inputs[2] = scanner.nextDouble();
		System.out.print("Enter initial value of c2: ");
		inputs[3] = scanner.nextDouble();
		System.out.print("Enter initial value of c3: ");
		inputs[4] = scanner.nextDouble();
		System.out.print("Enter initial value of c4: ");
		inputs[5] = scanner.nextDouble();
		System.out.print("Enter initial value of c5: ");
		inputs[6] = scanner.nextDouble();


		
		return inputs;
	}
}
