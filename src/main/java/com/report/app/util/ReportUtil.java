package com.report.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.report.app.document.trend.TrendValues;

public class ReportUtil {
	private final Calendar cal = Calendar.getInstance();
	
	private Set<Date> getSortedDate(HashMap<String, TrendValues> reportContent, Date trendStartDate, SimpleDateFormat format) throws ParseException
	{ 
		Set<Date> dateIndex = new TreeSet<>();
		for(Map.Entry<String, TrendValues> reportContentInstance : reportContent.entrySet())
		{
			for(Map.Entry<String, Object> maxParamDataInstance : reportContentInstance.getValue().entrySet())
			{
				Integer timeInMillis = Integer.parseInt(maxParamDataInstance.getKey())*1000;
				cal.setTimeInMillis(trendStartDate.getTime()+timeInMillis);
				dateIndex.add(format.parse(format.format(cal.getTime())));
			}
		}
		return dateIndex;
	}

	public Date getStartOfDay() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public Date getEndOfDay() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	
	public TreeMap<Date, HashMap<String, Object>> getCustomReportContent(HashMap<String, TrendValues> reportContent, Date trendStartDate, SimpleDateFormat format) throws ParseException
	{
		Set<Date> dateIndex = getSortedDate(reportContent, trendStartDate, format);
		Iterator<Date> dateIterator = dateIndex.iterator();
		TreeMap<Date, HashMap<String, Object>> finalMap = new TreeMap<>();
		while(dateIterator.hasNext())
		{
			Date currentDateInstance = dateIterator.next();
			for(Map.Entry<String, TrendValues> reportContentInstance : reportContent.entrySet())
			{
				String[] reportContentHeader = reportContentInstance.getKey().split("\\*");
				TrendValues value = reportContentInstance.getValue();
				for(Map.Entry<String, Object> valueIns : value.entrySet())
				{
				Integer timeInMillis = Integer.parseInt(valueIns.getKey())*1000;
				cal.setTimeInMillis(trendStartDate.getTime()+timeInMillis);
				Date currentTime = format.parse(format.format(cal.getTime()));
				HashMap<String, Object> tempMap = null;
				if(currentDateInstance.equals(currentTime))
				{
					if(finalMap.containsKey(currentTime))
					 tempMap= finalMap.get(currentTime);
					else
					 tempMap = new HashMap<>();
					
					if(!valueIns.getValue().toString().equalsIgnoreCase(reportContentHeader[1].toString()))
						tempMap.put(reportContentHeader[0], valueIns.getValue());
					else
						tempMap.put(reportContentHeader[0], reportContentHeader[1]);
					
					finalMap.put(currentDateInstance, tempMap);
				}
					
				}
			}
		}
		return finalMap;
		
	}

	public String[][] convertMapToArray(TreeMap<Date, HashMap<String, Object>> finalMap, String[][] destinationArray)
	{
		int finalMapIndex =1;
		for(Map.Entry<Date, HashMap<String, Object>> finalMapInstance: finalMap.entrySet())
		{
			destinationArray[finalMapIndex][0] = finalMapInstance.getKey().toGMTString();
			int columnIndex =1;
			for(Map.Entry<String, Object> paramIns : finalMapInstance.getValue().entrySet())
			{
				for(int i=1; i< destinationArray[0].length; i++)
				{
					try
					{
						if(destinationArray[0][i].equalsIgnoreCase(paramIns.getKey()))
						{
							columnIndex=i;
							break;
						}
						else
						{
							columnIndex++;
						}
					}
					catch (Exception e) {
						destinationArray[0][columnIndex] = paramIns.getKey();
						break;
					}
				}
				destinationArray[finalMapIndex][columnIndex] = paramIns.getValue().toString();
			}
			finalMapIndex++;
		}
		return destinationArray;
	}
}
