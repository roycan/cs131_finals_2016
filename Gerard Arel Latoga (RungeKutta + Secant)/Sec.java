import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import org.mariuszgromada.math.mxparser.*;

public class Sec {
    private Function myEquation; //the equation, y
    private Argument myStartingX; //first value of x, a
    private Argument myLastX; //last value of x, b
    private Argument myStepSize; //step size, err
    private Argument myMaxIterations; //max iters, imax
    private double myAnswer;

    public Sec(String _eq, String _stX, String _lsX, String _sS, String _mI) {
            myEquation = new Function(_eq);
            myStartingX = new Argument(_stX);
            myLastX = new Argument(_lsX);
            myStepSize = new Argument(_sS);
            myMaxIterations = new Argument(_mI);
            calculateAnswer();
    }

    void calculateAnswer() {
        int currentIterations;
        double currentXValue;
        double valueAtRangeEnd;
        double rangeStart = myStartingX.getArgumentValue();
        double rangeEnd = myLastX.getArgumentValue();
        if (rangeStart <= 0 && 0 < rangeEnd) {
            rangeStart = 0;
        }

        if (myEquation.calculate(rangeStart) == 0
        || myEquation.calculate(rangeEnd) == 0) {
            myAnswer = 0;
        }

        else {
            for (currentIterations = 0;
            currentIterations < (int) myMaxIterations.getArgumentValue();
            currentIterations++){
                valueAtRangeEnd = myEquation.calculate(rangeEnd);
                currentXValue = rangeEnd - valueAtRangeEnd*(rangeStart-rangeEnd)
                                /(myEquation.calculate(rangeStart) - valueAtRangeEnd);
                System.out.println(myEquation.calculate(rangeStart));

                if (Math.abs((currentXValue - rangeEnd) / rangeEnd) < myStepSize.getArgumentValue()) {
                    myAnswer = currentXValue;
                    break;
                }

                rangeStart = rangeEnd;
                rangeEnd = currentXValue;

            }
            if (currentIterations == (int) myMaxIterations.getArgumentValue()) {
                myAnswer = Double.NaN;
            }
        }
    }

    double getRoot() {
        return myAnswer;
    }
}
