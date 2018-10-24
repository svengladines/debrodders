package be.occam.debrodders.domain.job;

import static be.occam.utils.javax.Utils.list;
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

public class UpdateCalendarJob {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final String calendarURL
		= "https://extranet.e-kickoff.com/kbvb_publiek/kalender.do";
	
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
	 * Collect calendar info from the league's website, then build json file(s) and publish it to the frontend 
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
			
			MultiValueMap<String, Object> fields
				= this.postFormFields();
			
			Map<String,String> headers
				= this.postHeaders();

			ResponseEntity<String> htmlResponse
				= Client.postFormUrlEncoded(
						url,
						String.class,
						fields,
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
				
				Element awayElement
					= cells.get( 3 );
	
				String awayString
					= awayElement.getTextExtractor().toString();
	
				logger.info( "away is [{}]", awayString );
			
				Match match
					= new Match();
				
				match.setHomeTeam( homeString );
				match.setAwayTeam( awayString );
				match.setKickOff( kickOff );
				
				matches.add( match );
				
			}
			
			Collections.sort( matches, kickOffComparator );
			
			return matches;
				
	}
	
	protected MultiValueMap<String, Object> postFormFields() {
		
		/**
		 * selectedSerPlus_id: PCC82943
			wat: data
			KBVB_datumvan_dag: 01
			KBVB_datumvan_maand: 09
			KBVB_datumvan_jaar: 2018
			KBVB_datumtot_dag: 31
			KBVB_datumtot_maand: 05
			KBVB_datumtot_jaar: 2019
			enkel: test
			LANG: N
			secid: 7394
			useCssNewFootbel: hosted-pages
			matricule: 41178
			command: Bekijken
		 */
		 MultiValueMap<String, Object> fields
	    	= new LinkedMultiValueMap<String,Object>();
		
		fields.add( "selectedSerPlus_id", "PCC82943" );
		fields.add( "wat", "data" );
		fields.add( "KBVB_datumvan_dag", "01" );
		fields.add( "KBVB_datumvan_maand", "09" );
		fields.add( "KBVB_datumvan_jaar", "2018" );
		fields.add( "KBVB_datumtot_dag", "31" );
		fields.add( "KBVB_datumtot_maand", "05" );
		fields.add( "KBVB_datumtot_jaar", "2019" );
		fields.add( "enkel", "test" );
		fields.add( "LANG", "N" );
		fields.add( "secid", "7394" );
		fields.add( "useCssNewFootbel", "hosted-pages" );
		fields.add( "matricule", "41178" );
		fields.add( "command", "Bekijken" );
		
		return fields;
	}
	
	protected Map<String,String> postHeaders() {
		
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
