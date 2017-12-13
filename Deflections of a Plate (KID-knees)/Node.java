//The Node class is used by EllipticPDE.java to represent the different points on the plate.
public class Node {
	public Node(int i, int j) {
		_i = i;
		_j = j;
	}

	protected int _i, _j;

	public int get_i() {
		return _i;
	}

	public int get_j() {
		return _j;
	}
}