package models;

import java.util.ArrayList;
import java.util.HashMap;

public class GThEdge {
	int id;
	
	boolean onlineStatus;
	final double bandWidth; //total
	final double initial;
	
	GThNode startNode;
	GThNode endNode;
	
	double failRate;
	ArrayList<Service> sharedServices;
	
	public GThEdge(GThNode startNode, GThNode endNode) {
		super();
		this.startNode = startNode;
		this.endNode = endNode;
		sharedServices = new ArrayList<Service>();
		this.bandWidth = startNode.maxServices>endNode.maxServices?endNode.maxServices:startNode.maxServices;
		this.initial = startNode.availableServices.size()>endNode.availableServices.size()?endNode.availableServices.size():startNode.availableServices.size();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getBandWidth() {
		return bandWidth;
	}
//	public void setBandWidth(int bandWidth) {
//		this.bandWidth = bandWidth;
//	}
//	public int getFailRate() {
//		return failRate;
//	}
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
		return sharedServices;
	}
	public boolean addService(Service s) {
		 
		if(bandWidth > sharedServices.size()) {
		this.sharedServices.add(s);
		return true;
		}
		
		return false;
	}
	public int getAvailableBW() {
	 return	(  sharedServices.size());
	}
	
	@Override
	public String toString() {
		return ("(bw:"+bandWidth+", a:"+getAvailableBW() +")" );
	}
	 
	

}
