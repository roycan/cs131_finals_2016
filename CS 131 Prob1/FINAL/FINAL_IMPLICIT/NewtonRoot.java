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
public class NewtonRoot
{
    public static double doNewtonRoot(Function func, Function funcDer, double solEst, double maxErr, int iterMax) throws ArithmeticException
    {
        /**
        *Finds the root of a given function func near the point solEst using Newton's Method.
        *@param func Object of class Function with a user-defined method that calculates func for a given x.
        *@param funcDer Object of class Function with a user-defined method that calculates the derivative of func for a given x.
        *@param solEst Initial estimate and eventually succeeding estimates of the solution.
        *@param maxErr Maximum error given by the user.
        *@param iterMax Maximum number of iterations to apply Newton Method.
        *@return currEst Current estimate of the root of the function if |func| < maxErr, if found in or under iterMax iterations.
        *@exception ArithmeticException Solution is not obtained in or under iterMax iterations.
        */
        
        double currEst;
        int i;
        for(i = 0; i < iterMax; i++){
            currEst = solEst - func.f(solEst) / funcDer.f(solEst);
            if(Math.abs((currEst - solEst) / solEst) < maxErr || Math.abs(currEst) < maxErr){
                return currEst;
            }
            solEst = currEst;
        }
        System.out.println("Solution was not obtained in " + Integer.toString(i) + "iterations.");
        throw new ArithmeticException("No answer");
    }
    
    public static double doNewtonRoot(String funcStr, String funcDerStr, double solEst, double maxErr, int iterMax) throws ArithmeticException
    {
        /**
        *Overloaded convenience function for when func and funcDer is provided as a String expression for Func(x), Func'(x)
        */
        
        Argument x = new Argument("x");
        Argument y = new Argument("y = " + funcStr,x);
        Function func = new Function(){
            Argument xFunc = x;
            Argument yFunc = y;
            @Override
            public double f(double vars) {
                xFunc.setArgumentValue(vars);
                return yFunc.getArgumentValue();
            }
        };
        
        Argument xDer = new Argument("x");
        Argument yDer = new Argument("y = " + funcDerStr,x);
        Function funcDer = new Function(){
            Argument xFuncDer = xDer;
            Argument yFuncDer = yDer;
            @Override
            public double f(double vars) {
                xFuncDer.setArgumentValue(vars);
                return yFuncDer.getArgumentValue();
            }
        };
        
        double currEst;
        int i;
        for(i = 0; i < iterMax; i++){
            currEst = solEst - func.f(solEst) / funcDer.f(solEst);
            if(Math.abs((currEst - solEst) / solEst) < maxErr || Math.abs(currEst) < maxErr){
                return currEst;
            }
            solEst = currEst;
        }
        System.out.println("Solution was not obtained in " + Integer.toString(i) + "iterations.");
        throw new ArithmeticException("No answer");
    }
}
