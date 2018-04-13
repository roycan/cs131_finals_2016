import java.util.Scanner;

public class InputRK4 {
	private static Scanner scanner;
	//This function is for getting the inputs
	public static double[] inputs(){
		double[] inputs=new double[4];
		
		scanner = new Scanner(System.in);
		System.out.print("Enter values of x: ");
		inputs[0] = scanner.nextDouble();
		inputs[1] = scanner.nextDouble();
	
		System.out.print("Enter step size (h): ");
		inputs[2] = scanner.nextDouble();
		System.out.print("Enter initial value of y: ");
		inputs[3] = scanner.nextDouble();
		
		return inputs;
	}
}
