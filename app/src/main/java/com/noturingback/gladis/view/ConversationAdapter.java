package com.noturingback.gladis.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.noturingback.gladis.model.ConvMessage;

import java.util.List;

/**
 * Created by vrong on 01/12/16.
 */

public class ConversationAdapter extends ArrayAdapter<ConvMessage>
{


	public ConversationAdapter(Context context, int resource, List<ConvMessage> messages)
	{
		super(context, resource, messages);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		return null;
	}



	public class MessageHolder
	{
		ConvMessage message;

		RelativeLayout layout;

	}


}
