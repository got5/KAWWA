describe("Midway: Testing fieldComment", function() {

  var test, $injector;

  beforeEach(function(done) {
    ngMidwayTester.register('kawwa2', function(instance) {
      test = instance;
      done();
    });
  });

  beforeEach(function() {
    $injector = test.$injector;
  });

  //no test yet...
});