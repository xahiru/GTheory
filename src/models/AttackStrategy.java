package models;

import java.util.ArrayList;
import java.util.Random;

public class AttackStrategy {
	
	private Random randomGenerator;
	
	private ArrayList<AttackChian> AttackChainList;
	private ArrayList<GThEdge> networkEdges;
	private ArrayList<GThEdge> vulStartEdges;
	
	AttackStrategy(ArrayList<GThEdge> networkEdges) {
		this.randomGenerator = new Random();
		this.AttackChainList = new ArrayList<>();
		this.networkEdges = networkEdges;
		this.vulStartEdges = new ArrayList<>(); 
		
		for (int i = 0; i < networkEdges.size(); i++) {
			addStartEdge(networkEdges.get(i));
//			addStartEdge(networkEdges.get(i).getEndNode());
			
		}
		
	}
	
	public int networkEdgesSize() {
		return networkEdges.size();
	}
	
	public boolean addStartEdge(GThEdge p) {
		
		if((p.getStartNode().isValnerable()||p.endNode.isValnerable() ) && ! vulStartEdges.contains(p)) {
			vulStartEdges.add(p);
			return true;
		}
		else
			return false;
	}
	
	public void addAttackChain(AttackChian ac) {
		this.AttackChainList.add(ac);
	}
	
	public int getVulNodeSize() {
		return vulStartEdges.size();
	}
	
//	public GThNode getStartNode(int index) {
//		return vulNodesq.get(index);
//	}
	
	public AttackChian createRandomAttackChain(int chainLength,int attempt){
		
		ArrayList<GThEdge> participants = new ArrayList<>();
		int index = randomGenerator.nextInt(vulStartEdges.size());
		GThEdge startEdge  = vulStartEdges.get(index);
		participants.add(startEdge);
		
		AttackChian	 attackChain = new AttackChian(attempt,participants);
		if(networkEdges.size()<chainLength)
			return attackChain;
		
		int i = 0;
		
		int counter = 0;
		while(chainLength > counter ) {
		
				if(attackChain.getChain().size()<=chainLength){
				
				
					if(attackChain.addAnEdgeToChain(networkEdges.get(i)))
					{	
						counter++;				
					}
					
				}
			
				i++;
				if(i == networkEdges.size())
					i =0;
				
			}
		
		/*
		 * removing for future chains
		 * 
		 */
		
		for (int j = 0; j < attackChain.getChain().size(); j++) {
			if(networkEdges.contains(attackChain.getChain().get(j)))
				networkEdges.remove(attackChain.getChain().get(j));
				
		}
		
		
		return attackChain;
	}

}
