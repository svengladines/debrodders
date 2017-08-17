package be.occam.debrodders.domain.event;

import be.occam.debrodders.Event;
import be.occam.debrodders.Match;
import be.occam.debrodders.SimpleEvent;

public class Events {
	
	public static class Referee {
		
		public static final String EVENT_WHISTLES = "WHISTLE";
		
		public static final SimpleEvent<Match> whistles( Match match ) {
			return new SimpleEvent<Match>( EVENT_WHISTLES, match );
		}
		
	}
	
}
