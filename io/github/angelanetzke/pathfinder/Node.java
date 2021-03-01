package io.github.angelanetzke.pathfinder;

public class Node implements Comparable<Node> {
	private boolean isWall;
	public static final int EMPTY = 0;
	public static final int VERTICAL_PATH = 1;
	public static final int HORIZONTAL_PATH = 2;
	public static final int CORNER_PATH = 3;
	public static final int WALL = 4;
	public static final int START = 5;
	public static final int END = 6;
	private int type;
	private int distanceToEnd;
	private int distanceTraveled;
	private final int column;
	private final int row;
	private Node previous;
	private Node next;

	public Node(int type, int distanceToEnd, int column, int row) {
		this.column = column;
		this.row = row;
		assignType(type);
		this.distanceToEnd = distanceToEnd;
		previous = null;
		next = null;
		if (type == START) {
			distanceTraveled = 0;
		}
		else {
			distanceTraveled = Integer.MAX_VALUE;
		}
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

	public boolean visit(Node nextNode) {
		if (nextNode != previous && nextNode.getDistanceTraveled() > getDistanceTraveled() + 1) {
			nextNode.addToPath(this);
			return true;
		}
		else {
			return false;
		}
	}

	public void addToPath(Node link) {
		previous = link;
		distanceTraveled = link.getDistanceTraveled() + 1;
	}

	public void getPath() {
		if (type == START) {
			return;
		}
		else if (type == END) {
			previous.next = this;
			previous.getPath();
		}
		else {
			previous.next = this;
			if (previous.getColumn() == next.getColumn()) {
				assignType(VERTICAL_PATH);
			}
			else if (previous.getRow() == next.getRow()) {
				assignType(HORIZONTAL_PATH);
			}
			else {
				assignType(CORNER_PATH);
			}
		}
		previous.getPath();
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public int getTotalDistance() {
		return distanceTraveled + distanceToEnd;
	}

	public boolean isWall() {
		return isWall;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	@Override
	public int compareTo(Node o) {
		if (getTotalDistance() < o.getTotalDistance()) {
			return -1;
		}
		else if (getTotalDistance() > o.getTotalDistance()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public String toString() {
		switch (type) {
			case VERTICAL_PATH:
				return "|";
			case HORIZONTAL_PATH:
				return "-";
			case CORNER_PATH:
				return "+";
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
