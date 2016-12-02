package com.noturingback.gladis.model;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by vrong on 01/12/16.
 */

public class ConvMessage
{
	public enum Author{
		Human, Robot
	}
	Author author;
	Bitmap image = null;
	String text;


	public ConvMessage(Author aut, String text)
	{
		this.author = aut;
		this.text = text;
	}

	public ConvMessage(Author aut, String text, Bitmap bitmap)
	{
		this(aut, text);
		this.image = bitmap;
	}

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	public Bitmap getImage()
	{
		return image;
	}

	public void setImage(Bitmap image)
	{
		this.image = image;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}
}
