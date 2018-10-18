package com.report.app.document.trend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trends")
public class Trend {

	@Id
	private TrendId id;
	private TrendValues values;

	
	

	public Trend(TrendId id, TrendValues values) {
		this.id = id;
		this.values = values;

	}
	
	public TrendId getId() {
		return id;
	}
	public void setId(TrendId id) {
		this.id = id;
	}
	public TrendValues getValues() {
		return values;
	}
	public void setValues(TrendValues values) {
		this.values = values;
	}
	
	
}
