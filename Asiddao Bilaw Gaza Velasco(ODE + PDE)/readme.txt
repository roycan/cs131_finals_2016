To run SteadyState:

$javac main.java
$java main d u delta_x k c_in n

where d = dispersion coefficient
	u = flow rate / cross-sectional are
	delta_x = change in x
	k = first-order decay coefficient
	c_in = initial concentration
	n = number of splines

Example: java main 2 1 2.5 0.2 100 4

To run SteadyState:

$javac main.java
$java main d u delta_x k c_in n delta_t init_0 init_1 init_2 init_3

where d = dispersion coefficient
	u = flow rate / cross-sectional are
	delta_x = change in x
	k = first-order decay coefficient
	c_in = initial concentration
	n = number of splines
	delta_t = time
	init_n = initial values for the rhs

example: java main 2 1 2.5 0.2 100 4 0.2 0 0 0 0 0
		 java main 2 1 2.5 0.2 100 4 0.2 18.354040555484183 1.6372649471204033 0.14605168609482816 0.013033952426555574 0.0014283783481156794
		 java main 2 1 2.5 0.2 100 4 0.4 31.43773745220938 4.2113166114906955 0.5011733362897381 0.055910129023065754 0.0061271374271852875
		 java main 2 1 2.5 0.2 100 4 0.8 46.39678319161433 10.448016216511043 2.0146281231724013 0.35796798404776925 0.06859266760196775
		 java main 2 1 2.5 0.2 100 4 1.6 59.5027640704612 21.434986264602436 6.6471975478371155 1.9013738618400424 0.5822388859223097
