package be.occam.debrodders.domain.people;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import be.occam.debrodders.Event;
import be.occam.debrodders.Match;
import be.occam.debrodders.SimpleEvent;
import be.occam.debrodders.domain.event.EventConsumer;
import be.occam.debrodders.domain.event.Events;
import be.occam.debrodders.match.MatchStatus;
import be.occam.debrodders.match.MatchStatus.Status;

public class MatchStatusKeeper extends EventConsumer {
	
	@Resource
	Eventualist eventualist;
	
	@Resource
	Popper popper;
	
	@Resource
	MatchStatusManager matchStatusManager;
	
	@Override
	public void consume( Event<String,String> event ) {

		/*
		if ( Events.Referee.EVENT_WHISTLES.equals( event.getType() ) ) {
			
			SimpleEvent<Match> e
				= this.eventualist.simple( event, Match.class );
			
			Match m
				= e.getPrimary();
			
			String matchUuid
				= m.getUuid();
			
			MatchStatus matchStatus
				= this.matchStatusManager.findOneByMatchUuid( matchUuid );
			
			if ( matchStatus == null ) {
				matchStatus = this.matchStatusManager.create( matchUuid );
			}
			
			final Status oldStatus
				= matchStatus.getStatus();
			
			Status newStatus 
				= Status.NOT_YET_STARTED;
				
			
			if ( Status.NOT_YET_STARTED.equals( oldStatus) ) {
				
				newStatus = Status.FIRST_HALF;
				
			}
			else if ( Status.FIRST_HALF.equals( oldStatus) ) {
				
				newStatus = Status.INTERMISSION;
				
			}
			else if ( Status.INTERMISSION.equals( oldStatus) ) {
				
				newStatus = Status.SECOND_HALF;
				
			}
			else if ( Status.SECOND_HALF.equals( oldStatus) ) {
				
				newStatus = Status.FINISHED;
				
			}
			
			Date now 
				= new Date();
			matchStatus.setStatus( newStatus );
			matchStatus.setLastWhistle( now );
			matchStatus.setMinutes( this.minutes( matchStatus, now ) );
			
		}
		*/
		
	}
	
	protected int minutes( MatchStatus matchStatus, Date now ) {
		
		int minutes
			= 0;
		
		Status status
			= matchStatus.getStatus();
		
		Date last
			= matchStatus.getLastWhistle();
		
		GregorianCalendar nowCal
			= new GregorianCalendar();
		nowCal.setTime( now );
		
		GregorianCalendar lastCal
			= new GregorianCalendar();
		
		lastCal.setTime( last );
		
		if ( Status.NOT_YET_STARTED.equals( status) ) {
			
			minutes = 0;
			
		}
		else if ( Status.FIRST_HALF.equals( status) ) {
			
			long deltaMillis
				= now.getTime() - last.getTime();
			
			float deltaMinutes
				= ( deltaMillis / 60000 );
			
			minutes = Math.round( deltaMinutes + 0.5F );
			
		}
		else if ( Status.INTERMISSION.equals( status) ) {
			
			minutes = 45;
			
		}
		else if ( Status.SECOND_HALF.equals( status) ) {
			
			long deltaMillis
				= now.getTime() - last.getTime();
		
			float deltaMinutes
				= ( deltaMillis / 60000 );
		
			minutes = Math.round( deltaMinutes + 0.5F );
			
		}
		else if ( Status.FINISHED.equals( status) ) {
			
			minutes = 90;
						
		}
		
		return minutes;
		
	}
	

}
