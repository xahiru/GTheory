package models;

import java.util.ArrayList;
import java.util.Vector;


public class GThNode {
	int id;
	String name;
	int maxServices;
	int initialServices;
//	ROLE role;
	ArrayList<Service> availableServices;
	ArrayList<GThEdge> nodeEgdes;
	ArrayList<GThNode>	peers;
	boolean attackStartNode;
	double captureCost;
	boolean valnerable = false;
	
	Vector profitVector;
	
	
	
	public GThNode(int id, String name) {
		super();
		this.id = id;
		this.name = name;

		nodeEgdes = new ArrayList<>();
		availableServices = new ArrayList<Service>();
		
		
	}
	
	public GThNode(int id) {
		super();
		this.id = id;
		nodeEgdes = new ArrayList<>();
		availableServices = new ArrayList<Service>();
		
		
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Service> getAvaialableServices() {
		return availableServices;
	}

	public void setAvaialableServices(ArrayList<Service> services) {
		this.availableServices = services;
	}

	public ArrayList<GThEdge> getNodeEdges() {
		return nodeEgdes;
	}
	

	public void addEdge(GThEdge e) {
		this.nodeEgdes.add(e);
	}
	public void removeEdge(GThEdge e) {
		this.nodeEgdes.remove(e);
	}
	
	public ArrayList<GThNode> getPeers() {
		return peers;
	}

	public void setPeers(ArrayList<GThNode> peers) {
		this.peers = peers;
	}

	public boolean isAttackStartNode() {
		return attackStartNode;
	}

	public void setAttackStartNode(boolean attackStartNode) {
		this.attackStartNode = attackStartNode;
	}

	public double getCaptureCost() {
		return captureCost;
	}

	public void setCaptureCost(double captureCost) {
		this.captureCost = captureCost;
	}

	public boolean isValnerable() {
		return valnerable;
	}

	public void setValnerable(boolean valnerable) {
		this.valnerable = valnerable;
	}

	public Vector getProfitVector() {
		return profitVector;
	}

	public void setProfitVector(Vector profitVector) {
		this.profitVector = profitVector;
	}

	
	
	
	
	

}
