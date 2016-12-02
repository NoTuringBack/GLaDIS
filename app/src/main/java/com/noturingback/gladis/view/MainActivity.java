package com.noturingback.gladis.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.noturingback.gladis.R;
import com.noturingback.gladis.controller.Controller;
import com.noturingback.gladis.model.ConvMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	ListView messageListView;
	EditText entryEdit;
	Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		messageListView = (ListView)findViewById(R.id.message_listview);
		entryEdit = (EditText)findViewById(R.id.message_edit);

		List<ConvMessage> list = new ArrayList<>();
		list.add(new ConvMessage(ConvMessage.Author.Robot, "Bonjour"));
		list.add(new ConvMessage(ConvMessage.Author.Human, "Comment ça va ?"));
		list.add(new ConvMessage(ConvMessage.Author.Robot, "Bien et toi ?"));
		list.add(new ConvMessage(ConvMessage.Author.Human, "Nickel"));
		list.add(new ConvMessage(ConvMessage.Author.Robot, "Bonjour"));
		list.add(new ConvMessage(ConvMessage.Author.Human, "Comment ça va ?"));
		list.add(new ConvMessage(ConvMessage.Author.Robot, "Bien et toi ?"));
		list.add(new ConvMessage(ConvMessage.Author.Human, "Nickel"));
		list.add(new ConvMessage(ConvMessage.Author.Robot, "Bonjour"));
		list.add(new ConvMessage(ConvMessage.Author.Human, "Comment ça va ?"));
		list.add(new ConvMessage(ConvMessage.Author.Robot, "Bien et toi ?"));
		list.add(new ConvMessage(ConvMessage.Author.Human, "Nickel"));

		controller = new Controller(this);

		messageListView.setAdapter(new ConversationAdapter(this, R.layout.message_item_list, controller.getConversation().getMessages()));
    }

	public void buttonSendAction(View view)
	{
		String s = entryEdit.getText().toString();
		if(s.isEmpty())
			return;
		entryEdit.setText("");
		controller.newMessage(s);
		controller.askAnswer();
		((ConversationAdapter)messageListView.getAdapter()).notifyDataSetChanged();
		messageListView.smoothScrollToPosition(controller.getConversation().getMessages().size());
	}

	public void buttonVoiceAction(View view)
	{

	}


}
