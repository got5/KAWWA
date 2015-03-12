package awl.frontsolutions.pages;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.MessagesImpl;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;

public class GAnalyticsScriptsInjector implements MarkupRendererFilter {

	private final static Messages SCRIPTS = MessagesImpl.forClass(GAnalyticsScriptsMessages.class);


	public GAnalyticsScriptsInjector() {

	}

	private void addScript(Document document) {

		Element root = document.getRootElement();

		if (root == null)
			return;

		Element body = root.find("body");

		if (body == null) {
			body = root.element("body");
		}

		Element e = body.element("script", "type", "text/javascript");

		e.raw(SCRIPTS.get("scriptOne"));
	}

	public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
		renderer.renderMarkup(writer);

		this.addScript(writer.getDocument());
	}

}
