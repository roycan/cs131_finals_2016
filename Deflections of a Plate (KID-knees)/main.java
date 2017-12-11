import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);
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

        double qD = q / ((E * dz*dz*dz) / (12 * (1 - (sigma*sigma))));

        double[] bounds = {0, 0, 0, 0};
        // bounds[0] top
        // bounds[1] bottom
        // bounds[2] left
        // bounds[3] right

        Node[] u = EllipticPDE.Node_setup(lx, ly, h);
        double[][] A = EllipticPDE.A(u);
        double[] b = EllipticPDE.b(u, bounds, qD, h);


        
        for(int i=0; i<A.length; i++) {
            System.out.println(Arrays.toString(A[i]));
        }

        System.out.println(Arrays.toString(b));

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

        b = EllipticPDE.b(u, ans, h);

        s = ForwardSub.FS(LU[0], b);
        ans = BackwardSub.BS(LU[1], s);

        System.out.println(Arrays.toString(ans));
    }
}