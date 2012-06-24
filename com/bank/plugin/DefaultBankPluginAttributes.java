package com.bank.plugin;

import com.util.PropertiesEx;

public class DefaultBankPluginAttributes extends PropertiesEx implements
		PluginAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4849537674247563791L;

	public DefaultBankPluginAttributes() {
		super();
		setProperty("Name", "null");
		setProperty("Description", "null");
		setProperty("Author", "null");
		setProperty("ImageURL", "null");
		setProperty("SourceURL", "null");
	}

	@Override
	public void setName(String name) {
		setProperty("Name", name);
	}

	public void setDescription(String description) {
		setProperty("Description", description);
	}

	public void setAuthor(String author) {
		setProperty("Author", author);
	}

	public void setImageURL(String imageURL) {
		setProperty("ImageURL", imageURL);
	}

	public void setSourceURL(String sourceURL) {
		setProperty("SourceURL", sourceURL);
	}

	@Override
	public String getName() {
		return getProperty("Name");
	}

	@Override
	public String getDescription() {
		return getProperty("Description");
	}

	@Override
	public String getAuthor() {
		return getProperty("Author");
	}

	@Override
	public String getImageURL() {
		return getProperty("ImageURL");
	}

	@Override
	public String getSourceURL() {
		return getProperty("SourceURL");
	}

}
