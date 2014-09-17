package net.atos.kawwaportal.components.test.pages;

import java.util.*;

import net.atos.kawwaportal.components.KawwaEventsConstants;

import net.atos.kawwaportal.components.data.ActionsDropDownItem;
import org.apache.tapestry5.annotations.OnEvent;

public class ActionsDropDown {
    public List<ActionsDropDownItem> getItems(){
        List<ActionsDropDownItem> item = new ArrayList<ActionsDropDownItem>();
        item.add(new ActionsDropDownItem("Option1", "CardHolder"));
        item.add(new ActionsDropDownItem("Option2", "CardHolder"));
        item.add(new ActionsDropDownItem("Option3", "CardHolder"));

        item.add(new ActionsDropDownItem("Option1", "Card Account"));
        item.add(new ActionsDropDownItem("Option2", "Card Account"));
        item.add(new ActionsDropDownItem("Option3", "Card Account"));

        item.add(new ActionsDropDownItem("Option1", "Plastic"));
        item.add(new ActionsDropDownItem("Option2", "Plastic"));
        item.add(new ActionsDropDownItem("Option3", "Plastic"));
        return item;
    }


    @OnEvent(value = KawwaEventsConstants.ACTIONSDROPDOWN_SELECT)
    public void getEvent(String parent, String child){
        System.out.println(parent + " "  + child);
    }
}
