package be.occam.debrodders.domain.people;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;

import be.occam.debrodders.util.DataGuard;

public class MailMan {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	JavaMailSender javaMailSender;
	
	@Resource
	DataGuard dataGuard;
	
	public boolean deliver( MimeMessage mimeMessage ) {
		try {
			
			dataGuard.guard();
			
			Properties props = new Properties();
			// SGL| doesn't seem to work ... tries SMTP
			// this.javaMailSender.send( mimeMessage ); 
			Transport.send( mimeMessage );
			logger.info( "sent email to {}", (Object) mimeMessage.getRecipients(  RecipientType.TO ) );
			return true;
			
		}
		catch( Exception e ) {
			logger.error( "could not send email", e );
			throw new RuntimeException( e );
		}
		
	}
	
	public MimeMessage message( ) {
		
		try {
			return this.javaMailSender.createMimeMessage();
		}
		catch( Exception e ) {
			logger.error( "could not create mime message", e );
			throw new RuntimeException( e );
		}
		
	}
	
}
