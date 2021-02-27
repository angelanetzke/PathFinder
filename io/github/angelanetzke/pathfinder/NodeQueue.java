package io.github.angelanetzke.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class NodeQueue {
	private List<Node> nodes;

	public NodeQueue() {
		nodes = new ArrayList<Node>();
	}

	public void push(Node newNode) {
		if (!nodes.contains(newNode)) {
			nodes.add(newNode);
			List<Node> tempList = new ArrayList<Node>();
			while (nodes.size() > 0) {
				Node smallest = null;
				for (Node thisNode : nodes) {
					if (smallest == null || thisNode.getTotalDistance() < smallest.getTotalDistance()) {
						smallest = thisNode;
					}
				}
				tempList.add(smallest);
				nodes.remove(smallest);
			}
			nodes = tempList;
		}
	}

	public Node peek() {
		return nodes.get(0);
	}

	public void pop() {
		nodes.remove(0);
	}

	public int size() {
		return nodes.size();
	}
}
