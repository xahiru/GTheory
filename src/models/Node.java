package models;

import java.util.ArrayList;
import java.util.Vector;


public class Node {
	int id;
	String name;
//	ROLE role;
	ArrayList<Service> services;
	ArrayList<Edge> activeConnections;
	ArrayList<Node>	peers;
	boolean status;
	
	Vector profitVector;
	
	
	
	public Node(int id, String name, ROLE role) {
		super();
		this.id = id;
		this.name = name;
//		this.role = role;
		
	}

	public ArrayList<Node> scanOnlineNodes() {
		ArrayList<Node> onlineList =new ArrayList<Node>();
		//Todo searching goes here
		
		return onlineList;
	}
	
	public boolean attack(Node node, Strategy strategy) {
		boolean successStatus = false;
		
		//attack
		return successStatus;
	}
	
	
	

}
