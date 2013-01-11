package net.atos.kawwaportal.components.test.services;

import java.lang.ref.SoftReference;
import java.util.Locale;
import java.util.Map;

import org.apache.tapestry5.internal.services.PageLoader;
import org.apache.tapestry5.internal.services.PageSource;
import org.apache.tapestry5.internal.structure.Page;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.services.InvalidationListener;

public class MyPageSourceImpl implements PageSource, InvalidationListener{

	    private final PageLoader pageLoader;

    private static final class CachedPageKey
    {
        final String pageName;

        final Locale locale;

        public CachedPageKey(String pageName,  Locale locale)
        {
            this.pageName = pageName;
            this.locale = locale;
        }

        public int hashCode()
        {
        	return 37 * pageName.hashCode() + locale.hashCode();
        }

        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;

            if (!(obj instanceof CachedPageKey))
                return false;

            CachedPageKey other = (CachedPageKey) obj;

            return pageName.equals(other.pageName) && locale.equals(other.locale);
        }
    }

    private final Map<CachedPageKey, SoftReference<Page>> pageCache = CollectionFactory.newConcurrentMap();

    public MyPageSourceImpl(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
    }

    public void objectWasInvalidated()
    {
        clearCache();
    }

    public Page getPage(String canonicalPageName, Locale locale)
    {System.out.println("############################# GETPAGE");
        CachedPageKey key = new CachedPageKey(canonicalPageName, locale);

        while (true)
        {
            SoftReference<Page> ref = pageCache.get(key);

            Page page = ref == null ? null : ref.get();

            if (page != null)
            {
                return page;
            }

            // In rare race conditions, we may see the same page loaded multiple times across
            // different threads. The last built one will "evict" the others from the page cache,
            // and the earlier ones will be GCed.

            page = pageLoader.loadPage(canonicalPageName, locale);

            ref = new SoftReference<Page>(page);

            pageCache.put(key, ref);
        }
    }

    public void clearCache()
    {
        pageCache.clear();
    }
  }
