package awl.frontsolutions.services;

import org.apache.tapestry5.ContentType;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.dom.DefaultMarkupModel;
import org.apache.tapestry5.dom.MarkupModel;
import org.apache.tapestry5.dom.XMLMarkupModel;
import org.apache.tapestry5.internal.services.MarkupWriterImpl;
import org.apache.tapestry5.internal.services.PageContentTypeAnalyzer;
import org.apache.tapestry5.internal.services.RequestPageCache;
import org.apache.tapestry5.internal.structure.Page;
import org.apache.tapestry5.services.MarkupWriterFactory;

public class Html5MarkupWriterFactory implements MarkupWriterFactory
{
    private final PageContentTypeAnalyzer analyzer;

    private final RequestPageCache cache;

    private final MarkupModel htmlModel = new Html5MarkupModel();

    private final MarkupModel xmlModel = new XMLMarkupModel();

    private final MarkupModel htmlPartialModel = new Html5MarkupModel(true);

    private final MarkupModel xmlPartialModel = new XMLMarkupModel(true);

    public Html5MarkupWriterFactory(PageContentTypeAnalyzer analyzer, RequestPageCache cache)
    {
        this.analyzer = analyzer;
        this.cache = cache;
    }

    public MarkupWriter newMarkupWriter(ContentType contentType)
    {
        return newMarkupWriter(contentType, false);
    }

    public MarkupWriter newPartialMarkupWriter(ContentType contentType)
    {
        return newMarkupWriter(contentType, true);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private MarkupWriter newMarkupWriter(ContentType contentType, boolean partial)
    {
        boolean isHTML = contentType.getMimeType().equalsIgnoreCase("text/html");

        MarkupModel model = partial
                ? (isHTML ? htmlPartialModel : xmlPartialModel)
                : (isHTML ? htmlModel : xmlModel);

        // The charset parameter sets the encoding attribute of the XML declaration, if
        // not null and if using the XML model.

        return new MarkupWriterImpl(model, contentType.getCharset());
    }

    public MarkupWriter newMarkupWriter(String pageName)
    {
        Page page = cache.get(pageName);

        ContentType contentType = analyzer.findContentType(page);

        return newMarkupWriter(contentType);
    }
}
