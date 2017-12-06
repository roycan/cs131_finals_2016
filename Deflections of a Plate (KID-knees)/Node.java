public class Node {
	public Node(int i, int j) {
		_i = i;
		_j = j;
	}

	protected int _i, _j;
	protected double _data;

	public int get_i() {
		return _i;
	}

	public int get_j() {
		return _j;
	}

	public double get_data() {
		return _data;
	}

	public void set_data(double data) {
		_data = data;
	}
}