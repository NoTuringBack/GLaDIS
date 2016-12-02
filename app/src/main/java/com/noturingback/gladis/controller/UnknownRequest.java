package com.noturingback.gladis.controller;

import android.content.Context;

import com.noturingback.gladis.model.ConvMessage;
import com.noturingback.gladis.model.Conversation;
import com.noturingback.gladis.model.Granule;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by vrong on 02/12/16.
 */

public class UnknownRequest extends RequestType
{
	Context context;
	String fileName = "SimpleTypeGranules.json";

	public UnknownRequest(Context c)
	{
		super();
		context = c;
	}

	@Override
	public ConvMessage respondTo(String entry, Conversation conv)
	{

		String[] words = splitRequest(entry);
		HashMap<Granule, Float> values = new HashMap<>();

		float max = 0;
		Granule bestGranule = null;
		List<Granule> granules = GranuleParser.parseGranulesFromFile("UnknownTypeGranules.json", this.context);
		for (Granule g: granules)
		{
			values.put(g, 0f);
			for (String w : words )
			{
				for(String key : g.getKeywords())
				{
					if((key.contains(w) && w.length() > 3) || key.equals(w))
					values.put(g, values.get(g) + 1);
				}

			}
			values.put(g, values.get(g)/(float)g.getOptimalKeywords());
			if(values.get(g) >= max)
			{
				max = values.get(g);
				bestGranule = g;
			}
		}

		//Sélection d'une réponse
		int ri = new Random().nextInt(bestGranule.getAnswers().size());

		String answer = bestGranule.getAnswers().get(ri);
		ConvMessage m = new ConvMessage(ConvMessage.Author.Robot, answer);
		return m;
	}




	@Override
	public float estimateMatching(String entry, Conversation conv)
	{
		String[] words = splitRequest(entry);
		HashMap<Granule, Float> values = new HashMap<>();

		float max = 0;
		List<Granule> granules = GranuleParser.parseGranulesFromFile("SimpleTypeGranules.json", this.context);
		for (Granule g: granules)
		{
			values.put(g, 0f);
			for (String w : words )
			{
				if(g.getKeywords().contains(w.toUpperCase()))
					values.put(g, values.get(g) + 1);
			}
			values.put(g, values.get(g)/(float)g.getOptimalKeywords());
			if(values.get(g) >= max)
			{
				max = values.get(g);
			}
		}
		return max;
	}
}
