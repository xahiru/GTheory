package models;

import java.util.ArrayList;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

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
	

	ArrayList<GThNode> nodes;
	ArrayList<GThEdge> edges;
	ArrayList<Strategy> strategies;
	


//	public Game(int numServers, int numClients, int numAttackers) {
//		super();
//		this.numNodes = numServers + numClients;
//		this.numServers = numServers;
//		this.numClients = numClients;
//		this.numEdges = (this.numNodes * ((this.numNodes) -1))/2 ;
//		this.numAttackers = numAttackers;
////		init();
//		
//	}
	
	


	public void init() {
		
		nodes = new ArrayList<GThNode>();
		edges = new ArrayList<GThEdge>();
		for (int i = 1; i <= numNodes; i++) {
			GThNode n;
			
			if(i<numServers) {
				n = new Server(i, "Server "+i);
		
			}else
			{
				n= new Client(i,"Clients "+i);
	
			}
			nodes.add(n);
			
		}
		
		
		for (int i = 0; i < nodes.size(); i++) {

			
			for (int j = i; j < nodes.size(); j++) {
				
				if(i!=j) {
	
					GThEdge ed = new GThEdge(nodes.get(i),nodes.get(j));
					
					edges.add(ed);
					
				}
			
			}
						
			
		}
		
		
	}


	public void logToConsole(String s, String msg) {
		System.out.println(s+" : "+msg);
	}
	
	public Graph testGraph() {
		
		// Graph<V, E> where V is the type of the vertices
		 // and E is the type of the edges
		 Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
		 // Add some vertices. From above we defined these to be type Integer.
		 g.addVertex((Integer)1);
		 g.addVertex((Integer)2);
		 g.addVertex((Integer)3);
		 // Add some edges. From above we defined these to be of type String
		 // Note that the default is for undirected edges.
		 g.addEdge("Edge-A", 1, 2); // Note that Java 1.5 auto-boxes primitives
		 g.addEdge("Edge-B", 2, 3);
		 // Let's see what we have. Note the nice output from the
		 // SparseMultigraph<V,E> toString() method
		 System.out.println("The graph g = " + g.toString());
		 // Note that we can use the same nodes and edges in two different graphs.
		 Graph<Integer, String> g2 = new SparseMultigraph<Integer, String>();
		 g2.addVertex((Integer)1);
		 g2.addVertex((Integer)2);
		 g2.addVertex((Integer)3);
		 g2.addEdge("Edge-A", 1,3);
		 g2.addEdge("Edge-B", 2,3, EdgeType.UNDIRECTED);
		 g2.addEdge("Edge-C", 3, 2, EdgeType.UNDIRECTED);
		 g2.addEdge("Edge-P", 2,3); // A parallel edge
		 System.out.println("The graph g2 = " + g2.toString()); 
		 
		 return g2;
		
	}
	
	public Graph directedGraph() {

		
		 Graph<GThNode, GThEdge> g = new SparseMultigraph<GThNode, GThEdge>();
//		 
////		 g = new DirectedSparseMultigraph<MyNode, MyLink>();
//		 GThNode n1,n2,n3,n4,n5;
//		 
//		 n1 = new GThNode(1,"one"); n2 = new GThNode(2,"two"); n3 = new GThNode(3,"three");
//		 n4 = new GThNode(4, "four"); n5 = new GThNode(5, "five"); // note n1-n5 declared elsewhere.
//		 // Add some directed edges along with the vertices to the graph
//		 g.addEdge(new GThEdge(2, 48),n1, n2, EdgeType.DIRECTED); // This method
//		 
//	
//		 
//		 g.addEdge(new GThEdge(1, 48),n2, n3, EdgeType.DIRECTED);
//		 g.addEdge(new GThEdge(3, 192), n3, n5, EdgeType.DIRECTED);
//		 g.addEdge(new GThEdge(4, 48), n5, n4, EdgeType.DIRECTED); // or we can use
//		 g.addEdge(new GThEdge(5, 48), n4, n2); // In a directed graph the
//		 g.addEdge(new GThEdge(6, 48), n3, n1); // first node is the source
//		 g.addEdge(new GThEdge(7, 48), n2, n5);// and the second the destination
//		 
		 return g;
		
	}

	public Graph compeletGraph() {
		Graph<GThNode, GThEdge> g = new SparseGraph<GThNode, GThEdge>();
		

		for (int i = 0; i < edges.size(); i++) {
			
					 g.addEdge(edges.get(i),edges.get(i).startNode, edges.get(i).endNode, EdgeType.UNDIRECTED);

			}
						
		logToConsole("INFO: ", "Graph with"+edges.size()+"created");
		
		return g;
	}




	public int getNumNodes() {
		return numNodes;
	}




	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}




	public int getNumServers() {
		return numServers;
	}




	public void setNumServers(int numServers) {
		this.numServers = numServers;
	}




	public int getNumClients() {
		return numClients;
	}




	public void setNumClients(int numClients) {
		this.numClients = numClients;
	}




	public int getNumAttackers() {
		return numAttackers;
	}




	public void setNumAttackers(int numAttackers) {
		this.numAttackers = numAttackers;
	}




	public int getNumDeffenders() {
		return numDeffenders;
	}




	public void setNumDeffenders(int numDeffenders) {
		this.numDeffenders = numDeffenders;
	}




	public int getNumEdges() {
		return numEdges;
	}




	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}
	
	
	
}
