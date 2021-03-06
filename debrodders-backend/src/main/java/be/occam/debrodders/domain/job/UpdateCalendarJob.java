package be.occam.debrodders.domain.job;

import static be.occam.utils.javax.Utils.*;
import static be.occam.utils.javax.Utils.map;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import be.occam.debrodders.domain.object.Match;
import be.occam.debrodders.domain.people.Editor;
import be.occam.debrodders.domain.people.Publisher;
import be.occam.utils.one.OneDotComClient;
import be.occam.utils.spring.web.Client;
import be.occam.utils.timing.Timing;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

/* https://datalake-prod2018.rbfa.be/graphql?operationName=GetTeamCalendar&variables=%7B%22teamId%22%3A%22158437%22%2C%22language%22%3A%22nl%22%2C%22sortByDate%22%3A%22asc%22%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%22bf4be0c185dee11a27079e529a04d41dc692389ada678dac1f2280e056de7b7d%22%7D%7D */

public class UpdateCalendarJob {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final String calendarURL
		= "https://datalake-prod2018.rbfa.be/graphql?operationName=GetTeamCalendar&variables=%7B%22teamId%22%3A%22158437%22%2C%22language%22%3A%22nl%22%2C%22sortByDate%22%3A%22asc%22%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%22bf4be0c185dee11a27079e529a04d41dc692389ada678dac1f2280e056de7b7d%22%7D%7D";
	
	protected final SimpleDateFormat datePattern
		= new SimpleDateFormat("dd-MM-yyyy");
	
	protected final SimpleDateFormat timePattern
		= new SimpleDateFormat( "hh:mm" );
	
	protected final Comparator<Match> kickOffComparator 
		= new Comparator<Match>() {

			@Override
			public int compare(Match m1, Match m2) {
				return m1.getKickOff().compareTo( m2.getKickOff() );
			}
		
			
		};
		
	@Resource
	protected Editor editor;
	
	@Resource
	protected Publisher publisher;
	
	/**
	 * Collect match info from the league's website, then build json file(s) and publish it to the frontend 
	 */
	public void collectAndUpdate() {
		
		List<Match> allMatches
			= this.collect();
		
		List<Match> previousMatches
			= list();
		
		Match matchOfToday
			= null;
		
		List<Match> nextMatches
			= list();
		
		Date now
			= new Date();
		
		for ( Match match : allMatches ) {
			// could be optimized, as allMatches is sorted ...
			/*
			if ( Timing.isSameDay( match.getKickOff(), now ) ) {
				matchOfToday = match;
				continue;
			}
			
			else  */if ( match.getKickOff().before( now ) ) {
				previousMatches.add( match );
			}
			else {
				nextMatches.add( match );
			}
		}
		
		publish( null, previousMatches, nextMatches );
		
		
	}
	
	protected List<Match> collect() {
		
			List<Match> matches
				= list();
		
			String url
				= calendarURL;

			Map<String,String> headers
				= this.getHeaders();

			ResponseEntity<String> htmlResponse
				= Client.postFormUrlEncoded(
						url,
						String.class,
						headers);
			
			String html	
				= htmlResponse.getBody();
			
			logger.info( "html is [{}]", html );
		
			
			Source source
				= new Source( html );
			
			List<Element> tables
				= source.getAllElementsByClass( "table1" );
			
			Element calendarTable
				= tables.get( 1 );
			
			List<Element> rows
				= calendarTable.getChildElements();
			
			for( Element row : rows ) {
				
				List<Element> cells
					= row.getChildElements();
				
				Element dateElement
					= cells.get( 0 );
				
				String dateString
					= dateElement.getTextExtractor().toString().trim();
				
				try {
					Date date
						= Timing.date( dateString, datePattern );
				}
				catch( RuntimeException e ) {
					logger.warn("not a valid date, skip: [{}]", dateString );
					continue;
				}
				
				logger.info( "date is [{}]", dateString );
				
				Element timeElement
					= cells.get( 1 );
			
				String timeString
					= timeElement.getTextExtractor().toString();
			
				logger.info( "time is [{}]", timeString );
				
				String kickOffString
					= new StringBuilder( dateString ).append( " " ).append( timeString ).toString();
				
				Date kickOff
					= Timing.date( kickOffString, "dd-MM-yyyy hh:mm" );
				
				Element homeElement
					= cells.get( 2 );
		
				String homeString
					= homeElement.getTextExtractor().toString();
		
				logger.info( "home is [{}]", homeString );
				
				Match match
					= new Match();
				
				Element awayElement
					= cells.get( 3 );
	
				String awayString
					= awayElement.getTextExtractor().toString();
	
				logger.info( "away is [{}]", awayString );
				
				match.setHomeTeam( homeString );
				match.setAwayTeam( awayString );
				match.setKickOff( kickOff );
				
				Element goalsElement
					= cells.get( 4 );
				
				if ( goalsElement != null ) {
	
					String goalsString
						= goalsElement.getTextExtractor().toString();
					
					logger.info( "goalsString is [{}]", goalsString );
					
					if ( ! isEmpty( goalsString ) ) {
						
						String homeGoalsString 
							= goalsString.substring(0, goalsString.indexOf( "-") ).trim();
								
						if ( ! isEmpty( homeGoalsString ) ) {
					
							int homeGoals 
								= Integer.parseInt( homeGoalsString );
		
							logger.info( "home goals is [{}]", homeGoals );
						
							match.setHomeGoals( homeGoals );
							
						}
						
						String awayGoalsString 
							= goalsString.substring( goalsString.indexOf( "-") + 1 ).trim();
						
						if ( ! isEmpty( awayGoalsString ) ) {
						
							int awayGoals 
								= Integer.parseInt( awayGoalsString );
							
							logger.info( "away goals is [{}]", awayGoals );
							
							match.setAwayGoals( awayGoals );
							
						}
						
					}
				
				}
				
				matches.add( match );
				
			}
			
			Collections.sort( matches, kickOffComparator );
			
			return matches;
				
	}
	
	protected Map<String,String> getHeaders() {
		
		/**
		 *
		 */
		Map<String,String> headers
			= map();
		
		return headers;
	}
	
	protected void publish( Match matchOfTheDay, List<Match> previousMatches, List<Match> nextMatches ) {
		
		// publish to match-current.json TODO
		
		// publish to matches-previous.json
		String previousMatchesJson
			= this.editor.writeMatches( previousMatches );
		
		this.publisher.publish( "previous-matches.json", previousMatchesJson );
		
		// publish to matches-next.json
		String nextMatchesJson
			= this.editor.writeMatches( nextMatches );
		
		this.publisher.publish( "next-matches.json", nextMatchesJson );
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
}
