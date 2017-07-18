
package models;

import java.util.Random;

public class Server extends GThNode{
	
	static int CURRENT_SERVERCON_MAX;
	
	public Server(int id) {
		
		super(id);
		
	}

	
	@Override
	public String toString() {
		return "S"+ id +" service size:"+ getAvaialableConnections()+"Val="+isValnerable();
	}
	
	

}
