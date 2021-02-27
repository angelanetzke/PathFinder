package io.github.angelanetzke.pathfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
	private final Node[][] nodes;
	private final int WIDTH;
	private final int HEIGHT;
	private final int WALL_CHANCE = 4;
	private Node startNode;
	private Node endNode;

	public Grid(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		Random rng = new Random();
		nodes = new Node[WIDTH][HEIGHT];
		for (int column = 0; column < WIDTH; column++) {
			for (int row = 0; row < HEIGHT; row++) {
				int manhattanDistance = (WIDTH - 2 - column) + (HEIGHT - 2 - row);
				if (column == 0 || column == width - 1 || row == 0 || row == height - 1) {
					nodes[column][row] = new Node(Node.WALL, manhattanDistance, column, row);
				}
				else if (column == 1 && row == 1) {
					startNode = new Node(Node.START, manhattanDistance, column, row);
					nodes[column][row] = startNode;
				}
				else if (column == WIDTH - 2 && row == HEIGHT - 2) {
					endNode = new Node(Node.END, 0, column, row);
					nodes[column][row] = endNode;
				}
				else {
					if (column % 2 == 0) {
						int roll = rng.nextInt(WALL_CHANCE);
						if (roll == 0) {
							nodes[column][row] = new Node(Node.WALL, manhattanDistance, column, row);
						}
						else {
							nodes[column][row] = new Node(Node.EMPTY, manhattanDistance, column, row);
						}
					}
					else {
						nodes[column][row] = new Node(Node.EMPTY, manhattanDistance, column, row);
					}
				}
			}
		}
	}

	private Node getNode(int column, int row) {
		if (column >= 0 && column < WIDTH && row >= 0 && row < HEIGHT) {
			return nodes[column][row];
		}
		else {
			return null;
		}
	}

	private List<Node> expand(Node n) {
		List<Node> neighbors = new ArrayList<Node>();
		int thisColumn = n.getColumn();
		int thisRow = n.getRow();
		Node[] neighborsTemp = new Node[] { getNode(thisColumn - 1, thisRow), getNode(thisColumn + 1, thisRow),
				getNode(thisColumn, thisRow - 1), getNode(thisColumn, thisRow + 1) };
		for (Node thisNode : neighborsTemp) {
			if (thisNode != null && !thisNode.isWall()) {
				neighbors.add(thisNode);
			}
		}
		return neighbors;
	}

	public void findPath() {
		NodeQueue queue = new NodeQueue();
		List<Node> discarded = new ArrayList<Node>();
		queue.push(startNode);
		Node nextNode = queue.peek();
		while (nextNode != endNode && queue.size() > 0) {
			List<Node> expansion = expand(nextNode);
			queue.pop();
			discarded.add(nextNode);
			for (Node neighbor : expansion) {
				if (!discarded.contains(neighbor)) {
					if (nextNode.visit(neighbor)) {
						queue.push(neighbor);
					}
				}
			}
			if (queue.size() > 0) {
				nextNode = queue.peek();
			}
		}
		endNode.getPath();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < HEIGHT; row++) {
			for (int column = 0; column < WIDTH; column++) {
				builder.append(nodes[column][row]);
			}
			builder.append('\n');
		}
		return builder.toString();
	}

}
