/* **************************************************
 Copyright (c) 2012, University of Cambridge
 Neal Lathia, neal.lathia@cl.cam.ac.uk
 Kiran Rachuri, kiran.rachuri@cl.cam.ac.uk

This library was developed as part of the EPSRC Ubhave (Ubiquitous and
Social Computing for Positive Behaviour Change) Project. For more
information, please visit http://www.emotionsense.org

Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted, provided that the above
copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 ************************************************** */

package com.ubhave.triggermanager.triggers.clockbased;

import java.util.Calendar;

import android.content.Context;

import com.ubhave.triggermanager.TriggerException;
import com.ubhave.triggermanager.TriggerReceiver;
import com.ubhave.triggermanager.config.Constants;

public class OneTimeTrigger extends ClockTrigger
{
	private final Calendar surveyDate;

	public OneTimeTrigger(Context context, TriggerReceiver listener, Calendar surveyDate) throws TriggerException
	{
		super(context, listener);
		this.surveyDate = surveyDate;
		initialise();
	}
	
	protected void initialise() throws TriggerException
	{
		long waitTime = surveyDate.getTimeInMillis() - System.currentTimeMillis();
		if (waitTime > 0)
		{
			surveyTimer.schedule(new SurveyNotification(), waitTime);
		}
		else if (Constants.TEST_MODE)
		{
			throw new TriggerException(TriggerException.DATE_IN_PAST, "Scheduled time is in the past: "+surveyDate.getTime().toString());
		}
	}
}