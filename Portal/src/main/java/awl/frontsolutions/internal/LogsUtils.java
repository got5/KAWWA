package awl.frontsolutions.internal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LogsUtils {

	public static File getFile(String prefix, String componentFolder)
    {
		Calendar now = Calendar.getInstance();

		File file = new File(componentFolder + File.separatorChar + "logs" + File.separatorChar + prefix+"_"+
    			new SimpleDateFormat("yyyy").format(new Date()) + "_" +
    			now.get(Calendar.WEEK_OF_YEAR) + ".csv");

		if(!file.exists()){
    		try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
    	}

    	return file;
    }

	public static void log(File file, String value){

		List<String> list = new ArrayList<String>();

		FileReader fr;
		try {
			fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);

	        String tmp;

	        list.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+","+value);

	        while ((tmp = reader.readLine()) != null ) {
	        	if(!list.contains(tmp)) list.add(tmp);
	        }

	        fr.close();

	        FileWriter fw = new FileWriter(file);
	        BufferedWriter writer = new BufferedWriter(fw);
	        for (int i = 0; i < list.size(); i++){
	        	writer.write((String) list.get(i));
	        	writer.newLine();
		    }
	        writer.close();
	        fw.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}
    }
}
