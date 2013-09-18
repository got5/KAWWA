// Karma configuration
// Generated on Mon Aug 26 2013 14:21:36 GMT+0200 (CEST)

module.exports = function (config) {
    config.set({

        // base path, that will be used to resolve files and exclude
        basePath: '../app',

        // frameworks to use
        frameworks: ['jasmine'],


        // list of files / patterns to load in the browser
        files: [
            'components/jquery/jquery.min.js',
            'components/jquery-ui/ui/jquery.ui.core.js',
            'components/jquery-ui/ui/jquery.ui.widget.js',
            'plugins/**/*.js',
            'components/angular/angular.js',
            'components/angular-ui-router/release/angular-ui-router.js',
            'components/angular-mocks/angular-mocks.js',
            'components/angular-*/*min.js',
            'scripts/**/*.js',
            '../test/unit/**/*.js',
            '../app/template/*.html'
        ],

        preprocessors: {
            'tpl/*.html': 'ng-html2js'
        },

        // list of files to exclude
        exclude: [

        ],


        // test results reporter to use
        // possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
        reporters: ['progress'],


        // web server port
        port: 9876,


        // enable / disable colors in the output (reporters and logs)
        colors: true,


        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_ERROR,


        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,


        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['Chrome'],


        // If browser does not capture in given timeout [ms], kill it
        captureTimeout: 60000,


        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false
    });
};
