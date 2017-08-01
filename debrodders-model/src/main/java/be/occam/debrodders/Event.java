package be.occam.debrodders;

import java.util.Date;

public class Event<P,S> {
	
	protected String uuid;
	protected Date moment;
	
	protected String type;
	
	protected P primary;
	protected S secondary;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public P getPrimary() {
		return primary;
	}
	public void setPrimary(P primary) {
		this.primary = primary;
	}
	public S getSecondary() {
		return secondary;
	}
	public void setSecondary(S secondary) {
		this.secondary = secondary;
	}
	
	

}
