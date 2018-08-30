package be.occam.brodders.utest.domain.job;

import org.junit.Test;

import be.occam.debrodders.domain.job.UpdateCalendarJob;

public class TestUpdateCalendarJob {
	
	@Test
	public void doesItSmoke() {
		new UpdateCalendarJob().collectAndUpdate();
	}

}
