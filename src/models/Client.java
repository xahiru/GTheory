package models;

import java.util.Random;

public class Client extends GThNode {
	int max = 5;
	
public Client(int id, String name)  {
		
		super(id, name);
		maxServices = max;
	}
	
	public Client(int id) {
		super(id);
		maxServices = max;
		Random r = new Random();
		initialServices = r.nextInt(max);
		if(initialServices ==0)
			initialServices = 4;
		
		for (int i = 0; i < initialServices; i++) {
			Service s = new Service();
				s.setAttributeType(Service.CLIENT_ATTRIBUTE);
//				s.setStatus(false);
				availableServices.add(s);
			
		}
		
	}
	@Override
	public String toString() {
		return "C"+ id +"max:"+max;
	}

}
