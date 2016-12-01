package com.noturingback.gladis.controller;

import com.noturingback.gladis.model.ConvMessage;
import com.noturingback.gladis.model.Conversation;

/**
 * Created by vrong on 01/12/16.
 */

public abstract class RequestType
{
	public abstract int estimateMatching(String entry, Conversation conv);
	public abstract ConvMessage respondTo(String entry, Conversation conv);
}
