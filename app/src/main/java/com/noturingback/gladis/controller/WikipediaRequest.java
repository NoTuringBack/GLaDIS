package com.noturingback.gladis.controller;

import android.os.AsyncTask;

import com.noturingback.gladis.model.ConvMessage;
import com.noturingback.gladis.model.Conversation;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vrong on 02/12/16.
 */

public class WikipediaRequest extends RequestType
{
	private final String keywords = "QUI EST ";
	Runnable callback;

	public WikipediaRequest(Runnable callback)
	{
		this.callback = callback;
	}

	@Override
	public float estimateMatching(String entry, Conversation conv)
	{
		if(entry.toUpperCase().startsWith(keywords.toUpperCase()))
		{
			return 10;
		}
		else return 0;
	}

	@Override
	public ConvMessage respondTo(String entry, Conversation conv)
	{
		if(entry.toUpperCase().startsWith(keywords))
		{
			String search = entry.substring(keywords.length(), entry.length());
			search = search.trim();
			search = search.replace(' ', '_');


		}
		else
		{
			return new ConvMessage(ConvMessage.Author.Robot, "Je n'ai rien trouvé désolé :/");
		}

		return null;
	}

	public synchronized String readStream(InputStream in)
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(in));
		StringBuilder total = new StringBuilder();
		String line;
		try
		{
			while ((line = r.readLine()) != null) {
				total.append(line).append('\n');
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		String page = total.toString();

		return null;
	}

	protected abstract class DownloadWikiPage extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... names)
		{
			for (String name : names) {
				HttpURLConnection urlConnection;
				try {
					URL url = new URL("http://fr.wikipedia.org/wiki/" + name);
					urlConnection = (HttpURLConnection) url.openConnection();
					InputStream in = new BufferedInputStream(urlConnection.getInputStream());
					readStream(in);
					urlConnection.disconnect();
				}
				catch (Exception e)
				{

				}
			}

			return null;
		}


		protected void onPostExecute(String result)
		{

		}
	}

}
