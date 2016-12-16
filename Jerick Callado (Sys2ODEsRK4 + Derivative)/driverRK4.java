
import java.io.FileWriter;
import java.io.IOException;

/*
-This is used to operate the Sys2ODEsRK4.java file
-place your function in ODE1.java in f method
-place your function in ODE2.java in f method
-Sys2ODEsRK4.f accepts int n, Double h, Double array x, Double array y and Double array t
*/

public class driverRK4{
    private static Double a, b, h, xinit, yinit;
    private static Double[] x, y, t;
        public void init(Double a, Double b, Double h, Double x, Double y){
            driverRK4.a = a;
            driverRK4.b = b;
            driverRK4.h = h;
            driverRK4.xinit = x;
            driverRK4.yinit = y;
            main();
        }
        
	public static void main(){
		//Scanner scan = new Scanner(System.in);
		
		/*System.out.print("Input first value of t: ");
		a = Double.parseDouble(scan.nextLine());
		System.out.print("Input last value of t: ");
		b = Double.parseDouble(scan.nextLine());
		System.out.print("Input size of increment of t: ");
		h = Double.parseDouble(scan.nextLine());
		*/
                double N = (b-a)/h;
		int n = (int) N;
		
		x = new Double[n+2];
		y = new Double[n+2];
		t = new Double[n+2];
		
		t[1] = a;
		t[n] = b;
		
		//System.out.print("Input initial value of x: ");
		x[1] = xinit;
		//System.out.print("Input initial value of y: ");
		y[1] = yinit;
		
		Sys2ODEsRK4.f(n, h, x, y, t);
	FileWriter pp = null;
	try{
		pp = new FileWriter("Sys2ODEsRK4.csv");

		pp.append("iteration");
		pp.append(',');
		pp.append("x value");
		pp.append(',');
		pp.append("y value");
		pp.append('\n');
		for (int i=1;i<=n+1;i++){
			pp.append(Double.toString(i));
			pp.append(',');
			pp.append(Double.toString(t[i]));
			pp.append(',');
			pp.append(Double.toString(x[i]));
			pp.append(',');
			pp.append(Double.toString(y[i]));
			pp.append('\n');
			System.out.print(i);
			System.out.print(" t" + i + ": " + t[i]);
			System.out.print(" x" + i + ": " + x[i]);
			System.out.print(" y" + i + ": " + y[i]);
			System.out.print("\n");
		}
	}catch(Exception e){
		e.printStackTrace();
	
	} finally {
		try{
			pp.flush();
			pp.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
    }
        public Double[] getX(){
            return driverRK4.x;
        }
        public Double[] getY(){
            return driverRK4.y;
        }
        public Double[] getT(){
            return driverRK4.t;
        }
}