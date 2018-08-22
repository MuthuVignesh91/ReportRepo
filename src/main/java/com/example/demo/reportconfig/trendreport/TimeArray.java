package com.example.demo.reportconfig.trendreport;

/**
 * @author muthu
 *
 */
public class TimeArray extends Parameters {
	
    public TimeArray(String label, String format, String colname) {
		this.label = label;
		this.format = format;
		this.colname = colname;
	}

	private String label;

    private String format;

    private String colname;

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

    public String getFormat ()
    {
        return format;
    }

    public void setFormat (String format)
    {
        this.format = format;
    }

    public String getColname ()
    {
        return colname;
    }

    public void setColname (String colname)
    {
        this.colname = colname;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [label = "+label+", format = "+format+", colname = "+colname+"]";
    }
}