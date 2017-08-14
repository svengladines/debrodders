package be.occam.debrodders.domain.event;

import be.occam.debrodders.Event;

public abstract class EventConsumer {
	
	public EventConsumer() {
		
	}
	
	public abstract void consume( Event<String,String> event );

}
