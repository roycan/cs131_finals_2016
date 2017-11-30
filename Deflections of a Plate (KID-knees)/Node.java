public class Node {
	public Node(double data, int i, int j) {
		_i = i;
		_j = j;
		_data = data;
	}

	protected int _i, _j;
	protected double _data;

	public int get_i() {
		return _i;
	}

	public int get_j() {
		return _j;
	}
}