package io.github.angelanetzke.pathfinder;

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid(40, 15);
		System.out.println();
		System.out.println("Initial Grid:");
		System.out.println(grid.toString());
		//grid.findPath();
		System.out.println("Path:");
		System.out.println(grid.toString());
	}

}
