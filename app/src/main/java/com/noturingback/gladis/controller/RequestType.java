package com.noturingback.gladis.controller;

import com.noturingback.gladis.model.ConvMessage;
import com.noturingback.gladis.model.Conversation;

/**
 * Created by vrong on 01/12/16.
 */

public abstract class RequestType
{
	public abstract float estimateMatching(String entry, Conversation conv);
	public abstract ConvMessage respondTo(String entry, Conversation conv);

	protected String[] splitRequest (String entry)
	{
		String[] tab = entry.split(" ");

		//standardize to lower cases
		for (int i = 0; i < tab.length; i++)
		{
			tab[i] = tab[i].toUpperCase();
		}
		return tab;
	}
}
