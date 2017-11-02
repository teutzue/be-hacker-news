package util;

public class StatusMonitor {

	private static String status="Alive";
	private static StatusMonitor sm = new StatusMonitor();
	private static Integer lastPostId = 0;
	
	private StatusMonitor() {
		
	}
	
	public static Integer getLastPostId() {
		return lastPostId;
	}
	
	public static void setLastPostId(Integer lastPostId) {
		StatusMonitor.lastPostId = lastPostId;
	}
	
	public static String getStatus() {
		return status;
	}
	
	public static void setAlive() {
		status="Alive";
	}
	
	public static void setDown() {
		status="Down";
	}
	
	public static void setUpdate() {
		status="Update";
	}
	
	
	
	
}
