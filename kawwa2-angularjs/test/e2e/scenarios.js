'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('Demo App', function() {
   describe('index',function(){

       beforeEach(function(){
           browser().navigateTo('../../demo/index.html');
       });

       it('should displays the bigger image when clicked on thumber',function() {

           expect(repeater('.thumblist li').count()).toBe(3);
       });

   });
});

