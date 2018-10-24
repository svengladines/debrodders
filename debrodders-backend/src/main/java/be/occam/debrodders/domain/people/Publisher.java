package be.occam.debrodders.domain.people;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.utils.one.OneDotComClient;

public class Publisher {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	protected OneDotComClient oneDotComClient;

	public void publish( String file, String content ) {

		try {
			
			String path
				= String.format( "/svekke/data/%s", file );
			
			this.oneDotComClient.store( path, content );
			
		}
		catch( Exception e ) {
			logger.warn( "failed to publish file", e );
		}

	}

	public OneDotComClient getOneDotComClient() {
		return oneDotComClient;
	}

	public void setOneDotComClient(OneDotComClient oneDotComClient) {
		this.oneDotComClient = oneDotComClient;
	}
	
}
