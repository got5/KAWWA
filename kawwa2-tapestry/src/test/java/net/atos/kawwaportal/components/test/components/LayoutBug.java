//package net.atos.loyalty.admin.components;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import net.atos.loyalty.admin.entities.Usuario;
//import net.atos.loyalty.admin.pages.Index;
//import net.atos.loyalty.admin.pages.accesosBoletin.ListAccesosBoletin;
//import net.atos.loyalty.admin.pages.task.GenericTaskDetail;
//import net.atos.loyalty.admin.pages.task.TasksList;
//import net.atos.loyalty.admin.pages.titular.DetalleTitular;
//import net.atos.loyalty.admin.pages.titular.ListadoTitular;
//import net.atos.loyalty.admin.pages.processInstances.ProcessInstancesList;
//import net.atos.loyalty.admin.pages.processDefinition.ProcessDefinitionList;
//
//import org.apache.tapestry5.*;
//import org.apache.tapestry5.annotations.*;
//import org.apache.tapestry5.ioc.annotations.*;
//import org.apache.tapestry5.services.Request;
//import org.apache.tapestry5.BindingConstants;
//import org.apache.tapestry5.EventConstants;
//import org.apache.tapestry5.SymbolConstants;
//import org.slf4j.Logger;
//
//
///**
// * Layout component for pages of application newapp.
// * 
// * "context:js/ui/jquery.ui.core.js",
//		           "context:js/ui/jquery.ui.widget.js",
//		           "context:js/ui/jquery.ui.position.min.js",
//		           "context:js/ui/jquery.ui.dialog.min.js",
//		           "context:js/ui/jquery.ui.button.min.js",
//		           "context:js/ui/jquery.ui.panel.awl.js",
//		           "context:js/ui/jquery.ui.datepicker.js",
//		           "context:js/01_jquery.ui.core.js",
//		       	   "context:js/02_jquery.ui.widget.js",		           		       	   
//
//"context:js/03_jquery.ui.panel.awl.js",
//"context:js/plugins/jquery.tipsy.js",
//		           "context:js/superfish.js",
//		           "context:js/plugins/superfish.js",	
//		           
//		           "context:js/ui/jquery.ui.autocomplete.min.js",	 
//		           "context:js/04_jquery.ui.button.min.js",
//		           "context:js/05_jquery.ui.dialog.min.js",
//		           
//		           "context:js/k-load.js",
//		           "context:js/k-general.js",
// */
//
//@Import(library = {"context:js/validators.js"})
//public class Layout
//{
//	//--ACTIVOS DE LA PAGINA--------------------------------------------------------------
//	
////============= java scripts ========================================    	
//   
// /*   @Property
//    @Inject 
//    @Path("context:js/ieGeneralFix.js")  
//    private org.apache.tapestry5.Asset ieGeneralFix;   
//   */ 
//    @Property
//    @Inject 
//    @Path("context:js/PeriodicZoneUpdater.js")  
//    private org.apache.tapestry5.Asset periodicZoneUpdater;
//    
//  //============= estilos ========================================        
//
//    @Property
//    @Inject 
//    @Path("context:css/k-structure.css")  
//    private org.apache.tapestry5.Asset stylesKstructure;     
//    
//    @Property
//    @Inject 
//    @Path("context:css/k-theme1.css")  
//    private org.apache.tapestry5.Asset stylesKtheme1;  
//    
//    @Property
//    @Inject 
//    @Path("context:css/iehacks1.css")  
//    private org.apache.tapestry5.Asset iehacks1;  	
//    
//    @Property
//    @Inject 
//    @Path("context:css/wide_portrait_screen320.css")  
//    private org.apache.tapestry5.Asset wide_portrait_screen320;  	
//	
////============= imagenes ========================================    
//    
//    @Property
//    @Inject 
//    @Path("context:img/application_new.png")  
//    private org.apache.tapestry5.Asset imagenCabecera;  
//    
//    @Property
//    @Inject 
//    @Path("context:img/k-theme1/logo_Graphikawwa-trans.png")  
//    private org.apache.tapestry5.Asset imagenLogoGraphiKawwa;
//    
//    @Property
//    @Inject
//    @Path("context:img/bandera_idioma.jpg")
//    private Asset banderaIdiomaImagen;
//    
//    @Property
//    @Inject 
//    @Path("context:img/application_edit.png")  
//    private org.apache.tapestry5.Asset imagenEditDetalle;  
//    
//    @Property
//    @Inject 
//    @Path("context:img/application_new.png")  
//    private org.apache.tapestry5.Asset imagenCreate;    
//    
//    @Property
//    @Inject 
//    @Path("context:img/window-open2.png")  
//    private org.apache.tapestry5.Asset abrirVentanaImagen;    
//    
//    @Property
//    @Inject 
//    @Path("context:img/window-close.png")  
//    private org.apache.tapestry5.Asset cerrarVentanaImagen;    
//    
////--ATRIBUTOS DE LA SESSION--------------------------------------------------------------------------------        
//    //@Property
//    
////    @SessionAttribute
//    private Usuario userLogin; 
//
//    public Usuario getUserLogin() {
//		return this.userLogin;
//	}
//
//	public void setUserLogin(Usuario userLogin) {
//		this.userLogin = userLogin;
//	}
//
//	//----------------------------------------------------------------------------------    
//    @Inject
//    private Request request;
//    
//    @Inject
//	private Logger logger;
//	    
//    @Log
//	void setupRender() {
//    	logger.info("inicio");  
//    	
//    	try{		   
//		   if (this.request.getSession(false)!= null){
//			   logger.error("Layout.setupRender() request.getSession(false)!= null"); 
//			   if(this.request.getSession(false).getAttribute("userLogin")!= null){
//				   logger.error("Layout.setupRender() request.getSession(false).getAttribute('userLogin')!= null"); 
//				   this.userLogin = (Usuario) this.request.getSession(false).getAttribute("userLogin");   
//			   }
//			   else {
//				   logger.error("Layout.setupRender() request.getSession(false).getAttribute('userLogin')== null");
//			   }
//		   }
//		   else {
//			   logger.error("Layout.setupRender() request.getSession(false)== null");
//		   }
//		}
//		catch (Exception e){
//			logger.info("EXCEPCION: " + e.getMessage());  
//		}
//	}
//	    
//	//-- COMPONENTE: SITE SEARCH-----------------------------------------------------------------
//   	@Property
//	private String search;
//
//	@OnEvent(EventConstants.PROVIDE_COMPLETIONS)
//	public List<String> getResults(String input){
//
//		List<String> results = new ArrayList<String>();
//
//		if(input.startsWith("a")){
//			results.add("abcd");
//			results.add("abcd1");
//			results.add("abcd2");
//		}
//
//		return results;
//
//	}
//	
//	@InjectPage
//	private DetalleTitular detalle;
//	
//	@InjectPage
//	private ListadoTitular listadoTitularPage;
//	
//	@InjectPage
//	private ListAccesosBoletin listadoaccesosBoletinPage;
//	
//	@InjectPage
//	private ProcessInstancesList processInstancesListPage;
//	
//	@InjectPage
//	private ProcessDefinitionList processDefinitionListPage;
//	
//	@InjectPage
//	private GenericTaskDetail genericTaskDetailPage;
//	
//	@InjectPage
//	private TasksList tasksListPage;
//
//	@InjectPage
//	private Index index;
//	
//	
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="crear")
//			 public Object onActionFromCrear(){
//				System.out.println("crear actionLink");
//				//estos dos vienen por defecto a false pero para asegurar lo pongo
//				detalle.setConsultar(false);
//				return detalle;
//			
//	}
//	
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="consultar")
//	public Object onActionFromConsultar(){
//				detalle.setConsultar(true);
//				detalle.setPrimeraLlamada(true);
//				return detalle;			
//	}
//	
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="listado")
//	public Object onActionFromListado(){
//		return listadoTitularPage;
//	}
//	
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="accesosBoletin")
//	public Object onActionFromAccesosBoletin(){
//		return listadoaccesosBoletinPage;
//	}
//	
//	
//	@Log
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="salida")
//	public Object onActionFromSalida(){
//		if(request.getSession(false)!=null){
//			request.getSession(false).setAttribute("userLogin", null);	
//		}		
//		return index;
//	}
//	
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="processInstanceListLink")
//	public Object onActionFromProcessInstanceListLink(){
//		return processInstancesListPage;
//	}
//
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="processDefinitionListLink")
//	public Object onActionFromProcessDefinitionListLink(){
//		return processDefinitionListPage;
//	}
//	
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="taskDetailLink")
//	public Object onActionFromTaskDetailLink(){
//		return genericTaskDetailPage;
//	}
//
//	@OnEvent(value=EventConstants.ACTION, 
//			 component="tasksListLink")
//	public Object onActionFromTasksListLink(){
//		return tasksListPage;
//	}
//
//}
