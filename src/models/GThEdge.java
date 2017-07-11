package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class GThEdge {
	int id;
	
	final int initialCount;
	
	
	GThNode startNode;
	GThNode endNode;
	
	/*not sure we'll use the following time related variables*/
	Timer timeCounter;
	Date startTime;
	Date interval;
	
	
	double failRate;
	
	ArrayList<Connection> sharedConns; //availableConnection at any given time
	
	public GThEdge(GThNode startNode, GThNode endNode) {
		super();
		this.startNode = startNode;
		this.endNode = endNode;
		sharedConns = new ArrayList<Connection>();
		
		this.initialCount = startNode.INITIAL_SERVICE_COUNT>endNode.INITIAL_SERVICE_COUNT?endNode.INITIAL_SERVICE_COUNT:startNode.INITIAL_SERVICE_COUNT;
		
		this.sharedConns = startNode.getAvaialableConnections().size()>endNode.getAvaialableConnections().size()?endNode.getAvaialableConnections():startNode.getAvaialableConnections();
		
		
		
	}
	
	
	@Override
	public String toString() {
		return ("(ini:"+initialCount+", a:"+getSharedConns().size()+")" );
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isOnline() {
		return this.sharedConns.isEmpty()?false:true;
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


	public double getFailRate() {
		return failRate;
	}


	public void setFailRate(double failRate) {
		this.failRate = failRate;
	}


	public ArrayList<Connection> getSharedConns() {
		return sharedConns;
	}


	public void setSharedConns(ArrayList<Connection> sharedConns) {
		this.sharedConns = sharedConns;
	}
	public void addConns(Connection conns) {	
		this.sharedConns.add(conns);
	}
	public void dropConns(Connection conns) {	
		this.sharedConns.remove(conns);
	}


	public double getInitialCount() {
		return initialCount;
	}
	
	
	 
	

}
