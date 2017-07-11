package models;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.collections15.Factory;

import EDU.oswego.cs.dl.util.concurrent.misc.Fraction;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Game {
	
	Random rand;
	
	static int numNodes;
	static boolean reverse = false;
	int numServers;
	int numClients;

	int numEdges;
	
	
	
//	enum STATUS {
//		STOPPED, RUNNING, ONLINE, DOWN
//	}
	
	Graph mainGraph;
	
//	int networkV [];
	
	public static int MAX_SC;
	

	ArrayList<GThNode> nodes;
	ArrayList<GThEdge> edges;
	ArrayList<Strategy> strategies;
	
//	ArrayList<> gameStatus;
	
	public Factory <GThNode> vertexFactory;
	public Factory<GThEdge> edgeFactory;
//    int nodeCount;


	
	
	public Game(int maxsc,int numServers, int numClients, int numAttackers) {
		//MAX_SC should be > numNodes + 4
		
		this.MAX_SC = maxsc;
		
		this.numNodes = numServers + numClients;
		this.numServers = numServers;
		this.numClients = numClients;
		this.numEdges = (this.numNodes * ((this.numNodes) -1))/2 ;
		
		
		mainGraph = getGraph();

				
	}
	
	public Game() {
		//MAX_SC should be > numNodes + 4
		MAX_SC = 20;
		numNodes = 7;
		numServers = 3;
		this.numEdges = (this.numNodes * ((this.numNodes) -1))/2 ;
		
		mainGraph = getGraph();
//		GThNode n0 = nodes.get(0);
//		GThNode n3 = nodes.get(2);
//		GThNode n4 = nodes.get(5);
//		GThNode n7 = nodes.get(6);
//		GThNode n1 = nodes.get(1);
//		
//		ArrayList<GThNode> st1 = new ArrayList<>();
//		st1.add(n0);
//		st1.add(n3);
//		st1.add(n4);
//		st1.add(n7);
//		st1.add(n1);
		
		AttackStrategy attStr = new  AttackStrategy(edges);
		
		
		AttackChian attCh = attStr.createRandomAttackChain(3, 30);
		
		System.out.println("Vulsize======>"+attStr.getVulNodeSize());
		System.out.println("Edges should be ======>"+numEdges);
		System.out.println("Edges real size ======>"+edges.size());
		System.out.println("Attr networkEdges size ======>"+attStr.networkEdgesSize());
		
		for (int j = 0;j < attCh.getChain().size()  ; j++) {
			
			logToConsole("Edges", Integer.toString(attCh.getChain().get(j).startNode.id)+"->"+Integer.toString(attCh.getChain().get(j).endNode.id));
			
		}
		
		
		
		
				
	}
	
	public Graph getGraph() {
		initGraph();
		return createCompeletGraph();
	}
	


	public void initGraph() {
//		rand = new Random();
		nodes = new ArrayList<GThNode>();
		edges = new ArrayList<GThEdge>();
//		networkV = new Vector<>();
		
		/*
		 * Creating Nodes/vertices
		 * Each server has MAX_SC-i connection
		 * CURRENT_SERVERCON_MAX = MAX_SC -1 at initialization
		 * MAX_SC should be > numNodes + 4
		 * Each client has Server.CURRENT_SERVERCON_MAX-2+i
		 * 
		 */
		for (int i = 1; i <= numNodes; i++) {
			GThNode n;
			
			
			if(i<=numServers) {
				n = new Server(i,MAX_SC-i);
		
			}else
			{
				n= new Client(i, Server.CURRENT_SERVERCON_MAX-(2+i));
	
			}
			if(i % 2 == 0 ) {
				n.setValnerable(true);

				System.out.println("odd"+n.id);
			}
			
			nodes.add(n);
			
		}
		
		
		/*
		 * creating edges
		 * completegraph
		 * 
		 */
		GThNode inode;
		GThNode jnode;
		for (int i = 0; i < nodes.size(); i++) {

			for (int j = i; j < nodes.size(); j++) {
				
				if(i!=j) {
	
					inode = nodes.get(i);
					jnode = nodes.get(j);
					
					GThEdge ed = new GThEdge(inode,jnode);
					inode.addEdge(ed);
					jnode.addEdge(ed);
					edges.add(ed);
					
					
				}
			
			}
						
			
		}
		
		/*
		 * Factories for editing graph
		 * 
		 */
		vertexFactory = new Factory<GThNode>() { //vertex factory
            public GThNode create() {
            	
            		GThNode n = new GThNode(nodes.size()+1, 200);
            		nodes.add(n);
            		numNodes = nodes.size();
            		logToConsole(Integer.toString(nodes.size()), "node created");
                return n;
            }
        };
        edgeFactory = new Factory<GThEdge>() { // edge factory
            public GThEdge create() {
            	
                		GThEdge e =	new GThEdge(getLastNode(),getSecondLastNode() );
                		logToConsole(Integer.toString(e.getEndNode().id), "edge from");
                		edges.add(e);
                		return e;
            }
        };
        
       /*
        * Initialize netowrkVectro
        */
//        for (int i = 0; i < nodes.size(); i++) {
//        		
//        		int 	q = nodes.get(i).isValnerable()? 1: 0;
//        		networkV.add(q);
//    
//			
//		}
	}
	
	public void initStrategies(int numOfStrategies, Graph g) {
		
		
		
		
	}
	
//	public AttackStrategy creatAttackStrategy(ArrayList<GThNode> nodelist) {
//		
//		return new AttackStrategy(nodelist);
//		
//		
//	}
	
	public Strategy populateRandStrategy() {
		
		return new Strategy(nodes.get(0), 5);
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

	public Graph createCompeletGraph() {

		Graph<GThNode, GThEdge> g = new SparseGraph<GThNode, GThEdge>();

		 GThNode n;
		 
		for (int i = 0; i < edges.size(); i++) {
			
					 g.addEdge(edges.get(i),edges.get(i).startNode, edges.get(i).endNode, EdgeType.UNDIRECTED);

			}
				
		logToConsole("\nINFO: ", "Graph with "+edges.size()+" edge created");
		
		return g;
	}


	public GThNode getLastNode() {
		return nodes.get(nodes.size()-1);
	}
	public GThNode getSecondLastNode() {
		return nodes.get(nodes.size()-2);
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


	//get numb of ports available
	public int getTotalNumConnections() {
		int nServices = 0 ;
		for (int i = 0; i < nodes.size(); i++) {
			
			nServices += nodes.get(i).getAvaialableConnections().size();
		}
		
		return nServices;
	}




	public int getNumClients() {
		return numClients;
	}




	public void setNumClients(int numClients) {
		this.numClients = numClients;
	}



	public int getNumEdges() {
		return numEdges;
	}




	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}
	

	
	
	public void deleteEdge(GThEdge ed) {
	
		ed.getStartNode().getNodeEdges().remove(ed);
		ed.getEndNode().getNodeEdges().remove(ed);
		
	this.edges.remove(ed);
		
		
	}
	
	
	
}
