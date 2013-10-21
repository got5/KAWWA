package awl.frontsolutions

import geb.spock.GebSpec

class ContactFormSpec extends GebSpec{
    def "label associated to input namecontact"(){
        when:
        go "/Portal/Index"

        then:
        $("input", id:"namecontact") .size() == 1
        $("label", for:"namecontact") .size() == 1
    }

    def "label associated to input emailcontact"(){
        when:
        go "/Portal/Index"

        then:
        $("input", id:"emailcontact") .size() == 1
        $("label", for:"emailcontact") .size() == 1
    }

    def "label associated to input bodycontact"(){
        when:
        go "/Portal/Index"

        then:
        $("textarea", id:"bodycontact") .size() == 1
        $("label", for:"bodycontact") .size() == 1
    }
}
