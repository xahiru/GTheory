package models;

import java.util.Random;

public class Client extends GThNode {
	
public Client(int id, int init_service_count)  {
	
	super(id, init_service_count);
	}
	
	
	@Override
	public String toString() {
		return "C"+ id +"service size:"+ getAvaialableConnections().size()+"Val="+isValnerable();
	}

}
