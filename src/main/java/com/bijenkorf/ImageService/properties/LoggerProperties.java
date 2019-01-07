package com.bijenkorf.ImageService.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logger-properties")
public class LoggerProperties {
	/**
	 * the database endpoint for writing logs
	 */
	private String logdbEndpoint;
	/**
	 * the name of the database or schema to connect to
	 */
	private String logdbName;
	/**
	 * the log database username
	 */
	private String logdbUsername;
	/**
	 * the log database password
	 */
	private String logdbPassword;

	public String getLogdbEndpoint() {
		return logdbEndpoint;
	}

	public void setLogdbEndpoint(String logdbEndpoint) {
		this.logdbEndpoint = logdbEndpoint;
	}

	public String getLogdbName() {
		return logdbName;
	}

	public void setLogdbName(String logdbName) {
		this.logdbName = logdbName;
	}

	public String getLogdbUsername() {
		return logdbUsername;
	}

	public void setLogdbUsername(String logdbUsername) {
		this.logdbUsername = logdbUsername;
	}

	public String getLogdbPassword() {
		return logdbPassword;
	}

	public void setLogdbPassword(String logdbPassword) {
		this.logdbPassword = logdbPassword;
	}

}
