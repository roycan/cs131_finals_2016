import java.util.Scanner;
import java.util.Arrays;

public class RungeKutta{

	public double ODE(double x, double y, String equation){ //accepts x, y, and equation
		String new_equation = equation.replaceAll("x", String.valueOf(x));
		String newer_equation = new_equation.replaceAll("y", String.valueOf(y));
		return eval(newer_equation);
	}
	
	public double ODE1(double t, double x, double y, String equation){ //accepts t, x, y, and equation
		String new_equation = equation.replaceAll("x", String.valueOf(x));
		String newer_equation = new_equation.replaceAll("y", String.valueOf(y));
		String newest_equation = newer_equation.replaceAll("t", String.valueOf(t));
		return eval(newest_equation);
	}
	
	public double ODE2(double t, double x, double y, String equation){ //accepts t, x, y, and equation
		String new_equation = equation.replaceAll("x", String.valueOf(x));
		String newer_equation = new_equation.replaceAll("y", String.valueOf(y));
		String newest_equation = newer_equation.replaceAll("t", String.valueOf(t));
		return eval(newest_equation);
	}
	
	public static double eval(final String str) { //evaluates string as mathematical expression
		//from http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
		return new Object() {
			int pos = -1, ch;

			void nextChar() {
				ch = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			boolean eat(int charToEat) {
				while (ch == ' ') nextChar();
				if (ch == charToEat) {
					nextChar();
					return true;
				}
				return false;
			}

			double parse() {
				nextChar();
				double x = parseExpression();
				if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
				return x;
			}

			double parseExpression() {
				double x = parseTerm();
				for (;;) {
					if      (eat('+')) x += parseTerm(); 
					else if (eat('-')) x -= parseTerm(); 
					else return x;
				}
			}

			double parseTerm() {
				double x = parseFactor();
				for (;;) {
					if      (eat('*')) x *= parseFactor(); 
					else if (eat('/')) x /= parseFactor(); 
					else return x;
				}
			}

			double parseFactor() {
				if (eat('+')) return parseFactor(); 
				if (eat('-')) return -parseFactor(); 

				double x;
				int startPos = this.pos;
				if (eat('(')) {
					x = parseExpression();
					eat(')');
				} else if ((ch >= '0' && ch <= '9') || ch == '.') { 
					while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
					x = Double.parseDouble(str.substring(startPos, this.pos));
				} else if (ch >= 'a' && ch <= 'z') { 
					while (ch >= 'a' && ch <= 'z') nextChar();
					String func = str.substring(startPos, this.pos);
					x = parseFactor();
					if (func.equals("sqrt")) x = Math.sqrt(x);
					else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
					else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
					else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
					else throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char)ch);
				}

				if (eat('^')) x = Math.pow(x, parseFactor()); 

				return x;
			}
		}.parse();
	}
	
	public static void odeRK4(int a, int b, double h, double yIni, String equation){ //ODE formula
		
		RungeKutta RK = new RungeKutta();
		Double z = (b-a)/ h;
		int n = z.intValue();
		double[] x = new double[n+1];
		double[] y = new double[n+1];
		x[0] = a;
		y[0] = yIni;
		
		for (int i = 0; i < n; i++){
			x[i+1] = x[i]+h;
			double K1 = RK.ODE(x[i], y[i], equation);
			double xhalf = x[i] + (h/2);
			double yK1 = y[i] + (K1*h/2);
			double K2 = RK.ODE(xhalf, yK1, equation);
			double yK2 = y[i] + (K2*h/2);
			double K3 = RK.ODE(xhalf, yK2, equation);
			double yK3 = y[i] + (K3*h);
			double K4 = RK.ODE(x[i+1], yK3, equation);
			y[i+1] = y[i] + (((K1 + (2*K2) + (2*K3) + K4)*h)/6);
		}
		
		System.out.println("x coordinates: " + Arrays.toString(x));
		System.out.println();
		System.out.println("y coordinates: " + Arrays.toString(y));

	}
	
	public static void Sys2ODEsRK4(int a, int b, double h, double xIni, double yIni, String equation1, String equation2){ //system of ODEs formula
		RungeKutta RK = new RungeKutta();
		Double z = (b-a)/ h;
		int n = z.intValue();
		double[] t = new double[n+1];
		double[] x = new double[n+1];
		double[] y = new double[n+1];
		t[0] = a;
		x[0] = xIni;
		y[0] = yIni;
		
		for (int i = 0; i < n; i++){
			t[i+1] = t[i] + h;
			double tm = t[i] + (h/2);
			double Kx1 = RK.ODE1(t[i], x[i], y[i], equation1);
			double Ky1 = RK.ODE2(t[i], x[i], y[i], equation2);
			double Kx2 = RK.ODE1(tm, x[i] + (Kx1*h/2), y[i] + (Ky1*h/2), equation1);
			double Ky2 = RK.ODE2(tm, x[i] + (Kx1*h/2), y[i] + (Ky1*h/2), equation2);
			double Kx3 = RK.ODE1(tm, x[i] + (Kx2*h/2), y[i] + (Ky2*h/2), equation1);
			double Ky3 = RK.ODE2(tm, x[i] + (Kx2*h/2), y[i] + (Ky2*h/2), equation2);
			double Kx4 = RK.ODE1(t[i+1], x[i] + (Kx3*h), y[i] + (Ky3*h), equation1);
			double Ky4 = RK.ODE2(t[i+1], x[i] + (Kx3*h), y[i] + (Ky3*h), equation2);
			x[i+1] = x[i] + (((Kx1 + (2*Kx2) + (2*Kx3) + Kx4)*h)/6);
			y[i+1] = y[i] + (((Ky1 + (2*Ky2) + (2*Ky3) + Ky4)*h)/6);
		
		}
		
		System.out.println("t coordinates: " + Arrays.toString(t));
		System.out.println();
		System.out.println("x coordinates: " + Arrays.toString(x));
		System.out.println();
		System.out.println("y coordinates: " + Arrays.toString(y));
	}
	
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("One ODE[1] or System of 2 ODEs[2] ?:");
		int choice = keyboard.nextInt();
		if(choice == 1){
			String equation = new String("");
			double yIni, h;
			int a, b;
						
			System.out.println("equation (dy/dx) (please do not put spaces):");
			equation = keyboard.next();
			
			System.out.println("a (first value of x):");
			a = keyboard.nextInt();
			
			System.out.println("b (last value of x):");
			b = keyboard.nextInt();
			
			System.out.println("h (step size):");
			h = keyboard.nextDouble();
			
			System.out.println("yIni (value of y at first point):");
			yIni = keyboard.nextDouble();
			
			odeRK4(a,b,h,yIni,equation);
		
		} else if (choice == 2){
			String equation1 = new String("");
			String equation2 = new String("");
			double xIni, yIni, h;
			int a, b, t;
						
			System.out.println("equation 1 (dx/dt) (please do not put spaces):");
			equation1 = keyboard.next();
			
			System.out.println("equation 2 (dy/dt) (please do not put spaces):");
			equation2 = keyboard.next();
			
			System.out.println("a (first value of t):");
			a = keyboard.nextInt();
			
			System.out.println("b (last value of t):");
			b = keyboard.nextInt();
			
			System.out.println("h (step size):");
			h = keyboard.nextDouble();
			
			System.out.println("xIni (value of x at first point):");
			xIni = keyboard.nextDouble();
			
			System.out.println("yIni (value of y at first point):");
			yIni = keyboard.nextDouble();
			
			Sys2ODEsRK4(a, b, h, xIni, yIni, equation1, equation2);
		} else {
			System.out.println("Option not in choices! Please run program again.");
			System.exit(1);
		}
		
	}

}