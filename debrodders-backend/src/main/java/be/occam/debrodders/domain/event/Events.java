package be.occam.debrodders.domain.event;

import be.occam.debrodders.Event;
import be.occam.debrodders.Match;
import be.occam.debrodders.SimpleEvent;

public class Events {
	
	public static class Referee {
		
		public static final String EVENT_STARTS_MATCH = "STARTS_MATCH";
		public static final String EVENT_ENDS_MATCH = "ENDS_MATCH";
		public static final String EVENT_PAUZES_MATCH = "PAUZES_MATCH";
		public static final String EVENT_RESUMES_MATCH = "RESUMES_MATCH";
		
		public static final SimpleEvent<Match> startsMatch( Match match ) {
			return new SimpleEvent<Match>( EVENT_STARTS_MATCH, match );
		}
		
		public static final SimpleEvent<Match> endMatch( Match match ) {
			return new SimpleEvent<Match>( EVENT_ENDS_MATCH, match );
		}
			
		
	}
	
}
