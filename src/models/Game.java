package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.Vector;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Game {
	
	Random rand;
	
	static double DEFENCE_COST_ALPHA = 0.5;
	
	static int numNodes;
	static boolean reverse = false;
	int numServers;
	int numClients;
	
	

	int numEdges;
		
	Graph mainGraph;
	
//	int networkV [];
	
	public static int MAX_SC;
	public static int SC_0;
	public static int SC_D; //current total;
	

	private ArrayList<GThNode> nodes;
	private ArrayList<GThEdge> edges;
	ArrayList<Strategy> strategies;
	
//	ArrayList<> gameStatus;
	
	public Factory <GThNode> vertexFactory;
	public Factory<GThEdge> edgeFactory;
//    int nodeCount;

	private Random randomGenerator;
//	private Timer timer;
	
	
//	ArrayList<HashMap> currentGameState = new ArrayList<>();
	
	HashMap<GThEdge, Integer> currentGameState = new HashMap <GThEdge, Integer>();
	HashMap<GThEdge, Integer> initialGameState  = new HashMap <GThEdge, Integer>();

	//	All states/ a list of all states
	ArrayList<HashMap> gameStates = new ArrayList<>();
	
	ArrayList<AttackStrategy> attkStrategies = new ArrayList<>();
	ArrayList<DefenceStrategy> defStrategies = new ArrayList<>();
	
	
	
	
	public Game(int maxsc,int numServers, int numClients) {
		this.randomGenerator = new Random();
//		this.timer = new Timer();

		//MAX_SC should be > numNodes + 4
		
		this.MAX_SC = maxsc;
		
		this.numNodes = numServers + numClients;
		this.numServers = numServers;
		this.numClients = numClients;
		this.numEdges = (this.numNodes * ((this.numNodes) -1))/2 ;
		
		
		mainGraph = getGraph();

				
	}
	
	public Game() {
		this.randomGenerator = new Random();
		//MAX_SC should be > numNodes + 4
		MAX_SC = 20;
		numNodes = 7;
		numServers = 3;
		this.numEdges = (this.numNodes * ((this.numNodes) -1))/2 ;
		
		mainGraph = getGraph();
		
		Vector attack = new Vector<>();

	
	

		AttackStrategy attStr = new  AttackStrategy(edges,3);
//		AttackStrategy attStr2 = new  AttackStrategy(edges,3);
//		AttackStrategy attStr3 = new  AttackStrategy(edges,3);
//		AttackStrategy attStr4 = new  AttackStrategy(edges,3);
		attkStrategies.add(attStr);
//		attkStrategies.add(attStr2);
//		attkStrategies.add(attStr3);
//		attkStrategies.add(attStr4);
//		
		attack.addElement(attkStrategies);
		
	
		
//		System.out.println("Strategy current cost"+attStr.calculateCost());
//		
//		logToConsole("Strategy Attackchina", "==========");
//		
//		for (int i = 0; i < attStr.AttackChainList.size(); i++) {
//			System.out.println("Chain"+i);
//			for (int j = 0; j < attStr.AttackChainList.get(i).getChain().size(); j++) {
//				System.out.println("Edge"+attStr.AttackChainList.get(i).getChain().get(j).startNode.id+"=>"+attStr.AttackChainList.get(i).getChain().get(j).endNode.id);
//				
//			}
//			
//		}
		

		initialGameState = initGameState();
		
		
		DefenceStrategy df1 = createRandomDefenceStrategy(4);
		DefenceStrategy df2 = createRandomDefenceStrategy(2);
		DefenceStrategy df3 = createRandomDefenceStrategy(6);
		DefenceStrategy df4 = createRandomDefenceStrategy(3);
		
		defStrategies.add(df4);
		defStrategies.add(df3);
		defStrategies.add(df2);
		defStrategies.add(df1);
		
			for (HashMap.Entry<GThEdge, Integer> entry : df1.edgesList.entrySet()) {
				GThEdge e = entry.getKey();
				Integer value = entry.getValue();
				System.out.println(e.startNode.id+"=>"+e.endNode.id+" change:"+value);
			 
			}
	
		System.out.println("df cost:"+df1.calculateCost());
			
				
	}
	
	public Graph getGraph() {
		initGraph();
		return createCompeletGraph();
	}
	
	public void changeGameState(int i) {
		updateNodes(i);
		updateEdges(); 
	}
	
	
	
	
	public void attack(AttackStrategy attackS) {
		
		for (Iterator iterator = attackS.AttackChainList.iterator(); iterator.hasNext();) {
			AttackChian attackChian = (AttackChian) iterator.next();
			
			GThEdge minEdge ;
			
			for (int i = 0; i < attackChian.getChain().size(); i++) {
				if(i == 0)
					minEdge = attackChian.getChain().get(i);
				
			}
			
		}
		
		
	}
	
	public HashMap<GThEdge, Integer> initGameState(){
		
		HashMap<GThEdge, Integer> edgeWeigths = new HashMap <GThEdge, Integer>();
		for (int i = 0; i < edges.size(); i++) {
			GThEdge e = edges.get(i);
			Integer weight = edges.get(i).initialConnCount;
			edgeWeigths.put(e, weight);

		}
		gameStates.add(edgeWeigths);
		return edgeWeigths;
	}
	
	
	
	public DefenceStrategy createRandomDefenceStrategy(int staticNode) {
		
		changeGameState(staticNode);
		
		
		HashMap<GThEdge, Integer> edgeWeigths = new HashMap <GThEdge, Integer>();
		
		for (int i = 0; i < edges.size(); i++) {
			GThEdge e = edges.get(i);
			Integer weightChage = edges.get(i).initialConnCount-edges.get(i).getSharedConns();
			edgeWeigths.put(e, weightChage);
		}
		
		
		return new DefenceStrategy(edgeWeigths);
	}
	
	
	public void updateEdges() {
		int sdtemp = 0;
		for (int i = 0; i < edges.size(); i++) {
			edges.get(i).updateEdgeState();
			sdtemp += edges.get(i).sharedConns;
			
		}
		SC_D = sdtemp;
	}
	
	public void updateNodes(int staticNode) {
		
		for (int i = 0; i < nodes.size(); i++) {
			if(i == staticNode - 2 || i == staticNode)
				continue;
			if(i%3 == 0)
				nodes.get(i).setAvaialableConnections(i*2);
			else
				nodes.get(i).setAvaialableConnections(nodes.get(i).getAvaialableConnections()-i);
			
			
		}
		
	}


	public void initGraph() {
		
		int SCConCount = 0;
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
					SCConCount += ed.initialConnCount; //counting the total initial count of the network
					edges.add(ed);
				}
			}
		}
		
		/*
		 * Setting Initial Total Shared connections of the network
		 */
		SC_0 = SCConCount;
		
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
        
 
	}
	
	public void initStrategies(int numOfStrategies, Graph g) {
		
		
		
		
	}
	
	
	
	
	
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
			
			nServices += nodes.get(i).getAvaialableConnections();
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

	public ArrayList<GThNode> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<GThNode> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<GThEdge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<GThEdge> edges) {
		this.edges = edges;
	}
	
	
	
	
	
}
