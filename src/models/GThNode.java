package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class GThNode {
	int id;
	
	
//	final int INITIAL_CONNECTIONS;
	

	private ArrayList<GThNode> neighbors;
	private HashMap<GThNode, Integer> edgeConnections = new HashMap <GThNode, Integer>();
	
	
	private int availableConn;
	
	
	double captureCost;
	private	boolean valnerable = false;
	
	
	public GThNode(int id) {
	
		this.id = id;
		neighbors = new ArrayList<>();
		

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getAvaialableConnections() {
		return availableConn;
	}

	public void setAvaialableConnections(int conns) {
		if(conns > 0) {
			this.availableConn = conns;
		}else
		{
			this.availableConn = 0;
		}
		
	}

	public ArrayList<GThNode> getNeighbors() {
		return neighbors;
	}
	
	

	public void addNeighbor(GThNode e) {
		this.neighbors.add(e);
	}
	
	public void removeEdge(GThEdge e) {
		this.neighbors.remove(e);
	}
	
	public void addEdgeConnection(GThNode n, int conn) {
		this.edgeConnections.put(n, conn);
	}
	public void updateEdgeConnection(GThNode edgeEndNode, int conn) {
		this.edgeConnections.put(edgeEndNode, conn);
	}
	
//	public void removeEdgeConnection(GThEdge e) {
//		this.edgeConnections.remove(e);
//	}
	
	public int getEdgeConnection(GThNode n) {
		return this.edgeConnections.get(n);
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

	

}
