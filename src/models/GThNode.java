package models;

import java.util.ArrayList;
import java.util.Vector;


public class GThNode {
	int id;
	
//	final int INITIAL_SERVICE_COUNT;
	final int INITIAL_CONNECTIONS;
	
//	private ArrayList<Connection> availableConn;
	private ArrayList<GThEdge> nodeEgdes;
	private int availableConn;
	
	
	double captureCost;
	private	boolean valnerable = false;
	
	
	public GThNode(int id,int init_service_count) {
//		INITIAL_SERVICE_COUNT = init_service_count;
		this.id = id;
		nodeEgdes = new ArrayList<>();
		
		this.availableConn = init_service_count;
		INITIAL_CONNECTIONS = init_service_count;
//		availableConn = new ArrayList<Connection>();
//		for (int i = 0; i < init_service_count; i++) {
//			Connection newCon = new Connection(id*10+i+1);
//			availableConn.add(newCon);
//				
//		}
		
		
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

	public ArrayList<GThEdge> getNodeEdges() {
		return nodeEgdes;
	}
	
	

	public void addEdge(GThEdge e) {
		this.nodeEgdes.add(e);
	}
	public void removeEdge(GThEdge e) {
		this.nodeEgdes.remove(e);
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
