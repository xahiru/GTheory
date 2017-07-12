
package models;

import java.util.Random;

public class Server extends GThNode{
	
	static int CURRENT_SERVERCON_MAX;
	
	public Server(int id, int init_service_count) {
		
		super(id, init_service_count);
		
		CURRENT_SERVERCON_MAX = (CURRENT_SERVERCON_MAX>init_service_count) ?CURRENT_SERVERCON_MAX:init_service_count;
		
	}

	
	
	@Override
	public String toString() {
		return "S"+ id +" service size:"+ getAvaialableConnections()+"Val="+isValnerable();
	}
	
	

}
