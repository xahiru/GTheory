package models;

import java.util.ArrayList;

public class Game {
	
	int numNodes;
	int numServers;
	int numClients;
	int numAttackers;
	int numDeffenders;
	int numEdges;
//	enum STATUS {
//		STOPPED, RUNNING, ONLINE, DOWN
//	}
	

	ArrayList<Node> nodes;
	ArrayList<Edge> edges;
	ArrayList<Strategy> strategies;
	
	
	
	public Game(int numServers, int numClients, int numAttackers) {
		super();
		this.numNodes = numServers + numClients;
		this.numServers = numServers;
		this.numClients = numClients;
		this.numAttackers = numAttackers;
	}

	public void init() {
		
		for (int i = 1; i < numNodes; i++) {
			Node n;
			
			if(i<numServers) {
				n = new Server(i, "Server "+i, ROLE.DEFENDER);
		
			}else
			{
				n= new Client(i,"Clients "+i, ROLE.DEFENDER);
	
			}
			nodes.add(n);
			
			
		}
		
	}


	public void logToConsole(String s, String msg) {
		System.out.println(s+" : "+msg);
	}
	
	


}
