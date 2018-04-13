
public class Function {	

	public static double ODE1(double t, double c1, double c2, double c3, double c4, double c5){
		double dc1_dt = (-0.24*c1) + (0.04*c3) + 2;
		return dc1_dt;
	}

	public static double ODE2(double t, double c1, double c2, double c3, double c4, double c5){
		double dc2_dt = (0.15*c1) - (0.15*c2);
		return dc2_dt;
	}

	public static double ODE3(double t, double c1, double c2, double c3, double c4, double c5){
		double dc3_dt = (0.025*c2) - (0.225*c3) + 4;
		return dc3_dt;
	}

	public static double ODE4(double t, double c1, double c2, double c3, double c4, double c5){
		double dc4_dt = (0.0125*c2) + (0.1*c3) - (0.1375*c4) + (0.025*c5);
		return dc4_dt;
	}

	public static double ODE5(double t, double c1, double c2, double c3, double c4, double c5){
		double dc5_dt = (0.03*c1) + (0.01*c2) - (0.04*c5);
		return dc5_dt;
	}	
}


