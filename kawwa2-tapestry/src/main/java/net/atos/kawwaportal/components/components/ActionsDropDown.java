package net.atos.kawwaportal.components.components;

import java.util.List;
import net.atos.kawwaportal.components.KawwaEventsConstants;
import net.atos.kawwaportal.components.KawwaUtils;

import net.atos.kawwaportal.components.data.ActionsDropDownItem;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * The Actions dropDown component is an easy way to create a drop down list of actions. You just need to
 * give a list of ActionsDropDownItem objects to this component.
 *
 * By default, an eventLink will be displayed, triggering a KawwaEventsConstants.ACTIONSDROPDOWN_SELECT event.
 * You can catch this event, and the corresponding method will have one or two parameters : the category (if setted) and
 * the selected item (formatted in a camel-case way).
 *
 * You can override the rendering of each item, by defining a block parameter. The name of block should follow
 * this rule : (category formatted in a camel-case way)_(item formatted in a camel-case way)  or (item formatted in a camel-case way)
 * if the category is null.
 *
 * @author Emmanuel DEMEY
 * @tapestrydoc
 * @component_version 1.0    
 *
 * @see net.atos.kawwaportal.components.data.ActionsDropDownItem
*/
@Events({KawwaEventsConstants.ACTIONSDROPDOWN_SELECT})
@SupportsInformalParameters
@Import(library = {"classpath:/net/atos/kawwaportal/components/assets/actiondropdown/jquery.kawwa.actionsDd.js",
		"classpath:/net/atos/kawwaportal/components/assets/actiondropdown/actionsdropdown.js"})
public class ActionsDropDown {
	
	/**
	 * The title of your dropdown menu
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, value="message:actionsdropdown_title")
	private String title;
	
	/**
	 * The List of items your dropdown menu should display. 
	 */
	@Property
	@Parameter
	private List<ActionsDropDownItem> items;

    /**
     * id, setted in the setupRender phase, and associated to the div element containing the dropdown menu
     */
	@Property
	private String id;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
    private ComponentResources resources;

    /**
     * The current item
     */
	@Property private ActionsDropDownItem item;

    /**
     * The default block to display for each item
     */
	@Inject private Block defaultBlock;

    /**
     * Store the previous category, in order to know if we have or not to display a new "p" element
     */
    private String previousCategory;

    /**
     * In the setupRender phase, we generate the id attribute, and add the main div element,
     * and the button used to display/hide the dropdown menu.
     * @param writer
     */
	@SetupRender
	void setupRender(MarkupWriter writer) {
        previousCategory = null;
		id = javaScriptSupport.allocateClientId(resources);

        writer.element("div", "id", id, "class", "k-actions-dropdown", "role", "listbox");
        writer.element("button", "aria-haspopup", "true");
        writer.write(title);
        writer.end();

        writer.element("div",  "class", "content");
	}

    /**
     * We display a "p" element if necessary, and the corresponding opening "ul"
     * @param writer
    */
	@BeginRender
	void beginRender(MarkupWriter writer){
        item = items.remove(0);
        if(!isNotNewCategegory()){
            if(writer.getElement().getName().equalsIgnoreCase("ul")){
                writer.end();
            }

            writer.element("p");
            writer.write(item.getCategory());
            writer.end();


        }
        if(!writer.getElement().getName().equalsIgnoreCase("ul")){
            writer.element("ul");
        }
    }

    /**
     * We add the opening "li" element, with its role HTML attribute.
     * @param writer
     */
    @BeforeRenderTemplate
    void insertMainHtmlStructure(MarkupWriter writer){

        writer.element("li");
        writer.attributes("role", "option");
    }

    /**
     * We get the block to display (the default one or the the one provided by the developer
     * @return the block to displayed
     */
    @AfterRenderTemplate
    Block getItemBlock(){
        Block override = resources.getBlockParameter(String.format("%s", getCamelItem()));
        if(InternalUtils.isNonBlank(item.getCategory()))
            override = resources.getBlockParameter(String.format("%s_%s", getCamelCategory(), getCamelItem()));

        return (override != null) ? override : defaultBlock;

    }

    /**
     * We close the "li" element. and if the list is not empty, we execute again the beginRender phase.
     * @param writer
     * @return either the list is empty or not.
     */
    @AfterRender
    Boolean afterRender(MarkupWriter writer){
        writer.end();
        previousCategory = item.getCategory();
        return items.isEmpty();
    }

    /**
     * We close all the opened elements, and call the javascript method
     * @param writer
     */
	@CleanupRender
    void cleanUpRender(MarkupWriter writer) {

        if(writer.getElement().getName().equalsIgnoreCase("ul")){
            writer.end();
        }

		writer.end();
		writer.end();
    	JSONObject option = new JSONObject();
    	option.put("id", id);
	    
    	javaScriptSupport.addInitializerCall("actionsdropdown", option);
    }

    /**
     * @return Does the current item is associated to a new/or blank category ?
     */
	private Boolean isNotNewCategegory(){
        boolean ret = false;
        if(InternalUtils.isBlank(item.getCategory())) ret = true;
        else ret = item.getCategory().equalsIgnoreCase(previousCategory);

        return ret;
    }

    /**
     * @return The category in a camel case
     */
	private String getCamelCategory(){
		return KawwaUtils.camelCase(item.getCategory().split("\\s+"));
	}

    /**
     * @return The item in a camel case
     */
    private String getCamelItem(){
		return KawwaUtils.camelCase(item.getItem().split("\\s+"));
	}

    /**
     * @return the url used by the default block.
     */
	public String getUrl(){
        if(InternalUtils.isBlank(item.getCategory()))
		    return resources.createEventLink(KawwaEventsConstants.ACTIONSDROPDOWN_SELECT, getCamelItem()).toAbsoluteURI();

        return resources.createEventLink(KawwaEventsConstants.ACTIONSDROPDOWN_SELECT, getCamelItem(),getCamelCategory()).toAbsoluteURI();
	}
	
}
