package awl.frontsolutions

import awl.frontsolutions.services.ComponentUtils
import awl.frontsolutions.services.impl.ComponentUtilsImpl
import org.apache.tapestry5.Asset
import org.apache.tapestry5.internal.services.ApplicationStateManagerImpl
import org.apache.tapestry5.ioc.Resource
import org.apache.tapestry5.services.ApplicationStateContribution
import org.apache.tapestry5.services.ApplicationStateManager
import org.apache.tapestry5.services.AssetSource
import spock.lang.Specification
import spock.lang.Unroll

class MockAssetSource implements AssetSource{

    @Override
    Asset getAsset(Resource baseResource, String path, Locale locale) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Resource resourceForPath(String path) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Asset getClasspathAsset(String path, Locale locale) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Asset getContextAsset(String path, Locale locale) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Asset getClasspathAsset(String path) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Asset getUnlocalizedAsset(String path) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Asset getExpandedAsset(String path) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}

class MockApplicationStateManager implements ApplicationStateManager {
    @Override
    public <T> T get(Class<T> ssoClass){ return null; }

    @Override
    public <T> T getIfExists(Class<T> ssoClass){ return null; }

    @Override
    public <T> void set(Class<T> ssoClass, T sso){ }

    @Override
    public <T> boolean exists(Class<T> ssoClass) { return true;}
}

class FormatFileSizeTest extends Specification{

    @Unroll
	def "test formatFileSize method"() {
        ComponentUtils service = new ComponentUtilsImpl(new MockAssetSource() {},
                new MockApplicationStateManager()) ;

       expect:
            service.formatFileSize(size).equals(formatted)

        where:
        size              | formatted
        1024              | "1 024 bytes"
        1025              | "1 KB"
        1025*1024         | "1 MB"
        1025*1024*1024    | "1 GB"
	}
}
