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
	private List<Node> pathToNode;

	public Node(int type, int distanceToEnd) {
		assignType(type);
		this.distanceToEnd = distanceToEnd;
		if (type == START) {
			pathToNode = new ArrayList<Node>();
			pathToNode.add(this);
		}
		else {
			pathToNode = null;
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

	public void visit(Node nextNode) {
		if (!nextNode.isWall()) {
			if (nextNode.getDistanceTraveled() > getDistanceTraveled() + 1) {
				nextNode.addToPath(pathToNode);
			}
		}
	}

	public void addToPath(List<Node> previous) {
		pathToNode = previous;
		pathToNode.add(this);
	}

	public int getDistanceTraveled() {
		if (pathToNode == null) {
			return Integer.MAX_VALUE;
		}
		else {
			return pathToNode.size();
		}
	}

	public int getTotalDistance() {
		if (pathToNode == null) {
			return Integer.MAX_VALUE;
		}
		else {
			return pathToNode.size() - 1 + distanceToEnd;
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
