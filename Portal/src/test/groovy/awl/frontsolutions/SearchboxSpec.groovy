package awl.frontsolutions

import geb.spock.GebSpec
import spock.lang.Unroll

class SearchboxSpec extends GebSpec{
    @Unroll
    def "nav should have role=navigation and role=searchfor #page"(){
        setup:
        go page

        expect:
        $("form[role='search']") .size() >= 1
        $("input[type='search']") .size() >= 1

        where:
        page << ["/Portal/Index",
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
                "/Portal/Releases",
                "/Portal/Login",
                "/Portal/Tapestry",
                "/Portal/Templates",
                "/Portal/UnobtrusiveApproach",
                "/Portal/Updates"]

    }
}