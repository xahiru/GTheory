package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TimerTask;

public class AttackStrategy extends TimerTask {
	
	private Random randomGenerator;
	private double cost;
	
	public ArrayList<AttackChian> AttackChainList;
	private ArrayList<GThEdge> networkEdges;
	private ArrayList<GThEdge> vulStartEdges;
	
	AttackStrategy(AttackChian atc) {
		this.AttackChainList = new ArrayList<>();
		AttackChainList.add(atc);
		this.vulStartEdges = new ArrayList<>(); 
		networkEdges  = new ArrayList<>();
		
	}
	
	AttackStrategy(ArrayList<GThEdge> networkEdges, int length) {
		this.randomGenerator = new Random();
		this.AttackChainList = new ArrayList<>();
		this.networkEdges = networkEdges;
		this.vulStartEdges = new ArrayList<>(); 
		
		for (int i = 0; i < networkEdges.size(); i++) {
			addStartEdge(networkEdges.get(i));
//			addStartEdge(networkEdges.get(i).getEndNode());
			
		}
		
		while(AttackChainList.size()<length) {
			run();
			addAttackChain(createRandomAttackChain(2, 30));
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
	
	public boolean addAttackChain(AttackChian ac) {
		
		if(!isAttackChainValidt(ac))
			return false;
		this.AttackChainList.add(ac);
		return true;
		
	}
	
	public boolean isAttackChainValidt(AttackChian acc) {
		
		ArrayList<GThEdge> storedEdges = new ArrayList<>();
		for (int i = 0; i < AttackChainList.size(); i++) {
			AttackChian storedChain = AttackChainList.get(i);
				
			for (int j = 0; j < storedChain.getChain().size(); j++) {
				storedEdges.add( storedChain.getChain().get(j));
			}
					
		}
		
		for (int j = 0; j < acc.getChain().size(); j++) {
			if(storedEdges.contains( acc.getChain().get(j)))
				return false;
		}
		
		return true;
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
		while(chainLength-1 > counter) {
		
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
		
//		for (int j = 0; j < attackChain.getChain().size(); j++) {
//			if(networkEdges.contains(attackChain.getChain().get(j)))
//				networkEdges.remove(attackChain.getChain().get(j));
//				
//		}
		
		
		return attackChain;
	}

@Override
public void run() {
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}

 public double calculateCost() {
	 
	double cost = 0;
	 
	 for (Iterator iterator = AttackChainList.iterator(); iterator.hasNext();) {
		AttackChian attackChian = (AttackChian) iterator.next();
		
		int chainSize = attackChian.getSize();
		int attempt = attackChian.getAttempt();
		double attackCost = chainSize * attempt;
		
		double captureCost = attackChian.getChainCaptureCost();
		
		cost += attackCost + captureCost;
		
	}
	 this.cost = cost;
	 return cost;
 }
 
 
	
 
 

}
