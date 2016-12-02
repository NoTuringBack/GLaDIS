package com.noturingback.gladis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrong on 02/12/16.
 */

public class Conversation
{
	List<ConvMessage> messages;

	//TODO tout ce qui est relatif à la conversation en cours
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
}
