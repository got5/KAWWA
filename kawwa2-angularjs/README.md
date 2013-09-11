Kawwa2 AngularJS
================

Download
--------

You can grab the last version of Kawwa Angular here :  [Release][release]

In the archive, you will find the library as a plethora of forms : 

* the directive files
* the jquery plugins files
* the full kawwa library without the plugins
* the full kawwa library packaged with the plugins

Just pick the one you need.

Usage
-----

In the app.js of your application, just add kawwa to your dependencies like this :

	angular.module('demoApp', ['kawwa'])

	### Requirement

    * Jquery 1.8.3
    * Jquery-ui
    * [Karma][karma]
    * [Grunt][grunt]
    * [Bower][bower]
    * [Yeoman][yo]



Contribute to Kawwa
-------------------

The next part of this document only concerns you if you want to contribute or just want to config kawwa by yourself.




Install
-------

### Requirement

* [NodeJS][node]
* [Ruby][ruby]
* [Karma][karma]
* [Grunt][grunt]
* [Bower][bower]
* [Yeoman][yo]


Some of those can be install from Node.

	sudo npm install -g yo grunt-cli bower karma
	sudo npm install -g generator-angular generator-karma

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


### Build Kawwa angular

	grunt build

This will also prepare the demo app	


### Generate de doc

	grunt doc

### Package the library in a zip file
	
	grunt build
	grunt zip

### Do it all 

	grunt release



[node]: http://nodejs.org "NodeJs"
[ruby]: http://www.ruby-lang.org/fr/ "Ruby"
[karma]: http://karma-runner.github.io "Karma"
[phantomjs]: http://phantomjs.org/ "PhantomJS"
[yo]: http://yeoman.io/ "Yeoman"
[grunt]: http://gruntjs.com/ "Grunt"
[bower]: https://github.com/bower/bower "Bower"
[generator-angular]: https://github.com/yeoman/generator-angular 
[midway]: https://github.com/yearofmoo/ngMidwayTester
[release]: https://github.com/got5/KAWWA/blob/master/kawwa2-angularjs/kawwa-release.zip?raw=true
