package com.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class SampleCart implements EntryPoint {

	public void onModuleLoad() {
		SampleCartComposite simpleCart = new SampleCartComposite();
		RootPanel.get("testContainer").add(simpleCart);
	}

}
