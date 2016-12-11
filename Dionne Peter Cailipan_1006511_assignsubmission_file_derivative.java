/*
This class is almost direct translation of the octave script odeModEuler.
Please make sure x and y are arrays containing the x and y coordinates
of the data points, respectively.
*/

class derivative {
    float dx[];

    derivative(float[] x, float[] y) {
        int n = x.length;
        dx = new float[n];
        // Forward FDF
        dx[0] = (y[1] - y[0]) / (x[1] - x[0]);

        // Central FDF
        for (int i = 1; i < n-1; i++) {
            dx[i] = (y[i+1] - y[i-1]) / (x[i+1] - x[i-1]);
        }

        // Backward FDF
        dx[n-1] = (y[n-1] - y[n-2]) / (x[n-1] - x[n-2]);
    }
}
