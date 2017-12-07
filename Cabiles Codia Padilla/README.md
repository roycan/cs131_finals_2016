# CS 131 2017: MP2

This project aims to get the position of a swinging pendulum at some point in time using techniques for solving ODE's; namely, **Heun's**, **Euler's** and **Runge-Kutta (RK4)** methods.


## Getting Started

### Prerequisites

No external libraries are required to run the program. It is recommended to use Java 8 to avoid compatibility issues.

### Importing Files

The project contains three core java files: `Heun.java, Euler.java, and ODEFunction.java`. You may choose to import only the specific class files on the techniques you will need so long as you also import the `ODEFunction.java` file. 

## Quick Start

1. Implement the `ODEFunction` interface using your own class.

```
class myODEFunction implements ODEFunction {
```

2. Override the `f( t, y )` method within your class.

```
@Override
public double f( double t, double y )
{
  return y * t;
}
```

3. Instantiate your ODE class.

```
myODEFunction odefunction = new myODEFunction(); 
```

4. Call the abstract methods from the techniques you want to use.

```
double t0 = 0; 
double y0 = 1; 
double h = 0.1; 
int n = 4;
Euler.odeEuler( t0, y0, h, n, odefunction );
Heun.odeHeun( t0, y0, h, n, odefunction );
```

### Methods

Below is a list of available methods for the techniques used.

```
Heun.odeHeun( t, y, h, n, ode );
Euler.odeEuler( t, y, h, n, ode );
```

### Arguments
```
t: initial value for t
y: initial value for y
h: step size
n: number of iterations
ode: your ODEFunction object
```

### Example
```
import com.mccabiles.cs131mp2.*;

// Define your ODEFunction class and override f( t, y ):
class myODE implements ODEFunction
{
  @Override
  public double f(double t, double y) 
  {
    return y * t;
  }
}
 
 
public class Main
{
  
  // Main function:
  public static void main(String[] args)
  {
 
    // Set the Initial Values here:
    double t0 = 0;
    double y0 = 1;
    double h = 0.1;
    int n = 4;
    
    // Instantiate your ODEFunction object:
    myODE function = new myODE();
    
    // Finally, call the methods:
    Euler.odeEuler(t0, y0, h, n, function);
    Heun.odeHeun(t0, y0, h, n, function);
    
  }
 
}
```

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Group Members

* **Jiggs Cabiles**
* **Jenn Codia**
* **Joyce Anne Padilla**


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

