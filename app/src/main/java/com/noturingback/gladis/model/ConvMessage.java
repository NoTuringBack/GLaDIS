package com.noturingback.gladis.model;

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
	Image image;
	String text;
}
