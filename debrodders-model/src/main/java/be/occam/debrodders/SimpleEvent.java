package be.occam.debrodders;

import java.util.Date;

public class SimpleEvent<P> extends Event<P, Object> {

	public SimpleEvent(String type, P p) {
		super(type, p);
	}
	
	

}
