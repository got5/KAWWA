package net.atos.kawwaportal.components.components;

import java.util.List;

import net.atos.kawwaportal.components.data.ListOfActionsItem;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;

/**
 * 
 * Components used to display a list of links. It will implement 3 Kawwa components : ActionButtons, ActionButtonsBar and iconButtons
 *  
 * @see <a href="https://kawwa.atosworldline.com/component/actionButtons">actionButtons</a>
 * @see <a href="https://kawwa.atosworldline.com/component/actionButtonsBar">ActionButtonsBar</a>
 * @see <a href="https://kawwa.atosworldline.com/component/iconButtons">iconButtons</a>
 * @author Emmanuel DEMEY
 * @tapestrydoc
 *
 */
@SupportsInformalParameters
public class ListOfActions {
	
	/**
	 * Actions you want to display with this component. You will need to create a List of ListOfActionsItem. 
	 * If the onlyIcon parameter is set to true, every ListOfActionsItem item should have a value for its classe attribute.
	 */
	@Parameter(required=true)
	private List<ListOfActionsItem> items;
	
	/**
	 * If set to true, the list of actions will be surround by a bar. 
	 * @see <a href="https://kawwa.atosworldline.com/component/actionButtonsBar">ActionButtonsBar</a>
	 */
	@Parameter(required=false, value="false")
	private Boolean withBar;
	
	/**
	 * If set to true, your list will display only the icon of each item. The label associated to your link will be hidden. 
	 * @see <a href="https://kawwa.atosworldline.com/component/iconButtons">iconButtons</a> 
	 */
	@Parameter(required=false, value="false")
	private Boolean onlyIcon;
	
	@Property
	private ListOfActionsItem current;
	
	@Inject
	private Block block;
	
	@Inject
	private Block itemBlock;
	
	@Inject
	private ComponentResources cr;
	
	@Inject 
	private Messages messages;
	
	@SetupRender
	public void setupRender(){
		
		List<ListOfActionsItem> withoutClass = F.flow(items).filter(new Predicate<ListOfActionsItem>() {

			public boolean accept(ListOfActionsItem element) {
				return InternalUtils.isBlank(element.getClasse());
			}
		}).toList();
		
		if(withoutClass.size() > 0){
			throw new RuntimeException("When onlyIcon parameter is set to true, all your ListOfActionsItem should have a non-empty Classe attribute");
		}
	}

	public List<ListOfActionsItem> getItems() {
		return items;
	}

	public Boolean getWithBar() {
		return withBar;
	}

	public Boolean getOnlyIcon() {
		return onlyIcon;
	}
	
	public String getCssClass(){
		return onlyIcon ? "actions" : "k-actions";
	}
	
	public Block getBlock(){
		return block;
	}
	
	public Block getItemBlock(){
		Block override = cr.getBlockParameter(current.getId());
		return override == null ? itemBlock : override;
	}
	public String getBarTitle(){
		Messages containerMessages = cr.getContainerMessages();
		
		String key = String.format("%s-barTitle", cr.getId());
		if(containerMessages.contains(key))
			return containerMessages.get(key);
		else return  messages.get("barTitle-default");
	}
}
