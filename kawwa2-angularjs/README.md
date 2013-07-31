Kawwa2 AngularJS
================

Note to users : __Not ready for use__

If you still want to use it, get the directive files you need and add  the kawwa dependance to your application.  
	
	var myAppModule = angular.module('myApp', ['kawwa']);




Install
-------

### Requirement

* [NodeJS][node]
* [Ruby][ruby]
* [PhantomJS][phantomjs] \(optional\)
* [Karma][karma]
* [Grunt][grunt]
* [Bower][bower]

Some of those can be install from Node.

	sudo npm install -g grunt-cli bower karma

### Download dependencies from package.json
	
	npm install
	bower install

Running & Tests
-----

### Running the app
	
	grunt server	

### Running Unit Tests

    grunt test:unit

### Running E2E Tests

Close the web server before or configure the karma proxy

	grunt test:e2e

### Running Midway Tests

If you don't know what midway test is, go check this out : [ngMidwayTest][midway]	

	grunt test:midway

Want to add some contents ?
------------

You will need [Yeoman][yo] to continue.

 	sudo npm install -g yo 
 	sudo npm install -g generator-angular generator-karma

When you will create a directive, a route, controller or your unit tests, __you will have to create it from__ [Yeoman][yo]

example : For creating the Raty directive

	yo angular:directive raty

This will create the files, include it in HTML when necessary. It will also create the associate unit test.

See [generator-angular] web site for the  full doc.





[node]: http://nodejs.org "NodeJs"
[ruby]: http://www.ruby-lang.org/fr/ "Ruby"
[karma]: http://karma-runner.github.io "Karma"
[phantomjs]: http://phantomjs.org/ "PhantomJS"
[yo]: http://yeoman.io/ "Yeoman"
[grunt]: http://gruntjs.com/ "Grunt"
[bower]: https://github.com/bower/bower "Bower"
[generator-angular]: https://github.com/yeoman/generator-angular 
[midway]: https://github.com/yearofmoo/ngMidwayTester