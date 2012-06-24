package com.util;

public class WebEnumeration extends SimpleEnumeration<String> {

	public WebEnumeration(String... array) {
		super(array);
	}

	public String nextElementEx() {
		return StringUtil.stripHTML(nextElement());
	}

}
