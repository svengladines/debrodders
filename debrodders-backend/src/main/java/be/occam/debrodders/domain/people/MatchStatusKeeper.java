package be.occam.debrodders.domain.people;

import javax.annotation.Resource;

import be.occam.debrodders.Event;
import be.occam.debrodders.Match;
import be.occam.debrodders.SimpleEvent;
import be.occam.debrodders.domain.event.EventConsumer;
import be.occam.debrodders.domain.event.Events;

public class MatchStatusKeeper extends EventConsumer {
	
	@Resource
	Eventualist eventualist;
	
	@Resource
	Popper popper;
	
	@Resource
	MatchStatusManager matchStatusManager;
	
	@Override
	public void consume( Event<String,String> event ) {

		if ( Events.Referee.EVENT_STARTS_MATCH.equals( event.getType() ) ) {
			
			SimpleEvent<Match> e
				= this.eventualist.simple( event, Match.class );
			
			
			
		}
		
		
	}
	
	
	

}
