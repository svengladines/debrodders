package be.occam.debrodders.domain.service;

import static be.occam.utils.javax.Utils.*;
import static be.occam.utils.spring.web.Controller.response;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import be.occam.debrodders.Event;
import be.occam.debrodders.domain.event.EventConsumer;
import be.occam.debrodders.domain.people.Eventualist;
import be.occam.debrodders.domain.people.MailMan;
import be.occam.debrodders.web.dto.EventDTO;
import be.occam.debrodders.web.util.DataGuard;

public class EventService {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	JavaMailSender javaMailSender;
	
	@Resource
	MailMan mailMan;
	
	@Resource
	Eventualist eventualist;
	
	@Resource
	DataGuard dataGuard;
	
	
	protected final Map<String,List<EventConsumer>> eventConsumers;
	
	protected String fromEmailAddress;
	protected String toEmailAddress;
	
	public EventService() {
		this.eventConsumers = map();
	}
	
	public void register( String type, EventConsumer eventConsumer ) {
		
		List<EventConsumer> consumers 
			= this.eventConsumers.get( type );
		
		if ( consumers == null ) {
			consumers = list();
			this.eventConsumers.put( type, consumers );
		}
		
		consumers.add( eventConsumer );
		
	}
	
	@Transactional( readOnly=false )
	public ResponseEntity<EventDTO> consume( EventDTO eventDTO ) {
		
		logger.debug( "consume [{}]", eventDTO );
		
		Event<String, String> event
			= EventDTO.event( eventDTO );
		
		List<EventConsumer> consumers
			= this.eventConsumers.get( event.getType() );
		
		if ( consumers != null ) {
			
			for ( EventConsumer consumer : consumers ) {
				consumer.consume( event );
			}
			
		}
		
		return response( eventDTO, HttpStatus.CREATED );
			
	}
	
	public EventService guard() {
		this.dataGuard.guard();
		return this;
	}

}
