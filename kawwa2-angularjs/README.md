Kawwa2 AngularJS
================


Run the Tests
-------------

### Requirement

* [node]
* [karma]


### Running Unit Tests

    karma start config/karma-unit.conf.js

### Running E2E Tests

* A web server must be running.

        karma start config/karma-e2e.conf.js

* Navigate to  [http://localhost:8000/test/e2e/runner.html](http://localhost:8000/test/e2e/runner.html)


Run the Demo
------------


Some directives need  a web server to be run properly.

There is one available in the scripts directory (require [node]). Launch it from the *[kawwa-angular]* directory :

    ./scripts/web-server.js

Go to : [http://localhost:8000/demo/index.html](http://localhost:8000/demo/index.html)




[node]: http://nodejs.org "NodeJs"
[karma]: http://karma-runner.github.io "Karma"
[kawwa-angular]: https://github.com/got5/KAWWA/tree/master/kawwa2-angularjs "kawwa2-angularjs"
