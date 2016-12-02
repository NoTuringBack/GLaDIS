package com.noturingback.gladis.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.noturingback.gladis.R;
import com.noturingback.gladis.model.ConvMessage;

import java.util.List;

/**
 * Created by vrong on 01/12/16.
 */

public class ConversationAdapter extends ArrayAdapter<ConvMessage>
{
	List<ConvMessage> messages;

	public ConversationAdapter(Context context, int resource, List<ConvMessage> messages)
	{
		super(context, resource, messages);
		this.messages = messages;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ListView listview = (ListView)parent;
		MessageHolder holder = new MessageHolder(messages.get(position));
		if(convertView == null)
		{
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.message_item_list, null);
		}
		holder.layout = (RelativeLayout)convertView.findViewById(R.id.message_parent_layout);
		//holder.layout = (RelativeLayout)convertView;
		holder.textView = (TextView)convertView.findViewById(R.id.text_message);
		holder.imageView = (ImageView)convertView.findViewById(R.id.image_message);
		holder.authorView = (TextView)convertView.findViewById(R.id.author_message);
		holder.contentLayout = (RelativeLayout)convertView.findViewById(R.id.content);

		if(holder.message.getAuthor() == ConvMessage.Author.Robot) //change style whether it's a robot's quote or human's
		{
			holder.contentLayout.setBackgroundColor(convertView.getResources().getColor(R.color.robot));
			holder.authorView.setBackgroundColor(convertView.getResources().getColor(R.color.robot));
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.authorView.getLayoutParams();
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
			params = (RelativeLayout.LayoutParams)holder.layout.getLayoutParams();
			params.setMargins(0, 0, (int)convertView.getContext().getResources().getDimension(R.dimen.message_author_margin), 0);
		}
		else
		{
			holder.contentLayout.setBackgroundColor(convertView.getResources().getColor(R.color.human));
			holder.authorView.setBackgroundColor(convertView.getResources().getColor(R.color.human));
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.authorView.getLayoutParams();
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params = (RelativeLayout.LayoutParams) holder.layout.getLayoutParams();
			params.setMargins((int)convertView.getContext().getResources().getDimension(R.dimen.message_author_margin), 0, 0, 0);
		}
		if(holder.message.getImage() == null)
		{
			holder.imageView.setVisibility(View.GONE);
		}
		else
		{
			holder.imageView.setImageBitmap(holder.message.getImage());
		}

		holder.textView.setText(holder.message.getText());
		holder.authorView.setText(holder.message.getAuthor() == ConvMessage.Author.Robot ?
				parent.getContext().getString(R.string.gladis) : parent.getContext().getString(R.string.vous));

		if(holder.newMessage)
		{
			listview.smoothScrollToPosition(position);
			holder.newMessage = false;
		}

		return convertView;
	}



	public class MessageHolder
	{
		ConvMessage message;

		RelativeLayout layout;
		ImageView imageView;
		TextView textView;
		TextView authorView;
		RelativeLayout contentLayout;

		boolean newMessage = true;

		public MessageHolder(ConvMessage convMessage)
		{
			this.message = convMessage;
		}
	}


}
