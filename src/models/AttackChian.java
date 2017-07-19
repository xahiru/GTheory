package models;

import java.util.ArrayList;

public class AttackChian {
	private int id;
	private int attempt;
	private ArrayList<GThEdge> chain;
	
	public AttackChian(int attempt, ArrayList<GThEdge> chain) {
		
//		this.id = id;
		this.attempt = attempt;
		this.chain = chain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public ArrayList<GThEdge> getChain() {
		return chain;
	}

	public void setChain(ArrayList<GThEdge> chain) {
		this.chain = chain;
	}
	
	public double getChainCaptureCost() {
		if(!chain.isEmpty())
		return chain.get(0).startNode.captureCost;
		return 0;
	}
	
	public boolean addAnEdgeToChain(GThEdge edge) {
		GThEdge lastEdge;
		if(!chain.isEmpty())
		 lastEdge = chain.get(chain.size()-1);	
		else {
			chain.add(edge);
			return true;
		}
			
//		System.out.println("lastedge endnode id:"+lastEdge.startNode.id+"==>"+lastEdge.endNode.id);
//		System.out.println("new edge startnode"+edge.startNode.id+"==>"+edge.endNode.id);
		
		
		if(lastEdge != null  && !chain.contains(edge) ) {
			if (lastEdge.endNode == edge.startNode ){
				chain.add(edge);
				return true;
			}
			else 
			 if(lastEdge.endNode.id == Game.numNodes && edge.endNode == lastEdge.endNode && !Game.reverse){
				Game.reverse = true;
				chain.add(edge);
				return true;
				
			}else if(lastEdge.endNode.id == Game.numNodes && edge.startNode == lastEdge.startNode && Game.reverse) {
				Game.reverse = false;
				chain.add(edge);
				return true;
			}else
				
			return false;
			
		}
		return false;	
	}
	
	public int getSize() {
		if(!chain.isEmpty())
		return chain.size();
		return 0;
	}
	
	
	
	
	

}
