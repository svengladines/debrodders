package be.occam.debrodders.domain.service;

import javax.annotation.Resource;

import be.occam.debrodders.domain.job.UpdateCalendarJob;

public class JobService {
	
	@Resource
	UpdateCalendarJob updateCalendarJob;
	
	public boolean doUpdateCalendarJob() {
		
		boolean noError
			= true;
		
		this.updateCalendarJob.collectAndUpdate();
		
		return noError;
		
	} 

}
