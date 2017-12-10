# CS 131 2017: MP2

This project aims to get the position of a swinging pendulum at some point in time using techniques for solving ODE's; namely, **Heun's**, **Euler's** and **Runge-Kutta (RK4)** methods.


## Getting Started

### Prerequisites

No external libraries are required to run the program. It is recommended to use Java 8 to avoid compatibility issues.

### Importing Files

The project contains five java files. `Heun.java, Euler.java, and RK4.java` contain the implementation of their respective techniques. `ODEFunction.java` contains the abstract class to be extended for the definition of your ODE function. `Main.java` contains a working example on how to run the program. You may choose to import only the specific class files on the techniques you will need so long as you also import the `ODEFunction.java` file. 

## Quick Start

1. Create a child class of the `ODEFunction class`.

```
class myODEFunction extends ODEFunction {
```

2. Override the `f( t, y )` method within your class.

```
@Override
public double[] f( double t, double[] y ) {
  double[] new_y = { 2*y^2 };   // f( t, y ) = 2y^2.
}
```

3. Instantiate your ODE class.

```
myODEFunction odefunction = new myODEFunction(); 
```

4. Call the abstract methods from the techniques you want to use.

```
double t0 = 0;    // Set initial t
double[] y0 = {1};  // Set initial vector y
double h = 0.1;   // Set step size h
int n = 4;        // Set number of iterations

Euler.odeSystem( t0, y0, h, n, odefunction );
Heun.odeSystem( t0, y0, h, n, odefunction );
RK4.odeSystem( t0, y0, h, n, odefunction );
```

### Methods

Below is a list of available methods that can be called from the classes.

```
// ODE Techniques:
Euler.odeSystem( t0, y0, h, n, odefunction );
Heun.odeSystem( t0, y0, h, n, odefunction );
RK4.odeSystem( t0, y0, h, n, odefunction );

// Vector operations:
ODEFunction.add( double[] vector1, double[] vector2 );  // returns the vector sum of vector1 and vector2
ODEFunction.add( double[][] vectors );                  // returns the sum of all vectors in the array
ODEFunction.mult( double[] vector, double c);           // returns the product of a vector and some constant c
```

### Arguments
```
t: initial value for t
y: vector containing initial conditions
h: step size
n: number of iterations
ode: your ODEFunction object
```

### Example
In this example, we implement the ODE techniques to get the position of a body that is initially at rest, while experiencing uniform linear acceleration of `5 m/s^2`.
First we define our y vector:
```
y = [ position , velocity ].
```
Getting the derivative of y, we get: dx/dt = v, dv/dt = a.
```
y' = [ velocity, acceleration ].
```
We use the initial conditions as our y vector:
```
y = [ 0, 0 ]  // Since object is initially at rest, velocity = 0.
```
Then use the y' vector for our ODE function:
```
public class linearMotionODE extends ODEFunction{
@Override
  public double[] f(double t, double[] y) 
  {
    double[] new_y = { y[1], 5 };   // From input y = [ x, v ], we return [ v, a ].
    return new_y;
  }
}
```
We then set our parameters, then run the commands:
```
    double t0 = 0;
    double y0 = [0,0];
    double h = 0.05;
    int n = 20;
    linearMotionODE odefunction = new linearMotionODE();
    
    Euler.odeSystem( t0, y0, h, n, odefunction );
    Heun.odeSystem( t0, y0, h, n, odefunction );
    RK4.odeSystem( t0, y0, h, n, odefunction );
```

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Group Members

* **Jiggs Cabiles**
* **Jenn Codia**
* **Joyce Anne Padilla**


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

