package models;

import java.util.HashMap;

public class DefenceStrategy extends Strategy{
	
	
	HashMap<GThEdge, Integer> edgesList = new HashMap<GThEdge, Integer>();

	public DefenceStrategy(HashMap<GThEdge, Integer> edgesList) {
		this.edgesList = edgesList;
	}

	public HashMap<GThEdge, Integer> getEdgesList() {
		return edgesList;
	}

	public void setEdgesList(HashMap<GThEdge, Integer> edgesList) {
		this.edgesList = edgesList;
	}
	
	public double calculateCost() {
		double cost = 0;
		for (HashMap.Entry<GThEdge, Integer> entry : edgesList.entrySet()) {
			GThEdge e = entry.getKey();
			Integer value = Math.abs(entry.getValue());
			
			cost += value;
		}
		
		return cost*Game.DEFENCE_COST_ALPHA;
	}
	
	

	

}
