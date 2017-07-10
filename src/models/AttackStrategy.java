package models;

import java.util.ArrayList;

public class AttackStrategy extends Strategy {
	
	AttackStrategy(ArrayList<GThNode> nodeList) {
		super();
		for (int i = 0; i < nodeList.size(); i++) {
			addParicipant(nodeList.get(i));
		}
		
		
		// TODO Auto-generated constructor stub
	}
	ArrayList<GThNode> attackChainNodes;
	ArrayList<Service> attackChainServices;
	
	 boolean addParicipant(GThNode p) {
		if(p.isValnerable()) {
			this.participants.add(p);
			return true;
		}
		else
			return false;
	}
	

}
