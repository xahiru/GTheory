package models;

public class Service {
	
	public static final int SERVER_ATTRIBUTE = 3;
	public static final int CLIENT_ATTRIBUTE = 2;
	public static final int EDGE_ATTRIBUTE = 1;
	
	int id;
	String name;
	int attributeType;
	int requiredBandwith;
	boolean status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(int attributeType) {
		this.attributeType = attributeType;
	}
	public int getRequiredBandwith() {
		return requiredBandwith;
	}
	public void setRequiredBandwith(int requiredBandwith) {
		this.requiredBandwith = requiredBandwith;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
