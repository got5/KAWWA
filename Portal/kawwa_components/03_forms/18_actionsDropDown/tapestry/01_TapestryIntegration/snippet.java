import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.atos.kawwaportal.components.KawwaEventsConstants;

import org.apache.tapestry5.annotations.OnEvent;

public class ActionsDropDown {
	
	public Map<String, List<String>> getItems(){
		Map<String, List<String>> items = new TreeMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		list.add("Option1");
		list.add("Option2");
		list.add("Option3");
		
		items.put("CardHolder", list);
		items.put("Card Account", list);
		items.put("Plastic", list);
				
		
		return items;
	}
	
	@OnEvent(value = KawwaEventsConstants.ACTIONSDROPDOWN_SELECT)
	public void getEvent(String parent, String child){
		System.out.println(parent + " "  + child);
	}
}

