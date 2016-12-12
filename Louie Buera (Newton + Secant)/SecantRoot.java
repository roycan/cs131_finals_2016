/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rootFind;

import org.mariuszgromada.math.mxparser.Argument;

/**
 *
 * @author hp
 */
public class SecantRoot {
    public static double SecantRoot(Function Fun, double Xa, double Xb, double Err, int imax) throws ArithmeticException{
        /*
        SecantRoot finds the root of Fun = 0 near the point Xest using the Secant Method.
        
        Input Variables:
        Fun - Object of class Function with a user-defined method that calculates Fun for a given x.
        Xa, Xb - Two points in the neighborhood of the root (on either side, or the same side of the root).
        Err - Maximum Error.
        imax - Maximum number of iterations.
        
        Return:
        The root of Fun, |Fun| < Err, if found in or under imax iterations.
        
        */
        double FunXb, Xi;
        for(int i = 0; i < imax; i++){
            FunXb = Fun.f(Xb);
            Xi = Xb - FunXb * (Xa - Xb) / (Fun.f(Xa) - FunXb);
            if(Math.abs((Xi - Xb) / Xb) < Err || Math.abs(Xi) < Err){
                return Xi;
            }
            Xa = Xb;
            Xb = Xi;
        }
        System.out.println("Solution was not obtained in " + Integer.toString(imax) + "iterations.");
        throw new ArithmeticException("No answer");
    }
    
    //Overloaded convinience function for when Fun is provided as a String expression for Fun(x)
    public static double SecantRoot(String Fun, double Xa, double Xb, double Err, int imax) throws ArithmeticException{
        /*
        SecantRoot finds the root of Fun = 0 near the point Xest using the Secant Method.
        
        Input Variables:
        Fun - String for the expression of Fun
        Xa, Xb - Two points in the neighborhood of the root (on either side, or the same side of the root).
        Err - Maximum Error.
        imax - Maximum number of iterations.
        
        Return:
        The root of Fun, |Fun| < Err, if found in or under imax iterations.
        
        */
        
        Argument x = new Argument("x");
        Argument y = new Argument("y = " + Fun,x);
        Function f = new Function(){
            Argument x_ = x;
            Argument y_ = y;
            @Override
            public double f(double vars) {
                x_.setArgumentValue(vars);
                return y_.getArgumentValue();
            }
        };
        
        double FunXb, Xi;
        for(int i = 0; i < imax; i++){
            FunXb = f.f(Xb);
            Xi = Xb - FunXb * (Xa - Xb) / (f.f(Xa) - FunXb);
            if(Math.abs((Xi - Xb) / Xb) < Err || Math.abs(Xi) < Err){
                return Xi;
            }
            Xa = Xb;
            Xb = Xi;
        }
        System.out.println("Solution was not obtained in " + Integer.toString(imax) + "iterations.");
        throw new ArithmeticException("No answer");
    }
}
