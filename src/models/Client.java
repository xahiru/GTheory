package models;

import java.util.Random;

public class Client extends GThNode {
	
public Client(int id)  {
	
	super(id);
	}
	
	
	@Override
	public String toString() {
		return "C"+ id +"service size:"+ getAvaialableConnections()+"Val="+isValnerable();
	}

}
