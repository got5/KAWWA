package awl.frontsolutions.internal;

import awl.frontsolutions.entities.Panier;
import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;

public class KawwaUtils {

	public static void updateDownload(Panier panier, String name, String value) {
		if (name.equals("examples")) {
			panier.setIncludeTemplate(Boolean.valueOf(value));
		} else if (name.equals("tapestry")) {
			panier.setIncludeTapestry(Boolean.valueOf(value));
		} else if (name.equals("doctype")) {

			for (DownloadDocType d : DownloadDocType.values()) {
				if (d.name().equalsIgnoreCase(value))
					panier.setDoctype(DownloadDocType.HTML5);
				else
					panier.setDoctype(DownloadDocType.XHTML);
			}
		} else if (name.equals("theme")) {
			panier.setTheme(value);
		} else if (name.equals("componentList")) {
			panier.getListeComponent().clear();
			for (String cp : value.split(",")) {
				if (InternalUtils.isNonBlank(cp))
					panier.add(cp);
			}

		}
	}

	public static String replaceTag(String notes, String tag, String content) {
		if (StringUtils.isNotEmpty(content)) {
			return notes.replaceAll("\\[\\[" + tag + "\\]\\]", content);
		} else {
			return notes.replaceAll("\\[\\[" + tag + "\\]\\]", "");
		}
	}


}
