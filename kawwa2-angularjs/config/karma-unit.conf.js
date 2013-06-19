basePath = '../demo';

files = [
    JASMINE,
    JASMINE_ADAPTER,
    'lib/angular/angular.js',
    '../test/lib/angular/angular-mocks.js',
    'lib/jquery/jquery-1.8.3.min.js',
    'js/**/*.js',
    '../kawwa-angular.js',
    '../test/unit/*.js'

];

autoWatch = true;

browsers = ['Chrome'];
reporters = ['dots','junit'];
junitReporter = {
    outputFile: '../test-results.xml'

};
