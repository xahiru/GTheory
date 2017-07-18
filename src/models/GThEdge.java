package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class GThEdge {
	String id;
	
	 int initialConnCount;
	int sharedConns;
	static int c =0;
	
	
	GThNode startNode;
	GThNode endNode;
	
	/*not sure we'll use the following time related variables*/
	Timer timeCounter;
	Date startTime;
	Date interval;
	
	
	double failRate;
	
	public GThEdge(GThNode startNode, GThNode endNode) {
	
		this.id = String.valueOf(startNode.getId())+ String.valueOf(endNode.getId());
		this.startNode = startNode;
		this.endNode = endNode;
//		sharedConns = new ArrayList<Connection>();
		
		
	}
	
	public void updateEdgeState() {
		this.sharedConns = startNode.getEdgeConnection(endNode) >=endNode.getEdgeConnection(startNode)?endNode.getEdgeConnection(startNode):startNode.getEdgeConnection(endNode);
		if(GThEdge.c ==0)
		initialConnCount = sharedConns;
		
		c++;
		
	}
	
	
	@Override
	public String toString() {
		return ("(ini:"+sharedConns+", a:"+getSharedConns()+")" );
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isOnline() {
		return this.sharedConns==0?false:true;
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


	public int getSharedConns() {
		return sharedConns;
	}


//	public void setSharedConns(ArrayList<Connection> sharedConns) {
//		this.sharedConns = sharedConns;
//	}
//	public void addConns(Connection conns) {	
//		this.sharedConns.add(conns);
//	}
//	public void dropConns(Connection conns) {	
//		this.sharedConns.remove(conns);
//	}


	public double getInitialCount() {
		return sharedConns;
	}
	
	
	 
	

}
