package models;

import java.util.ArrayList;

public class GThEdge {
	int id;
	String onlineStatus;
	int bandWidth; //total
	int failRate;
	GThNode startNode;
	GThNode endNode;
	ArrayList<Service> services;
	public GThEdge(GThNode startNode, GThNode endNode) {
		super();
		this.startNode = startNode;
		this.endNode = endNode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	public int getBandWidth() {
		return bandWidth;
	}
	public void setBandWidth(int bandWidth) {
		this.bandWidth = bandWidth;
	}
	public int getFailRate() {
		return failRate;
	}
	public void setFailRate(int failRate) {
		this.failRate = failRate;
	}
	public GThNode getStartNode() {
		return startNode;
	}
	public void setStartNode(GThNode startNode) {
		this.startNode = startNode;
	}
	public GThNode getEndNode() {
		return endNode;
	}
	public void setEndNode(GThNode endNode) {
		this.endNode = endNode;
	}
	public ArrayList<Service> getServices() {
		return services;
	}
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}
	
	 
	

}
