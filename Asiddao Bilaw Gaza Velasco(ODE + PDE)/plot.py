#This is just a hard-coded program for plotting the values from the SteadyState java program and TransientResponse java program

import numpy as np
import matplotlib.pyplot as plt
from scipy.interpolate import spline

#Raw data
x = np.array([0, 2.5, 5, 7.5, 10])

first = np.array([18.354040555484183, 1.6372649471204033, 0.14605168609482816, 0.013033952426555574, 0.0014283783481156794])
sec = np.array([31.43773745220938, 4.2113166114906955, 0.5011733362897381, 0.055910129023065754, 0.0061271374271852875])
third = np.array([46.39678319161433, 10.448016216511043, 2.0146281231724013, 0.35796798404776925, 0.06859266760196775])
fourth = np.array([59.5027640704612, 21.434986264602436, 6.6471975478371155, 1.9013738618400424, 0.5822388859223097])
fifth = np.array([68.50943588542668, 34.74900418619775, 15.712650474844235, 6.6254358452147954, 2.894388355588716])
#D = 2
steady_state = np.array([76.44011923, 52.47164868, 36.06102409, 25.05002437, 19.08573285 ])
#D = 4
steady_state1 = np.array([65.60036643288483, 47.63197427749892, 34.97957756153443, 26.72480999539061, 23.11334918520269])


#Make the lines smooth
xnew = np.linspace(x.min(), x.max(), 300)

first_smooth = spline(x, first, xnew)
sec_smooth = spline(x, sec, xnew)
third_smooth = spline(x, third, xnew)
fourth_smooth = spline(x, fourth, xnew)
fifth_smooth = spline(x, fifth, xnew)
steady_state_smooth = spline(x, steady_state, xnew)
steady_state1_smooth = spline(x, steady_state1, xnew)

#Plot figure 2
plt.plot(xnew, steady_state1_smooth, 'r-', label="D=4", linewidth=2)
plt.plot(xnew, steady_state_smooth, 'b-', label="D=2", linewidth=2)
plt.legend()
plt.axis([0,10, 0, 100])
plt.ylabel("Concentration(c)")
plt.xlabel("Distance(x)")
plt.show()

plt.plot(xnew, first_smooth, 'r-', xnew, sec_smooth, 'g-', xnew, third_smooth, 'b-', xnew, fourth_smooth, 'y-', xnew, fifth_smooth, 'k-', xnew, steady_state_smooth, 'c-', linewidth=2 )
plt.plot(xnew, first_smooth, 'g-', label="t=0.2", linewidth=2)
plt.plot(xnew, sec_smooth, 'b-', label="t=0.4", linewidth=2)
plt.plot(xnew, third_smooth, 'r-', label="t=0.8", linewidth=2)
plt.plot(xnew, fourth_smooth, 'k-', label="t=1.6", linewidth=2)
plt.plot(xnew, fifth_smooth, 'c-', label="t=3.2", linewidth=2)
plt.plot(xnew, steady_state_smooth, 'm-', label="t=steady state", linewidth=2)
plt.legend()
plt.axis([0,10, 0, 100])
plt.ylabel("Concentration(c)")
plt.xlabel("Distance(x)")
plt.show()