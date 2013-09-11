package net.atos.kawwaportal.components.data;

/**
 * Class used by the ActionsDropDown component
 * @author Emmanuel DEMEY
 * @see net.atos.kawwaportal.components.components.ActionsDropDown
 */
public class ActionsDropDownItem{

    /**
     * The label of the action.
     */
    private String item;

    /**
     * The category of the action. If setted, will be displayed in "p" tag.
     */
    private String category;

    public ActionsDropDownItem(String item) {
        this(item, null);
    }

    public ActionsDropDownItem(String item, String category) {
        this.item = item;
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
