package com.noturingback.gladis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrong on 02/12/16.
 */

public class Granule
{
	private String type;
	private int optimalKeywords = 0;
	private List<String> keywords = new ArrayList<>();
	private List<String> answers = new ArrayList<>();

	public int getOptimalKeywords()
	{
		return optimalKeywords;
	}

	public void setOptimalKeywords(int optimalKeywords)
	{
		this.optimalKeywords = optimalKeywords;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public List<String> getKeywords()
	{
		return keywords;
	}

	public void setKeywords(List<String> keywords)
	{
		this.keywords = keywords;
	}

	public List<String> getAnswers()
	{
		return answers;
	}

	public void setAnswers(List<String> answers)
	{
		this.answers = answers;
	}
}
