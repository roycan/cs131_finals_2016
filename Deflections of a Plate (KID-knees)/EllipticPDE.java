//Constraint: assumes delta x is equal to delta y

public class EllipticPDE {
    public static Node[] Node_setup(float lx, float ly, float h) {
        int column = (int) (lx/h) - 1;
        int row = (int) (ly/h) - 1;
        int length = column * row;
        int j = 1, k = 1;

        Node[] u = new Node[length];

        for(int i=0; i<length; i++) {
            u[i] = new Node(j, k);
            k++;
            if(k>column) {
                k = 1;
                j++;
            }
        }

        return u;
    }

    public static double[][] A(Node[] u) {
        double[][] An = new double[u.length][u.length];

        //Setup A
        for(int i=0; i<u.length; i++) {
            for(int j=0; j<u.length; j++) {
                if(i == j) {
                    An[i][i] = -4;
                }

                else if ((u[i].get_i()+1 == u[j].get_i() || u[i].get_i()-1 == u[j].get_i()) && u[i].get_j() == u[j].get_j()) {
                    An[i][j] = 1;
                }

                else if ((u[i].get_j()+1 == u[j].get_j() || u[i].get_j()-1 == u[j].get_j()) && u[i].get_i() == u[j].get_i()) {
                    An[i][j] = 1;
                }

                else {
                    An[i][j] = 0;
                }
            }
        }

        return An;
    }

    public static double[] b(Node[] u, double[] bounds, double q, float h) {
        double[] b = new double[u.length];

        //Setup b
        for(int i=0; i<u.length; i++) {
            b[i] = q * h * h;

            if(u[i].get_i() == 1) {
                b[i] -= bounds[0];
            }

            else if(u[i].get_i() == u[u.length-1].get_i()) {
                b[i] -= bounds[1];
            }

            if(u[i].get_j() == 1) {
                b[i] -= bounds[2];
            }

            else if(u[i].get_j() == u[u.length-1].get_j()) {
                b[i] -= bounds[3];
            }
        }

        return b;
    }

    public static double[] b(Node[] u, double[] q, float h) {
        double[] b = new double[u.length];

        //Setup b
        for(int i=0; i<u.length; i++) {
            b[i] = q[i] * h * h;
        }

        return b;
    }
}