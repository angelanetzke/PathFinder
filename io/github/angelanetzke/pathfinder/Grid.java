package io.github.angelanetzke.pathfinder;

import java.util.Random;

public class Grid {
	private final Node[][] nodes;
	private final int WIDTH;
	private final int HEIGHT;
	private final int WALL_CHANCE = 4; 

	public Grid(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		Random rng = new Random();
		nodes = new Node[WIDTH][HEIGHT];
		for (int column = 0; column < WIDTH; column++) {
			for (int row = 0; row < HEIGHT; row++) {
				int manhattanDistance = (WIDTH - 2 - column) + (HEIGHT - 2 - row);
				if (column == 0 || column == width - 1 || row == 0 || row == height - 1) {
					nodes[column][row] = new Node(Node.WALL, manhattanDistance);
				}
				else if (column == 1 && row == 1) {
					nodes[column][row] = new Node(Node.START, manhattanDistance);
				}
				else if (column == WIDTH - 2 && row == HEIGHT - 2) {
					nodes[column][row] = new Node(Node.END, manhattanDistance);
				}
				else {
					if (column % 2 == 0) {
						int roll = rng.nextInt(WALL_CHANCE);
						if (roll == 0) {
							nodes[column][row] = new Node(Node.WALL, manhattanDistance);
						}
						else {
							nodes[column][row] = new Node(Node.EMPTY, manhattanDistance);
						}
					}
					else {
						nodes[column][row] = new Node(Node.EMPTY, manhattanDistance);
					}					
				}
			}
		}
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
