/**
**	Caparoso, Patricia Ann G.
**	201318381
**	CS 131 THR
**	December 08, 2016
**
**	This program solves a first order ODE using RK4 method
**/

import java.util.List;
import java.util.ArrayList;

public class odeRK4
{
	public static void main(String[] args)
	{	
		//Get the inputs with the order x-values, h, y-value
		double[] inputs = InputRK4.inputs(); 
		
		double a = inputs[0], b = inputs[1], h = inputs[2], yini = inputs[3];
		
		double temp=0.0;
		if (b<a) {
			temp=b;
			b=a;
			a=temp;
		}
		double n = (b-a)/h;
		
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();

		x.add(a);
		y.add(yini);
		double xhalf, K1, K2, K3, K4, yK1, yK2, yK3;

		for (int i = 0; i < n; i++){
			x.add(x.get(i)+h);
			K1 = Function.ODE(x.get(i), y.get(i));
			xhalf = x.get(i) + (h/2);
			yK1 = y.get(i)+K1*h/2;
			K2 = Function.ODE(xhalf, yK1);
			yK2 = y.get(i)+K2*h/2;
			K3 = Function.ODE(xhalf, yK2);
			yK3 = y.get(i)+K3*h;
			K4 = Function.ODE(x.get(i+1), yK3);
			y.add(y.get(i) + ((K1 + (2*K2) + (2*K3) + K4) * h)/6);
		}

		System.out.println("x = "+ x);
		System.out.println("y = "+ y);
	}
}
