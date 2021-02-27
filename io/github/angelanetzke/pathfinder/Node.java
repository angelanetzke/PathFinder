package io.github.angelanetzke.pathfinder;

public class Node {
	private boolean isWall;
	public static final int EMPTY = 0;
	public static final int VERTICAL_PATH = 1;
	public static final int HORIZONTAL_PATH = 2;
	public static final int WALL = 3;
	public static final int START = 4;
	public static final int END = 5;
	private int type;
	private int distanceToEnd;

	public Node(int type, int distanceToEnd) {
		assignType(type);
		this.distanceToEnd = distanceToEnd;
	}

	public void assignType(int newType) {
		type = newType;
		if (type == WALL) {
			isWall = true;
		}
		else {
			isWall = false;
		}
	}

	public boolean isWall() {
		return isWall;
	}

	@Override
	public String toString() {
		switch (type) {
			case VERTICAL_PATH:
				return "|";
			case HORIZONTAL_PATH:
				return "-";
			case WALL:
				return "#";
			case START:
				return "S";
			case END:
				return "E";
			default:
				return " ";
		}
	}

}
