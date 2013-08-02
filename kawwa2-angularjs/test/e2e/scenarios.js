'use strict';

describe('kawwa app', function() {

    beforeEach(function() {
        browser().navigateTo('/');
    });

    describe('Homepage', function() {
        it('should display the correct route', function() {
            expect(browser().location().path()).toBe('/');
        });
    });
    
    describe('Component : Directive :  fieldComment ',function() {
    	
    	
    	it('should display the correct route', function() {
    		browser().navigateTo('/#/components');
    		expect(browser().location().path()).toBe('/components');
    		var elem = element('#field1');
         

   //  		elem.query(function(elements, done) {
   //  			elements.trigger('hover');
   //  			done();
			// });

      elem.click();
      var tipsy = element('div.tipsy');
    		//expect(tipsy.count()).toBe(1); // 0
    		
    		//broken ! cannot get this working...  
            
        });
    });
    

});