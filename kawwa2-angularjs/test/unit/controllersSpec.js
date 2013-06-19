/* jasmine specs for controllers go here */
describe('Demo controllers', function() {

    describe('TestCtrl', function(){
       iit('should create "images" model with 3 images',function(){
           var scope = {},
               ctrl = new TestCtrl(scope);
           expect(scope.gallery.length).toBe(3);
       })
    })
});


