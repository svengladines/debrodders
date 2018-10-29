package be.occam.debrodders.utest.domain.job;

import org.junit.Test;

import be.occam.debrodders.domain.job.UpdateCalendarJob;
import be.occam.debrodders.domain.people.Editor;
import be.occam.debrodders.domain.people.Publisher;
import be.occam.utils.one.OneDotComClient;

public class TestUpdateCalendarJob {
	
	@Test
	public void doesItSmoke() {
		
		UpdateCalendarJob job
			= new UpdateCalendarJob();
		
		String userName
			= System.getProperty( "one.userName" );
		
		String passWord
			= System.getProperty( "one.passWord" );
		
		OneDotComClient oneDotComClient
			= new OneDotComClient( "debrodders.be", userName, passWord);
		
		Publisher publisher
			= new Publisher();
		
		publisher.setOneDotComClient( oneDotComClient );
		
		job.setPublisher( publisher );
		
		Editor editor
			= new Editor();
		
		job.setEditor( editor );
		
		job.collectAndUpdate();
		
	}

}
