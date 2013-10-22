package awl.frontsolutions

import geb.spock.GebSpec
import spock.lang.Unroll

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

    @Unroll
    def "Email field should have a type=email for #page"(){
        setup:
        go page

        expect:
        $("form[id='contextForm'] input[type='email']") .size() == 1

        where:
        page << ["/Portal/Index",
                "/Portal/About",
                "/Portal/AboutKawwa",
                "/Portal/Accessibility",
                "/Portal/Angular",
                "/Portal/Component",
                "/Portal/ComponentApproach",
                "/Portal/Contact",
                "/Portal/Documentation",
                "/Portal/Download",
                "/Portal/Faq",
                "/Portal/HowTo",
                "/Portal/IeFix",
                "/Portal/KawwaPortal",
                "/Portal/License",
                "/Portal/Releases",
                "/Portal/Login",
                "/Portal/Tapestry",
                "/Portal/Templates",
                "/Portal/UnobtrusiveApproach",
                "/Portal/Updates"]

    }

}
