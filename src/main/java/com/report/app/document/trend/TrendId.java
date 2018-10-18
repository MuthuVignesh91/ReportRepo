package com.report.app.document.trend;

import java.util.Calendar;
import java.util.Date;

public class TrendId {
	//TODO convert this to Datetime
	private Date timestamp;
	private String deviceId;
	private String parameterName;
	
	public TrendId(Date timestamp, String deviceId, String parameterName) {
		this.timestamp = timestamp;
		this.deviceId = deviceId;
		this.parameterName = parameterName;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
}