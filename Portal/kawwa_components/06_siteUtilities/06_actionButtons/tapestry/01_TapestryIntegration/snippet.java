import java.util.ArrayList;
import java.util.List;

import net.atos.kawwaportal.components.data.ListOfActionsItem;

public class ListOfActions {
	
	public List<ListOfActionsItem> getItems(){
		List<ListOfActionsItem> items = new ArrayList<ListOfActionsItem>();
		
		items.add(new ListOfActionsItem("print", "#", "bt-print", false));
		items.add(new ListOfActionsItem("download", "#", "bt-download", false));
		items.add(new ListOfActionsItem("edit", "#", "bt-edit", false));
		items.add(new ListOfActionsItem("view", "#", "bt-view", false));
		items.add(new ListOfActionsItem("email", "#", "bt-email", false));
		return items;
	}
}


