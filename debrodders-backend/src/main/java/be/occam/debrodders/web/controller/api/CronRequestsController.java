package be.occam.debrodders.web.controller.api;

import static be.occam.utils.spring.web.Controller.response;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import be.occam.debrodders.domain.service.EventService;
import be.occam.debrodders.domain.service.JobService;
import be.occam.debrodders.web.dto.EventDTO;

@Controller
@RequestMapping(value="/cron")
public class CronRequestsController {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( CronRequestsController.class );
	
	@Resource
	protected JobService jobService;
	
	@RequestMapping( value="/matches", method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<EventDTO> get( WebRequest request ) {
		
		logger.info( "cron request received (GET)" );
		
		this.jobService.doUpdateCalendarJob();
		
		return response( new EventDTO(), HttpStatus.OK );
			
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleServiceException(IllegalArgumentException e, WebRequest webRequest ) {
		logger.warn("server error", e);
		return response( e.getMessage(), HttpStatus.BAD_REQUEST );
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleServiceException(Exception e, WebRequest webRequest ) {
		logger.warn("server error", e);
		return response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
}
