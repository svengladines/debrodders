package be.occam.debrodders.domain.job;

import static be.occam.utils.javax.Utils.*;
import java.util.Map;

import javax.swing.text.html.parser.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import be.occam.debrodders.domain.object.Match;
import be.occam.utils.spring.web.Client;
import net.htmlparser.jericho.Source;

public class UpdateCalendarJob {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final String calendarURL
		= "https://extranet.e-kickoff.com/kbvb_publiek/kalender.do";
	
	/**
	 * Collect calendar info from the league's website, then build json file and publish it to the frontend 
	 */
	public void collectAndUpdate() {
		
		this.collect();
		
	}
	
	protected void collect() {
		
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
		
			/*
			Source source
				= new Source( html );
			
			Element movieIDElement
				= source.getFirstElement("name", "FilmId", true );
			
			Element sessionIDElement
				= source.getFirstElement("name", "SessionId", true );
			
			if ( movieIDElement == null ) {
				
				logger.warn( "vista html = [{}]", html );
				
				throw new RuntimeException( "vista says: no movieId");
				
			}
			
			if ( sessionIDElement == null ) {
				
				logger.warn( "vista html = [{}]", html );
				
				throw new RuntimeException( "vista says: no sessionId");
				
			}
			
			String movieID
				= movieIDElement.getAttributeValue("value");
			
			String sessionID
				= sessionIDElement.getAttributeValue("value");
				
			 */
		
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

}
