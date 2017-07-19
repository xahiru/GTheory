package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.Vector;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Game {
	
	Random rand;
	
	static double DEFENCE_COST_ALPHA = 0.5;
	
	public static final int SERVER_TYPE = 1;
	public static final int CLIENT_TYPE =2;
	
	static int numNodes;
	static boolean reverse = false;
	int numServers;
	int numClients;
	
	public int vulServer;
	public int vulClient;
	
	

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

	

	HashMap<GThEdge, Integer> currentGameState = new HashMap <GThEdge, Integer>();
	HashMap<GThEdge, Integer> initialGameState  = new HashMap <GThEdge, Integer>();

	//	All states/ a list of all states
	ArrayList<HashMap> gameStates = new ArrayList<>();
	
	public ArrayList<AttackStrategy> attkStrategies = new ArrayList<>();
	public ArrayList<DefenceStrategy> defStrategies = new ArrayList<>();
	
	public List<List<AttackStrategy>> offence = new ArrayList<List<AttackStrategy>>();
	public List<List<DefenceStrategy>> defence = new ArrayList<List<DefenceStrategy>>();
	
	
	
	
	
	
	public Game(int maxsc,int numServers, int vulServers, int numClients, int vulClients) {
	
		this.MAX_SC = maxsc;
		
		this.numServers = numServers;
		this.numClients = numClients;
		this.vulClient = vulClients;
		this.vulServer = vulServers;
		
		
		this.numNodes = numServers + numClients;
		this.numEdges = (this.numNodes * ((this.numNodes) -1))/2 ;
		
		mainGraph = getGraph();
		
		

				
	}
	
	
	public Game(int i) {
	
		//MAX_SC should be > numNodes + 4
		MAX_SC = 20;
		numNodes = 7;
		numServers = 3;
		this.numEdges = (this.numNodes * ((this.numNodes) -1))/2 ;
		
		mainGraph = getGraph();
		
		Vector attack = new Vector<>();

	
	

//		AttackStrategy attStr = new  AttackStrategy(edges,3);
//		AttackStrategy attStr2 = new  AttackStrategy(edges,3);
//		AttackStrategy attStr3 = new  AttackStrategy(edges,3);
//		AttackStrategy attStr4 = new  AttackStrategy(edges,3);
//		attkStrategies.add(attStr);
//		attkStrategies.add(attStr2);
//		attkStrategies.add(attStr3);
//		attkStrategies.add(attStr4);
//		
//		attack.addElement(attkStrategies);
		
	
		
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
		
		updateRandNodesConn(i);
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
			Integer weight = edges.get(i).sharedConns;
			edgeWeigths.put(e, weight);

		}
		gameStates.add(edgeWeigths);
		return edgeWeigths;
	}
	
	
	
	public void addRandDS(int str, int offcount) {
		for (int i = 0; i < offcount; i++) {
			
			for (int j = i; j < str+i; j++) {
				defStrategies.add(createRandomDefenceStrategy(j));
			}
			
			defence.add(defStrategies);
		}
		
		
	}
	
	public void addRandOS( int length, int offcount) {
		
		
		for (int i = 0; i < offcount; i++) {
			
			for (int j = i; j < length+i; j++) {
				
				attkStrategies.add(createRandAttackStrategy(edges,length, 30+j));
			}
			
			offence.add(attkStrategies);
		}
		
		
	}
	
	
	
	public AttackStrategy createRandAttackStrategy(ArrayList<GThEdge> networkEdges, int length, int attempt ) {
		
		AttackStrategy atStr = new AttackStrategy(networkEdges, length, attempt);
		
		return atStr;
				
		
	}
	
	
	public DefenceStrategy createRandomDefenceStrategy(int staticNode) {
		
		changeGameState(staticNode);
		
		
		HashMap<GThEdge, Integer> edgeWeigths = new HashMap <GThEdge, Integer>();
		
		for (int i = 0; i < edges.size(); i++) {
			GThEdge e = edges.get(i);
			Integer weightChage =e.initialConnCount - e.getSharedConns();
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
	
	public void updateRandNodesConn(int k) {
		
		for (int i = 0; i < nodes.size(); i++) {
			
			for (int j = 0; j  < nodes.size(); j++) {
				
				if(i!=j) {
					
					int val = 	nodes.get(i).getEdgeConnection(nodes.get(j))-(j+k);
					if(val<0)
						val = j;
					
					nodes.get(i).updateEdgeConnection(nodes.get(j), val );
				}
			}
			
//			if(i == staticNode - 2 || i == staticNode)
//				continue;
//			if(i%3 == 0)
//				nodes.get(i).setAvaialableConnections(i*2);
//			else
//				nodes.get(i).setAvaialableConnections(nodes.get(i).getAvaialableConnections()-i);
			
			
		}
		
	}


	public void initGraph() {
		
		int SCConCount = 0;
		
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
		
		nodes = createNodes(numServers, Game.SERVER_TYPE,vulServer, nodes);
		nodes = createNodes(numClients, Game.CLIENT_TYPE,vulClient, nodes);
		
		
		/*
		 * creating edges
		 * for completegraph
		 * 
		 */
		edges = createEdges(nodes);
		
		
		/*
		 * Setting Initial Total Shared connections of the network
		 */
		SC_0 = createConnections(nodes, 20);
		
		/*
		 * Factories for editing graph
		 * 
		 */
		vertexFactory = new Factory<GThNode>() { //vertex factory
            public GThNode create() {
            	
            		GThNode n = new GThNode(nodes.size()+1);
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

	
	public ArrayList<GThNode> createNodes(int number,int type, int valCount, ArrayList<GThNode> nList) {
		
		GThNode n;
		
		int size = nList.size();
		switch (type) {
		case 1:
			for (int i = size; i < number+size; i++) {
				
				n = new Server(i);
				if(i<valCount+size)
					n.setValnerable(true);
				nList.add(n);
				
			}
			
			break;
			
		case 2:
			
			for (int i = size; i < number+size; i++) {
				
				n = new Client(i);
				if(i<valCount+size)
					n.setValnerable(true);
				nList.add(n);	
			}
			
			break;
		default:
			break;
		}
		
		return nList;
		
		
	}
	
public ArrayList<GThEdge> createEdges(ArrayList<GThNode> nodes){
	
	  ArrayList<GThEdge> eList = new ArrayList<>();
	
		GThNode inode;
		GThNode jnode;
		GThEdge ed;
		for (int i = 0; i < nodes.size(); i++) {

			for (int j = i; j < nodes.size(); j++) {
				
				if(i!=j) {
	
					inode = nodes.get(i);
					jnode = nodes.get(j);
					
					ed = new GThEdge(inode,jnode);
					inode.addEdge(ed);
					jnode.addEdge(ed);
					eList.add(ed);
				}
			}
			
			
		}
	
		return eList;
}
	
	
public int createConnections(ArrayList<GThNode> nodes,int conn){
	
	conn += (nodes.size()*nodes.size());
	int count = 0;
	GThNode n;
	GThNode jnode;
	for (int i = 0; i < nodes.size(); i++) {
		n = nodes.get(i);
		for (int j = 0; j < nodes.size(); j++) {
			if(i!=j) {
				jnode = nodes.get(j);
				count += conn-(i+j);
				n.addEdgeConnection(nodes.get(j), conn-(i+j));
				n.addNeighbor(jnode);
				jnode.addNeighbor(n);
			}
				
		}
		
		
	}
	return count;
	
}
	
	
	
	
	public Strategy populateRandStrategy() {
		
		return new Strategy(nodes.get(0), 5);
	}
	
	


	public void logToConsole(String s, String msg) {
		System.out.println(s+" : "+msg);
	}
	

	
	

	public Graph createCompeletGraph() {

		Graph<GThNode, GThEdge> g = new SparseGraph<GThNode, GThEdge>();

		 
		for (int i = 0; i < edges.size(); i++) {
			
					 g.addEdge(edges.get(i),edges.get(i).startNode, edges.get(i).endNode, EdgeType.UNDIRECTED);

			}
				
		logToConsole("\nINFO: ", "Graph with "+edges.size()+" edge created");
		
		return g;
	}
	
	public void setGraph(Graph g) {
		this.mainGraph = g;
	}

	public Graph getMainGraph() {
		return mainGraph;
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
	
//		ed.getStartNode().getNodeEdges().remove(ed);
//		ed.getEndNode().getNodeEdges().remove(ed);
		
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
	
	
	public void printALLpaths(int start, int end) {
		
//		AllPaths allP = new AllPaths(mainGraph, nodes.get(start), nodes.get(end));
//		List<List<GThEdge>> allPaths = allP.getAllPathsBetweenNodes();
		List<List<GThEdge>> allPaths = new ArrayList<List<GThEdge>>();
		
		getPaths(nodes.get(2), new ArrayList<GThNode>() ,allPaths);
		
		for (Iterator iterator = allPaths.iterator(); iterator.hasNext();) {
			List<GThEdge> list = (List<GThEdge>) iterator.next();
			System.out.println("newlist");
			for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
				GThEdge gThEdge = (GThEdge) iterator2.next();
				System.out.println(gThEdge.startNode.getId()+"->"+gThEdge.endNode.getId());
				
			}
			
		}
		
		
	}
	
	public class AllPaths {

	    private  List<List<GThEdge>> allPaths;
	    private Graph<GThNode, GThEdge> graph;
	    private GThNode startNode;
	    private GThNode endNode;
	    
	    

	    public AllPaths(Graph<GThNode, GThEdge> graph, GThNode startNode, GThNode endNode) {
			super();
			this.graph = graph;
			this.startNode = startNode;
			this.endNode = endNode;
		}

		public  List<List<GThEdge>> getAllPathsBetweenNodes() {
	        allPaths = new ArrayList<List<GThEdge>>();

	        List<GThEdge> currentPath = new ArrayList<GThEdge>();

	        findAllPaths(startNode, startNode, endNode, currentPath, graph);

	        return allPaths;
	    }

	    private  int findAllPaths(GThNode currentNode, GThNode startNode, GThNode endNode, List<GThEdge> currentPath, Graph<GThNode, GThEdge> graph) {
	        Collection<GThEdge> outgoingEdges = currentNode.getEdges();
	        
	       

	        for (GThEdge outEdge : outgoingEdges) {
	        	GThNode outNode = outEdge.endNode;

	            if (outNode.equals(startNode)) {
	                List<GThEdge> cyclePath = new ArrayList<GThEdge>(currentPath);
	                cyclePath.add(outEdge);
	                System.out.println("Found cycle provoked by path " + cyclePath);
	                continue;
	            }

	            List<GThEdge> newPath = new ArrayList<GThEdge>(currentPath);
	            newPath.add(outEdge);

	            if (outNode.equals(endNode)) {
	                allPaths.add(newPath);
	                continue;
	            }
	            if(currentNode == this.startNode)
	    	        return 0;

	            findAllPaths(outNode, startNode, endNode, newPath, graph);
	        }
	   
	        return 0;
	    }
	    
	    
	}
	
	public void getPaths (GThNode n, ArrayList<GThNode> nodesVisited, List<List<GThEdge>> mainList) {
//	    int pathCount = 0;
		
//		List<List<GThEdge>> allPaths = new ArrayList<List<GThEdge>>();
		
	    ArrayList<GThEdge> edgeList = new ArrayList<>();
	    
	    for (GThEdge p : n.getEdges()) {
	    	GThNode otherSide = p.endNode; // Where this function basically takes a node and gets the other node in the path
	        if (!(nodesVisited.contains(otherSide))) {
	            nodesVisited.add(otherSide);
	            getPaths(otherSide, new ArrayList<GThNode>(nodesVisited), mainList); 
	            edgeList.add(p);
	            if(!mainList.contains(edgeList))
	            		mainList.add(edgeList);
	            
	        }
	    }
//	    allPaths.add(edgeList);
//	    return allPaths;
	}
	
	
	public  List<List<GThEdge>> getPaths(ArrayList<GThEdge> edgeList) {
		List<List<GThEdge>> mainList = new ArrayList<List<GThEdge>>();
		
		return mainList;
		
	}


	
	
}
