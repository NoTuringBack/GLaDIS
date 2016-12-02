package com.noturingback.gladis.controller;

import android.content.Context;

import com.noturingback.gladis.model.Granule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrong on 02/12/16.
 */

public class GranuleParser
{
	public static List<Granule> parseGranulesFromFile(String filename, Context context)
	{
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();

		try
		{
			reader = new BufferedReader(
					new InputStreamReader(context.getAssets().open(filename)));

			// do reading, usually loop until end of file reading
			String mLine;
			while ((mLine = reader.readLine()) != null)
			{
				//process line
				sb.append(mLine + "\n");
			}
			String result = sb.toString();
			return parseGranules(result);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static List<Granule> parseGranules(String s) throws JSONException
	{
		List<Granule> result = new ArrayList<>();

		JSONArray array = new JSONArray(s);

		for(int i = 0 ; i < array.length(); i++)
		{
			JSONObject obj = array.getJSONObject(i);
			Granule g = parseGranule(obj.toString());
			result.add(g);
		}

		return result;
	}

	public static Granule parseGranule(String s) throws JSONException
	{
		Granule g = new Granule();

		JSONObject obj = new JSONObject(s);
		g.setType(obj.getString("type"));
		g.setOptimalKeywords(obj.getInt("optimal_keywords"));

		JSONArray karray = obj.getJSONArray("keyword");
		for(int i = 0 ; i < karray.length() ; i++)
		{
			g.getKeywords().add(karray.getString(i).toUpperCase());
		}

		JSONArray aarray = obj.getJSONArray("answer");
		for(int i = 0 ; i < aarray.length() ; i++)
		{
			g.getAnswers().add(aarray.getString(i));
		}

		return g;
	}
}
