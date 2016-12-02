package com.noturingback.gladis.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.noturingback.gladis.R;
import com.noturingback.gladis.model.ConvMessage;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.appcompat.R.styleable.View;

public class MainActivity extends AppCompatActivity {

	ListView messageListView;
	EditText entryEdit;

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

		messageListView.setAdapter(new ConversationAdapter(this, R.layout.message_item_list, list));
    }

	public void buttonSendAction(View view)
	{

	}

	public void buttonVoiceAction(View view)
	{

	}


}
