package awl.frontsolutions.components;

import awl.frontsolutions.entities.ChoosenTheme;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

@Import(library="context:js/plugins/jquery.kawwa.nav.js")
public class TopMenu {

	@Parameter
	private Integer activeMenu;

	@SessionState
    private ChoosenTheme choosen;

    @Inject
    private AssetSource as;

	public String getClass(Integer indice){

		String classe = new String();

		if(indice == 0) classe = "home ";

		if(indice == activeMenu) classe += "active";

		return classe;
	}

	public Boolean isActive(Integer indice){
		return getClass(indice).contains("active");
	}

	public String getUrlImg(){
		return as.getContextAsset(String.format("img/%s/nav_control.png", choosen.getDir()), null).toClientURL();
	}
}
