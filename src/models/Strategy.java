package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Strategy {
//	public static final String STR_TYPE_DEFENDER = "Defendece strategy";
//	public static final String STR_TYPE_ATTACKER = "Attack strategy";
	int id;
	String name;
//	String strategyType;
	
	ArrayList<GThNode> participants;
	
	Strategy(GThNode startNode,int length){
		participants = new ArrayList<>();
//		createRandomParticipantList(startNode,length);
		
	}
	
	Strategy(ArrayList<GThNode> nodeList){
		this.participants = new ArrayList<>();
		
	}
	Strategy(){
		participants = new ArrayList<>();
	}
	
	
	
	public ArrayList<GThNode> createRandomParticipantList(GThNode startNode,int length){
		
		ArrayList<GThNode> nodes = new ArrayList<>();
		
		int counter = 0;
		boolean stopcounter = true;
		
		GThNode currentNode = startNode;
		
//		nodes.add();
		
		recursiveSearch(currentNode.getNodeEdges().isEmpty(), currentNode);
		
		return nodes;
	}
	public ArrayList<GThNode> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<GThNode> participants) {
		this.participants = participants;
	}
	
	
	
	
	private GThNode recursiveSearch(boolean empty, GThNode node){
		System.out.println("node id:"+node.id+" :"+node.isValnerable());
	    if (node.isValnerable())
	        return node;
	    ArrayList<GThEdge> edgeNodes = node.getNodeEdges(); 
	    GThNode res = null;
	    for (int i = 0; res == null && i < edgeNodes.size(); i++) { 
	    		
	        res = recursiveSearch(edgeNodes.isEmpty(), edgeNodes.get(i).endNode);
	      
	    }
	    if(res.isValnerable())
	    	return res;
	    else {
	     return	recursiveSearch(false, node);
	    }
	 }

	
	
	

}
