package com.example.demo.trends;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.document.trend.Trend;
import com.example.demo.document.trend.TrendId;
import com.example.demo.document.trend.TrendValues;
import com.example.demo.repository.TrendRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrendTest {
	
	@Autowired
	TrendRepository trendRepo;
	
	public Date getStartTime()
	{
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	@Test
	public void addTrend()
	{
		TrendId id = new TrendId(getStartTime(), "device1", "S001");
		
		TrendValues param1 = new TrendValues();
		param1.put("0", 20);
		param1.put("1", 30);
		
		Trend trend = new Trend(id, param1);
		trendRepo.save(trend);
		
		TrendValues param2 = new TrendValues();
		param2.put("0", 40);
		param2.put("1", 50);
		
		TrendId id2 = new TrendId(getStartTime(), "device1", "S002");
		
		Trend trend2 = new Trend(id2, param2);
		trendRepo.save(trend2);
		
	}

}
