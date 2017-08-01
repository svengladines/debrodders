package be.occam.debrodders;

import java.util.Date;

public class Match {
	
	protected String uuid;
	protected String code;
	
	protected Date kickOff;
	protected Team homeTeam;
	protected Team visitingTeam;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getKickOff() {
		return kickOff;
	}
	public void setKickOff(Date kickOff) {
		this.kickOff = kickOff;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getVisitingTeam() {
		return visitingTeam;
	}
	public void setVisitingTeam(Team visitingTeam) {
		this.visitingTeam = visitingTeam;
	}
	
	

}
