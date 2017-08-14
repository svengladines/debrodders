package be.occam.debrodders.web.dto;

import java.util.Date;

import be.occam.debrodders.Event;

public class EventDTO {
	
	protected String uuid;
	protected Date moment;
	
	protected String type;
	
	protected String primary;
	protected String secondary;
	
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
	
	public String getPrimary() {
		return primary;
	}
	
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	
	public String getSecondary() {
		return secondary;
	}
	
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	
	public static Event<String,String> event( EventDTO f ) {
		
		Event<String,String> t
			= new Event<String,String>( f.getPrimary(), f.getSecondary() );
		
		t.setType( f.getType() );
		t.setUuid( f.getUuid() );
		
		return t;
		
	}
	
	
}
