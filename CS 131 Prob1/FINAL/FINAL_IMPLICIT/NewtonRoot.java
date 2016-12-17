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
public class NewtonRoot {
    public static double NewtonRoot(Function Fun, Function FunDer, double Xest, double Err, int imax) throws ArithmeticException{
        /*
        NewtonRoot finds the root of Fun = 0 near the point Xest using Newton's Method.
        
        Input Variables:
        Fun - Object of class Function with a user-defined method that calculates Fun for a given x.
        FunDer - Object of class Function with a user-defined method that calculates the dericative of Fun for a given x.
        Xest - Initial estimate of the solution.
        Err - Maximum Error.
        imax - Maximum number of iterations.
        
        Return:
        The root of Fun, |Fun| < Err, if found in or under imax iterations.
        
        */
        double Xi;
        int i;
        for(i = 0; i < imax; i++){
            Xi = Xest - Fun.f(Xest) / FunDer.f(Xest);
            if(Math.abs((Xi - Xest) / Xest) < Err || Math.abs(Xi) < Err){
                return Xi;
            }
            Xest = Xi;
        }
        System.out.println("Solution was not obtained in " + Integer.toString(i) + "iterations.");
        throw new ArithmeticException("No answer");
    }
    
    //Overloaded convinience function for when Fun and FunDer is provided as a String expression for Fun(x), Fun'(x)
    public static double NewtonRoot(String Fun, String FunDer, double Xest, double Err, int imax) throws ArithmeticException{
        /*
        NewtonRoot finds the root of Fun = 0 near the point Xest using Newton's Method.
        
        Input Variables:
        Fun - Object of class Function with a user-defined method that calculates Fun for a given x.
        FunDer - Object of class Function with a user-defined method that calculates the dericative of Fun for a given x.
        Xest - Initial estimate of the solution.
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
        
        Argument xNew = new Argument("x");
        Argument yNew = new Argument("y = " + FunDer,x);
        Function fd = new Function(){
            Argument x_ = xNew;
            Argument y_ = yNew;
            @Override
            public double f(double vars) {
                x_.setArgumentValue(vars);
                return y_.getArgumentValue();
            }
        };
        
        double Xi;
        int i;
        for(i = 0; i < imax; i++){
            Xi = Xest - f.f(Xest) / fd.f(Xest);
            if(Math.abs((Xi - Xest) / Xest) < Err || Math.abs(Xi) < Err){
                return Xi;
            }
            Xest = Xi;
        }
        System.out.println("Solution was not obtained in " + Integer.toString(i) + "iterations.");
        throw new ArithmeticException("No answer");
    }
}
