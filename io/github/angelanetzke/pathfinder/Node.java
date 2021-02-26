package io.github.angelanetzke.pathfinder;

public class Node {
	private boolean isWall;
	public static final int NO_PATH = 0;
	public static final int VERTICAL_PATH = 1;
	public static final int HORIZONTAL_PATH = 2;
	int path;

	public Node() {
		this(false);
	}

	public Node(boolean isWall) {
		path = NO_PATH;
		this.isWall = isWall;
	}

	public void assignPath(int newPath) {
		path = newPath;
	}

	public boolean isWall() {
		return isWall;
	}

	@Override
	public String toString() {
		if (path == VERTICAL_PATH) {
			return "|";
		}
		else if (path == HORIZONTAL_PATH) {
			return "-";
		}
		else {
			if (isWall()) {
				return "#";
			}
			else {
				return " ";
			}
		}
	}

}
