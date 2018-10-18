package com.report.app.reportconfig.trendreport;

import java.util.ArrayList;

public class ParamArray {
	
private ArrayList<Parameters> params = new ArrayList<>();
	
	public final void addToList(Parameters parameter)
	{
		params.add(parameter);
	}
	
	public final Parameters[] getParamArray()
	{
		return params.toArray(new Parameters[params.size()]);
	}

}
