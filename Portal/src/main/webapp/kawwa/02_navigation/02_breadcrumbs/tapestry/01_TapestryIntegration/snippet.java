//Here is how you can create your own BreadCrumb

//You first have to implement the following interface.

public interface BreadcrumbListProvider {
	/* This method is called within the breadcrumb component.
	 * Parameters :
	 * - home : the first element of the breadcrumb (generally the home page of your application), 
	 * this is the home parameter of the breadcrumb component 
	 * - resources : the componentResources service associated with the current Breadcrumb component
	 * This method has to return a  List<String[]>, with :
	 * - String[0] is the page name (to create the pagelink)
	 * - String[1] is the text to be displayed
	 */
	List<String[]> getBreadcrumbList (String home, ComponentResources resources);
}

//Then you have to contribute your implementation to the BreadcrumbListProviderSource.
//You just have to give an id and the class of your implementation.

@Contribute(BreadcrumbListProviderSource.class)
public static void addingYourkageBasedBreadcrumbListProvider(MappedConfiguration<String, BreadcrumbListProvider> configuration)
{
	configuration.addInstance("TheIdOfYourBreadcrumbListProvider", YourBreadcrumbListProvider.class);
}

//The id is important, because if you want to use your own implementation, 
//you will have to specifiy it when adding the component into your page : 
<t:kawwa.breadcrumb t:home="Index" t:breadcrumbProvider="TheIdOfYourBreadcrumbListProvider"/>