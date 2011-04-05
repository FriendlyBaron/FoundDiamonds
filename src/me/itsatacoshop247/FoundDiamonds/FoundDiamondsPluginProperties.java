package me.itsatacoshop247.FoundDiamonds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoundDiamondsPluginProperties extends Properties{
static final long serialVersionUID = 0L;
private static final Logger log = Logger.getLogger("Minecraft");
private String fileName;
private Properties properties;
protected FoundDiamonds plugin;

public FoundDiamondsPluginProperties(String file) {
	this.fileName = file;
	//this.plugin = plugin;
	this.properties = new Properties();
}
public void load() {
	File file = new File(this.fileName);
	if(file.exists()) {
	try {
		load(new FileInputStream(this.fileName));
	} catch (IOException ex) {
		ex.printStackTrace();
	}
	}
}
public void save(String start) {
	try {
		store(new FileOutputStream(this.fileName), start);
	} catch (IOException ex) {
		ex.printStackTrace();
	}
}
public void save() {
	try {
		FileOutputStream outFile = new FileOutputStream(this.fileName);
		this.properties.store(outFile, "Minecraft Properties File");
		outFile.close();
	} catch (IOException ex) {
		log.log(Level.SEVERE, "[" + plugin.pName + "]: Unable to save "+this.fileName, ex);
	}
}
public int getInteger(String key, int value){
	if(containsKey(key)){
		return Integer.parseInt(getProperty(key));
	}
	put(key, String.valueOf(value));
	return value;
}
public boolean getBoolean(String key, boolean value) {
	if (containsKey(key)) {
		return Boolean.parseBoolean(getProperty(key));
	} else {
		put(key, String.valueOf(value));
	}
	return value;
}
public String getString(String key, String value) {
	if (containsKey(key)) {
		return getProperty(key);
	}

	put(key, value);
	return value;
}
}