package io.github.angelanetzke.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class NodeQueue {
	private List<Node> nodes;
	
	public NodeQueue() {
		nodes = new ArrayList<Node>();
	}
	
	public void add(Node newNode) {
		nodes.add(newNode);
		List<Node> tempList = new ArrayList<Node>();
		while(nodes.size() > 0) {
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
