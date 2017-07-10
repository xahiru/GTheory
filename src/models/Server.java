
package models;

import java.util.Random;

public class Server extends GThNode{
	
//	int captureCost; //number of services
	int max = 10;
	
	public Server(int id, String name)  {
		
		super(id, name);
		maxServices = max;
	}
	
	public Server(int id) {
		super(id);
		maxServices = max;
		
		Random r = new Random();
		initialServices = r.nextInt(max);
		if(initialServices ==0)
			initialServices = 8;
		
		for (int i = 0; i < initialServices; i++) {
			Service s = new Service();
				s.setAttributeType(Service.SERVER_ATTRIBUTE);
				s.setStatus(false);
				availableServices.add(s);
			
		}
		
	}
	@Override
	public String toString() {
		return "S"+ id +"max:"+max;
	}
	
	

}
