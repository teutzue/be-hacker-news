package util;

import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

public class StatusMonitor {
	private static String status="Alive";
	private static StatusMonitor sm = new StatusMonitor();
	private static Integer lastPostId = 0;

	static final Counter requests = Counter.build()
		.name("requests_total").help("Total requests.").register();
	

	static final Summary requestLatency = Summary.build()
		     .name("requests_latency_seconds").help("Request latency in seconds.").register();

	private StatusMonitor() {

		
	}

	public static void incrementCounter() {
		requests.inc();
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
	
	public static Summary getRequestlatency() {
		return requestLatency;
	}
	
	
	
	
}
