package awl.frontsolutions.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;

import awl.frontsolutions.internal.KawwaPortalConstants;
import awl.frontsolutions.pages.Component;
import awl.frontsolutions.treeDescription.TreeNode;

public class Breadcrumb {

    @Parameter
    private String page;

	@Inject
	private ComponentResources resources;
	
	@InjectPage
	private Component component;
	
	public String getPageName(){

        if(resources.isBound("page")) return page;

		String breadcrumb = resources.getPageName();
		
		if(breadcrumb.equalsIgnoreCase(KawwaPortalConstants.COMPONENT_PAGE)){
			StringBuilder sb = new StringBuilder(component.getComponentInfo().getNodeName());
			TreeNode parent = component.getComponentInfo().getParent();
			while(parent!=null){
				sb.insert(0,parent.getNodeName()+" : ");
				parent = parent.getParent();
			}
			breadcrumb = sb.toString();
		}
		
		return breadcrumb;
	}
	
	public boolean getShowComponents(){
		return resources.getPageName().equalsIgnoreCase(KawwaPortalConstants.COMPONENT_PAGE);
	}

    public boolean getShowAboutKawwa(){
        String pageName = resources.getPageName();
        return pageName.equalsIgnoreCase("HowTo") || pageName.equalsIgnoreCase("Accessibility") ||
                pageName.equalsIgnoreCase("Tapestry") || pageName.equalsIgnoreCase("Angular") ||
                pageName.equalsIgnoreCase("IeFix") || pageName.equalsIgnoreCase("UnobstrusiveApproach") ||
                pageName.equalsIgnoreCase("KawwaPortal") || pageName.equalsIgnoreCase("Releases") ||
                pageName.equalsIgnoreCase("ComponentApproach");


    }
}