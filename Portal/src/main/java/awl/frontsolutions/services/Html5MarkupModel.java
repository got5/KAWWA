package awl.frontsolutions.services;

import org.apache.tapestry5.dom.AbstractMarkupModel;
import org.apache.tapestry5.dom.EndTagStyle;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;

import java.util.Set;


public class Html5MarkupModel extends AbstractMarkupModel
{
    private final Set<String> ALWAYS_EMPTY = CollectionFactory.newSet("area", "base", "br", "col", "embed", "hr", "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr");

    public Html5MarkupModel()
    {
        this(false);
    }

    public Html5MarkupModel(boolean useApostropheForAttributes)
    {
        super(useApostropheForAttributes);
    }

    public EndTagStyle getEndTagStyle(String element)
    {
        boolean alwaysEmpty = ALWAYS_EMPTY.contains(element);

        return alwaysEmpty ? EndTagStyle.ABBREVIATE : EndTagStyle.REQUIRE;
    }

    /**
     * Returns false.
     */
    public boolean isXML()
    {
        return false;
    }
}