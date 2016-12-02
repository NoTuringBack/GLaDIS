package com.noturingback.gladis.controller;

import android.content.Context;

import com.noturingback.gladis.model.ConvMessage;
import com.noturingback.gladis.model.Conversation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrong on 02/12/16.
 */

public class Controller
{
	private Conversation conversation = new Conversation();
	private Context context;
	private List<RequestType> requests = new ArrayList<>();

	public Controller(Context context)
	{
		this.context = context;
		requests.add(new BlagueRequest(context));
		requests.add(new SimpleRequest(context));
		requests.add(new UnknownRequest(context));
	}

	public void newMessage(String message)
	{
		conversation.addMessage(new ConvMessage(ConvMessage.Author.Human, message));
	}

	public ConvMessage askAnswer()
	{
		ConvMessage m = conversation.getLastHumanMessage();

		float max = 0;
		RequestType request = null;

		for(int i = 0 ; i < requests.size(); i++)
		{
			float temp = requests.get(i).estimateMatching(m.getText(), conversation);
			if(temp >= max)
			{
				max = temp;
				request = requests.get(i);
			}
		}

		ConvMessage answer = request.respondTo(m.getText(), conversation);

		getConversation().addMessage(answer);
		return answer;

	}

	public Conversation getConversation()
	{
		return conversation;
	}
}
