package awl.frontsolutions.components;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Worker;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.PageRenderLinkSource;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.internal.ComponentConstants;
import awl.frontsolutions.pages.Component;
import awl.frontsolutions.treeDescription.NodeType;
import awl.frontsolutions.treeDescription.TreeNode;

public class ListComponents {

	@Inject
	private PageRenderLinkSource pageRender;

	@Inject
	private AssetSource assetSource;

	@Parameter(required = true)
	private TreeNode fileStructure;

	@Persist
	private Map<String, String> linkToResources;

	private String jQueryIconUrl;

	private String tapestryIconUrl;

    private String angularIconUrl;

	@SessionState
	private ChoosenTheme theme;

	public Map<String, String> getLinkToResources() {
		return linkToResources;
	}

	public void beginRender(MarkupWriter writer) {
		jQueryIconUrl = assetSource.getContextAsset(
				String.format("img/%s/jquery-trans.png", theme.getDir()), null)
				.toClientURL();
		tapestryIconUrl = assetSource.getContextAsset(
				String.format("img/%s/tapestry-small.png", theme.getDir()),
				null).toClientURL();

        angularIconUrl = assetSource.getContextAsset(
                String.format("img/%s/angular-small.png", theme.getDir()),
                null).toClientURL();
		linkToResources = new HashMap<String, String>();

		writer.element("section", "id", "components-list", "class", "transitions-enabled");
		writer.element("h2");
		writer.write("List of Components");
		writer.end();
		writer.element("p", "class", "go-top");
		writer.element("a", "href", "#", "title", "Top of the page");

		writer.element(
				"img",
				"src",
				assetSource.getContextAsset(
						String.format("img/%s/to-top.png", theme.getDir()),
						null).toClientURL(), "alt", "Top");
		writer.end();

		writer.end();
		writer.end();

		writer.element("dl", "class", "legend");

		writer.element("dt");
		writer.element("img", "src", jQueryIconUrl, "alt", "jQuery Feature");
		writer.end();
		writer.end();

		writer.element("dd");
		writer.write("jQuery Interactive Feature");
		writer.end();

		writer.element("dt");
		writer.element("img", "src", tapestryIconUrl, "alt",
				"Tapestry Integration");
		writer.end();
		writer.end();
		writer.element("dd");
		writer.write("Tapestry integration");
		writer.end();

        writer.element("dt");
        writer.element("img", "src", angularIconUrl, "alt",
                "AngularJS Integration");
        writer.end();
        writer.end();
        writer.element("dd");
        writer.write("AngularJS integration");
        writer.end();

		writer.end();

		for (TreeNode subMenu : fileStructure.getChildren()) {
			listDirectory(subMenu, writer, 1);
		}
		writer.end();

	}

	private void listDirectory(TreeNode fileStructure, final MarkupWriter writer,
			final int level) {

		if (fileStructure.getNodeType().equals(NodeType.GROUP)) {
			writer.element("section");
			if (level > 0) {
				writer.element("h3");
				String nameFile = null;

				writer.element(
						"img",
						"src",
						assetSource.getContextAsset(
								String.format("img/%s/icon_%s.png", theme
										.getDir(), fileStructure.getNodeName()
										.replaceAll("\\s", "")), null)
								.toClientURL(), "alt", "");
				writer.end();
				writer.write(fileStructure.getNodeName());
				writer.end();
			}
            writer.element("ul");
			F.flow(fileStructure.getChildren())
					.sort(new Comparator<TreeNode>() {

						@Override
						public int compare(TreeNode o1, TreeNode o2) {
							return o1.getNodeName().compareToIgnoreCase(
									o2.getNodeName());
						}
					}).each(new Worker<TreeNode>() {

						@Override
						public void work(TreeNode element) {

							listDirectory(element, writer, level + 1);


						}
					});

            writer.end();
			writer.end();
		} else if (fileStructure.getNodeType().equals(NodeType.COMPONENT)) {
			if (level == 1) {
				writer.element("section");

				writer.element("h3");
				String nameFile = null;

				writer.element(
						"img",
						"src",
						assetSource.getContextAsset(
								String.format("img/%s/icon_%s.png", theme
										.getDir(), fileStructure.getNodeName()
										.replaceAll("\\s", "")), null)
								.toClientURL(), "alt", "");
				writer.end();

				writer.end();

                        writer.element("ul");

				writer.element("li");
				writer.element("a", "href", pageRender
						.createPageRenderLinkWithContext(Component.class,
								fileStructure.getUrlParam()));
				writer.write("HTML5 Page Structure");
				writer.element("img", "src", tapestryIconUrl, "alt",
						"Tapestry Integration");
				writer.end();
				writer.end();
				writer.end();

				writer.element("li");
				writer.element("a", "href", pageRender
						.createPageRenderLinkWithContext(Component.class,
								fileStructure.getUrlParam()));
				writer.write("XHTML Page Structure");
				writer.element("img", "src", tapestryIconUrl, "alt",
						"Tapestry Integration");
				writer.end();
				writer.end();
				writer.end();

				writer.end();

				writer.end();
			} else {
				writer.element("li");
				writer.element("a", "href", pageRender
						.createPageRenderLinkWithContext(Component.class,
								fileStructure.getUrlParam()));
				writer.write(fileStructure.getNodeName());
				if (fileStructure.containsTag(ComponentConstants.TAG_JQUERY)) {
					writer.write(" ");
					writer.element("img", "src", jQueryIconUrl, "alt",
							"jQuery", "title", "jQuery Interactive feature");
					writer.end();
				}
				if (fileStructure.containsTag(ComponentConstants.TAG_TAPESTRY)) {
					writer.write(" ");
					writer.element("img", "src", tapestryIconUrl, "alt",
							"Tapestry", "title", "Tapestry Integration");
					writer.end();
				}
                if (fileStructure.containsTag(ComponentConstants.TAG_ANGULAR)) {
                    writer.write(" ");
                    writer.element("img", "src", angularIconUrl, "alt",
                            "Angular", "title", "ANGULARIntegration");
                    writer.end();
                }
				writer.end();
				writer.end();
			}
		}
	}

}
