package be.occam.debrodders.match;

public class MatchStatus {
	
	public static enum Status {
		TO_BE_STARTED, IN_PROGRESS, FINISHED
	};
	
	protected String uuid;
	protected Status status;
	protected int homeGoals;
	protected int visitorGoals;
	protected int minutes;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getHomeGoals() {
		return homeGoals;
	}
	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}
	public int getVisitorGoals() {
		return visitorGoals;
	}
	public void setVisitorGoals(int visitorGoals) {
		this.visitorGoals = visitorGoals;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	
	
}
