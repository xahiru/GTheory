package models;

import java.util.ArrayList;
import java.util.Vector;


public class GThNode {
	int id;
	String name;
//	ROLE role;
	ArrayList<Service> services;
	ArrayList<GThEdge> activeConnections;
	ArrayList<GThNode>	peers;
	boolean attackStartNode;
	int captureCost;
	boolean valnerable;
	
	Vector profitVector;
	
	
	
	public GThNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;
//		this.role = role;
		
	}

	public ArrayList<GThNode> scanOnlineNodes() {
		ArrayList<GThNode> onlineList =new ArrayList<GThNode>();
		//Todo searching goes here
		
		return onlineList;
	}
	
	public boolean attack(GThNode node, Strategy strategy) {
		boolean successStatus = false;
		
		//attack
		return successStatus;
	}
	
	
	

}
