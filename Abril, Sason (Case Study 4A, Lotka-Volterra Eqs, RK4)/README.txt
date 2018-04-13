//	CS 131, AY 2017-2018
//	Machine Problem 2
//	An improvement of "Prob4GUI", as written/edited by Don Rodolfo Abril (THU) and Paul Matthew Sason (WFU-2)

These files comprise a program which we perceive to be an improvement of those found in the CS131 Prob4 directory.
It is a GUI based solver for Lotka-Volterra (Predator-Prey) models given user-defined values.

The graphical user interface was redesigned, but is still simple. You will find that you can change the values of
all variables in the Lotka-Volterra equations as found in CaseStudy4A.pdf, as in

x' = ax - bxy
y' = -cy + dxy

To achieve this, we had to edit the classes Sys2ODEsRK4, driverRK4, ODE1, ODE2, and scrapped Graph and Graph2
in lieu of Graphs, which rolls the former 'graph' classes into one class. The Prob4GUI program did not allow anyone
to change the prey growth and destruction rates and the predator death and increase rates, as those were imposed as
constants in the ODE1 and ODE2 classes. We simply added more class/function parameters so that now, you may play with
values for those variables as well.

No external libraries are needed to be able to compile and run the program (PredatorPrey). If on Windows, simply execute
the PredatorPrey.bat (batch) file. If on another computer that has a Unix-like OS, compile all of the classes with
the command "javac ODE1.java ODE2.java Sys2ODEsRK4.java driverRK4.java Graphs.java PredatorPrey.java", and then enter
"java PredatorPrey". Note that since the program halts everytime the graph display is closed, this program will be more
convenient for Windows users since the batch file has a loop that re-executes the program. We could not reimplement this
feature so that the program doesn't stop after displaying the graph because of time constraints (and given that it was
quite costly to refactor the code), so we hope that if you're reading this and planning improvements, you might be able
to implement that one, for the sake of future batches who might also use this (and maybe, your) program.

Upon the GUI being displayed, simply fill in the values as asked for by the program. You'll notice that most text fields
already have values, but fret not, for these are editable. We only set those values as default as they were the values
imposed in the tasks as seen in CaseStudy4A.pdf. Should you decide to remove this feature, simply erase from PredatorPrey.java
the .setText("<something>") functions for objects preyGroText, preyDestText, predDthText, predIncrText, tRange1Text, tRange2Text,
and intervalText.

Press the "Calculate (RK4)" button, wait for the computations to finish, and then press the "Show Graphs" button to
display the time series and phase plane solutions/graphs of the Predator-Prey model. And that's it, you're done!

P.S. Thank you for taking the time to read this, and for even considering to use our program.
