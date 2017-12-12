import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);

        //Input the constants
        System.out.print("Enter the x-axis length: ");
        float lx = user_input.nextFloat();
        System.out.print("Enter the y-axis length: ");
        float ly = user_input.nextFloat();
        System.out.print("Enter the delta x/y (equal dx & dy): ");
        float h = user_input.nextFloat();
        System.out.print("Enter the load: ");
        double q = user_input.nextDouble();
        System.out.print("Enter the thickness: ");
        double dz = user_input.nextDouble();
        System.out.print("Enter the Poisson's ratio: ");
        double sigma = user_input.nextDouble();
        System.out.print("Enter the Modulus of Elasticity: ");
        double E = user_input.nextDouble();

        //Calculate the constant 'q/D'
        double qD = q / ((E * dz*dz*dz) / (12 * (1 - (sigma*sigma))));

        //Since the metal plate is supported on all edges, the value for the bounds are 0
        double[] bounds = {0, 0, 0, 0};
        // bounds[0] top
        // bounds[1] bottom
        // bounds[2] left
        // bounds[3] right

        //Using EllipticPDE class to setup 'Ax=b' to solve for 'u'
        Node[] u = EllipticPDE.Node_setup(lx, ly, h);
        double[][] A = EllipticPDE.A(u);
        double[] b = EllipticPDE.b(u, bounds, qD, h);


        
        for(int i=0; i<A.length; i++) {
            System.out.println(Arrays.toString(A[i]));
        }

        System.out.println(Arrays.toString(b));

        //Uses LU decomposition to solve the obtained 'Ax=b'
        double[][][] LU = LUdecompCrout.LU(A);
        double[] s = ForwardSub.FS(LU[0], b);
        double[] ans = BackwardSub.BS(LU[1], s);

        //for(int i=0; i<length; i++) {
        //  System.out.println(Arrays.toString(LU[0][i]));
        //}

        //for(int i=0; i<length; i++) {
        //  System.out.println(Arrays.toString(LU[1][i]));
        //}

        System.out.println(Arrays.toString(ans));

        //Using the obtained values for 'u', we now set up a 'b' to solve for 'z'
        b = EllipticPDE.b(u, bounds, ans, h);
        s = ForwardSub.FS(LU[0], b);
        ans = BackwardSub.BS(LU[1], s);

        System.out.println(Arrays.toString(ans));
    }
}