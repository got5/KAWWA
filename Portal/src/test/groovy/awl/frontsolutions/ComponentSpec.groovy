package awl.frontsolutions

import geb.spock.GebSpec


class ComponentSpec extends GebSpec{
    def "A Component page should have a DEMO tab"(){
        when:
        go "/Portal/component/ResponsiveMainNav"

        then:
        $("a[href='#demo']").size() == 1
        $("div[id='demo']").size() == 1
    }
    def "A Component page should have a HTML5 tab"(){
        when:
        go "/Portal/component/ResponsiveMainNav"

        then:
        $("a[href='#html5']").size() == 1
        $("div#html5").size() == 1
    }
}
