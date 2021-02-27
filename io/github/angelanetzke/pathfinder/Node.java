package io.github.angelanetzke.pathfinder;

import java.util.ArrayList;
import java.util.List;

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
	private int distanceTraveled;
	private final int column;
	private final int row;
	private Node link;

	public Node(int type, int distanceToEnd, int column, int row) {
		this.column = column;
		this.row = row;
		assignType(type);
		this.distanceToEnd = distanceToEnd;
		if (type == START) {
			link = this;
			distanceTraveled = 0;
		}
		else {
			link = null;
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
		if (nextNode != link && nextNode.getDistanceTraveled() > getDistanceTraveled() + 1) {
			nextNode.addToPath(this);
			return true;
		}
		else {
			return false;
		}
	}

	public void addToPath(Node link) {
		this.link = link;
		distanceTraveled = link.link.getDistanceTraveled() + 1;
	}

	public List<Node> getPath() {
		if (type == START) {
			return new ArrayList<Node>();
		}
		else {
			if (link.getColumn() == getColumn()) {
				assignType(VERTICAL_PATH);
			}
			else {
				assignType(HORIZONTAL_PATH);
			}
			List<Node> path = link.getPath();
			path.add(link);
			return path;
		}
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
