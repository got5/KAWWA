basePath = '../demo';

files = [
    ANGULAR_SCENARIO,
    ANGULAR_SCENARIO_ADAPTER,
    '../test/e2e/**/*.js'
];

autoWatch = false;

browsers = ['Chrome'];

singleRun = true;

proxies = {
    '/': 'http://localhost:8000/demo/index.html'
};

reporters=['junit'];
junitReporter = {
    outputFile: 'test_out/e2e.xml',
    suite: 'e2e'
};
