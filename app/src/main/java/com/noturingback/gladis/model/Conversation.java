package com.noturingback.gladis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrong on 02/12/16.
 */

public class Conversation
{
	List<ConvMessage> messages;

	public Conversation()
	{
		messages = new ArrayList<ConvMessage>();
	}

	public void addMessage (ConvMessage cm)
	{
		messages.add(cm);
	}

	public List<ConvMessage> getMessages ()
	{
		return messages;
	}

	public ConvMessage getLastHumanMessage()
	{
		for(int i = getMessages().size()-1; i >= 0 ; i--)
		{
			if(getMessages().get(i).getAuthor() == ConvMessage.Author.Human)
				return getMessages().get(i);
		}
		return null;
	}

}
