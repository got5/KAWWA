package awl.frontsolutions

import geb.spock.GebSpec

class ListOfComponentsSpec extends GebSpec{

    def "A Section with id=components-list should exist"(){
        when:
        go "/Portal/Index"

        then:
        $("section", id:"components-list").size() == 1
    }

    def "A category should contain only one UL element"(){
        when:
        go "/Portal/Index"

        then:
        $("section[id='components-list'] section").size() == $("section[id='components-list'] section ul").size()
    }

    def "All header images should have an alt attribute"(){
        when:
        go "/Portal/Index"

        then:
        $("section[id='components-list'] section h3 img:not([alt])").size() == 0
    }
}
