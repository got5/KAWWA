package awl.frontsolutions.components

import geb.spock.GebSpec

class DataTableSpec extends GebSpec{

    def "a th element should have a scope=col"(){
        when:
        go "/Portal/component/dynamicTables"

        then:
        $("div[id='i-component'] table th[scope='col']") .size() == 5

    }

}
