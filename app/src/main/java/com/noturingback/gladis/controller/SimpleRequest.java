package com.noturingback.gladis.controller;

import android.content.Context;

import com.noturingback.gladis.model.ConvMessage;
import com.noturingback.gladis.model.Conversation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by vrong on 02/12/16.
 */

public class SimpleRequest extends RequestType
{
	Context context;
	String fileName = "database.json";

	public SimpleRequest(Context c)
	{
		super();
		context = c;
	}

	@Override
	public ConvMessage respondTo(String entry, Conversation conv)
	{
		//TODO si le mec dit "bonsoir", répondre un truc qui correspond genre "salut" ?
		//TODO On peut créer un fichier (json ? xml ? autre ? ) pour chaque type de demande qui regroupe les mot-clés et les réponses possibles
		//TODO Une fois qu'on a cerné le type d'échange avec les fichiers, choisir une réponse aléatoire parmi celles disponibles pour ce type d'échange
		/*
			type1 : bonsoir.json
				motclés : bonsoir, salut, bonjour, ...
				réponses possibles : hey, bonsoir, salut, ...

			type2 : Commentcava.json
				motclés : comment, ça, va
				réponses possbiels : tranquille et toi, nickel, ...

			Etape 1 :
				On reçoit "comment ça va ?"
					On test le database.json : compare avec type1 peu de correspondance avec les mot-clés.
					On test le database.json: compare avec type 2 Whaouh ça match de ouf du coup on prend celui là.
			Etape 2 : On choisit une réponse aléatoire dans celles proposées dans le fichier
			Etape 3 : On crée un objet ConvMessage avec la réponse et on la renvoie.

		 */
		String typeOfEntry = estimateGlobalMatching(entry, conv);
		String answer = getAnswer(typeOfEntry);
		System.out.println(answer);
		return null;
	}


	@Override
	public String estimateGlobalMatching(String entry, Conversation conv)
	{
		int a = 0;
		int b = 0;
		String r;
		String[] input = splitRequest(entry);
		//test the matching
		for (int i = 0; i < input.length; i++ ) {
			a = a + estimateMatching('A', input[i], conv);
			b = b + estimateMatching('B', input[i], conv);
		}
		if (a > b)
			r = "A";
		else
			r = "B";
		return r;
	}

	public String[] splitRequest (String entry)
	{
		String[] tab = entry.split(" ");

		//standardize to lower cases
		for (int i = 0; i < tab.length; i++)
		{
			tab[i] = tab[i].toLowerCase();
		}
		return tab;
	}


	public int estimateMatching(char c, String entry, Conversation conv) {
		int a = 0;
		JSONArray feedbacks = null;
		try {
			feedbacks = parseJson("keyword");
			for (int i = 0; i < feedbacks.length(); i++)
			{
				if (entry.equals(feedbacks.getString(i)))
					a ++;
			}
		}catch (JSONException je) {
			//log the exception
		}
		return a;
	}

	public String getAnswer (String type)
	{
		String result = null;
		int number = 0;
		Random r = new Random();
		JSONArray feedbacks = null;

		try {
			feedbacks = parseJson(type);
			number = r.nextInt(feedbacks.length()-1);
			result = feedbacks.getString(number);
		}catch (JSONException je) {
			//log the exception
		}
		return result;
	}

	//Parse json and send true if the input has a match in the type field
	public JSONArray parseJson (String title)
	{
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		String result = null;
		JSONArray keywords = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(context.getAssets().open(fileName)));

			// do reading, usually loop until end of file reading
			String mLine;
			while ((mLine = reader.readLine()) != null) {
				//process line
				sb.append(mLine + "\n");
			}
			result = sb.toString();
			JSONObject jsonObject = new JSONObject(result);
			keywords = (JSONArray) jsonObject.get(title);

		} catch (IOException e) {
			//log the exception
		}catch (JSONException je) {
			//log the exception
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					//log the exception
				}
			}
		}
		return keywords;
	}
}
