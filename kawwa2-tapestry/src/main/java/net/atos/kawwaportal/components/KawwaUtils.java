package net.atos.kawwaportal.components;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.internal.util.MessagesImpl;

public class KawwaUtils {
	
	/**
	 * This method will return the value of a Key. If the key is not specified
	 * in one of the Client's Bundle, We will use the Kawwa default bundle.
	 */
	
	public static String getMessages(String key, Messages messages, Locale l){
		
		String message = new String();
		
		try{
			message = new MessagesImpl(l, ResourceBundle.getBundle("net.atos.kawwaportal.components.KawwaStrings")).get(key);
		}
		catch (MissingResourceException e) {
			
		}
		catch (NullPointerException e) {
			
		}
		
		if(messages.contains(key)) message=messages.get(key);
		
		return message;
		
	}
	
	public static String camelCase(String... args) {
		if (args == null || args.length == 0) {
			return null;
		}
		StringBuilder buf = new StringBuilder();
		buf.append(args[0]);
		for (int i = 1; i < args.length; i++) {
			String arg = args[i];
			if (arg != null && arg.length() > 0) {
				buf.append(Character.toUpperCase(arg.charAt(0)));
				buf.append(arg.substring(1));
			}
		}
		return buf.toString();
	}
}	
