'use strict';
var LIVERELOAD_PORT = 35729;
var lrSnippet = require('connect-livereload')({ port: LIVERELOAD_PORT });
var mountFolder = function (connect, dir) {
    return connect.static(require('path').resolve(dir));
};



module.exports = function (grunt) {
    // load all grunt tasks
    require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

    // configurable paths
    var yeomanConfig = {
        app: 'app',
        dist: 'dist',
        doc: 'docs'
    };

    try {
        yeomanConfig.app = require('./bower.json.bak').appPath || yeomanConfig.app;
    } catch (e) {
    }

    grunt.initConfig({
        yeoman: yeomanConfig,
        watch: {
            livereload: {
                options: {
                    livereload: LIVERELOAD_PORT
                },
                files: [
                    '<%= yeoman.app %>/{,*/}*.html',
                    '<%= yeoman.app %>/template/{,*/}*.html',
                    '{.tmp,<%= yeoman.app %>}/styles/{,*/}*.css',
                    ' {.tmp,<%= yeoman.app %>}/scripts/{,*/}*.js',
                    '<%= yeoman.app %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
                ]
            }
        },
        connect: {
            options: {
                port: 9000,

                // Change this to '0.0.0.0' to access the server from outside.
                hostname: 'localhost'
            },
            livereload: {
                options: {
                    middleware: function (connect) {
                        return [
                            lrSnippet,
                            mountFolder(connect, '.tmp'),
                            mountFolder(connect, yeomanConfig.app)
                        ];
                    }
                }
            },
            test: {
                options: {
                    middleware: function (connect) {
                        return [
                            mountFolder(connect, '.tmp'),
                            mountFolder(connect, 'test')
                        ];
                    }
                }
            },
            dist: {
                options: {
                    middleware: function (connect) {
                        return [
                            mountFolder(connect, yeomanConfig.dist)
                        ];
                    }
                }
            },
            doc: {
                options: {
                    port: 8000,
                    base: 'docs'
                }
            }
        },
        open: {
            server: {
                url: 'http://localhost:<%= connect.options.port %>'
            },
            doc: {
                url: 'http://localhost:<%= connect.doc.options.port %>'
            }
        },
        clean: {
            dist:'dist',
            server: '.tmp',
            ngdocexample:'app/scripts/temp',
            doc: 'docs'
        },
        jshint: {
            options: {
                jshintrc: '.jshintrc'
            },
            all: [
                'Gruntfile.js',
                '<%= yeoman.app %>/scripts/{,*/}*.js'
            ]
        },
        concat: {
            options: {
                separator: ';'
            },
            only: {
                src: [
                    '<%= yeoman.dist %>/lib/*',
                    '<%= yeoman.app %>/scripts/kawwa.js',
                    '<%= yeoman.app %>/scripts/temp/scripts/templates.js',
                    '<%= yeoman.app %>/scripts/directives/*.js'],
                dest: '<%= yeoman.dist %>/kawwa-directives-only.js'
            },
            full: {
                src: [
                    '<%= yeoman.dist %>/lib/*',
                    '<%= yeoman.app %>/scripts/kawwa.js',
                    '<%= yeoman.app %>/scripts/temp/scripts/templates.js',
                    '<%= yeoman.app %>/plugins/*.js',
                    '<%= yeoman.app %>/scripts/directives/*.js'],
                dest: '<%= yeoman.dist %>/kawwa-directives-full.js'
            }

        },
        rev: {
            dist: {
                files: {
                    src: [
                        '<%= yeoman.dist %>/scripts/{,*/}*.js'
                    ]
                }
            }
        },
        // Put files not handled in other tasks here
        copy: {
            basic: {
                files: [
                    {
                        expand: true,
                        dot: true,
                        cwd: '<%= yeoman.app %>/scripts/',
                        dest: '<%= yeoman.dist %>',
                        src: [
                            'directives/**/*'

                        ]
                    }
                ]
            },
            template: {
                files: [
                    {
                        expand: true,
                        dot: true,
                        cwd: '<%= yeoman.app %>/',
                        dest: '<%= yeoman.dist %>/directives',
                        src: [
                            'template/*'

                        ]
                    }
                ]
            },
            lib:{
                files: [
                    {
                        expand: true,
                        dot: true,
                        cwd: '<%= yeoman.app %>/components/angular-ui-bootstrap-bower/',
                        dest: '<%= yeoman.dist %>/lib',
                        src: [
                            'ui-bootstrap.js'
                        ]
                    }
                ]
            },
            plugin: {
                files: [
                    {
                        expand: true,
                        dot: true,
                        cwd: '<%= yeoman.app %>',
                        dest: '<%= yeoman.dist %>',
                        src: [
                            'plugins/*'
                        ]
                    }
                ]
            },


            declaremodule: {
                files: [
                    {
                        expand: true,
                        dot: true,
                        process: function (src) {
                            return "try{\nangular.module(\"kawwa2\");   \n}catch(err){\nangular.module(\'kawwa2\',[]);\n//angular.module(\'kawwa2\',[\'ui.bootstrap\']);\n}\n"
                                + '\n' + src.replace(/(^|\n)[ \t]*('use strict'|"use strict");?\s*/g, '$1');
                        },
                        cwd: '<%= yeoman.dist %>',
                        dest: '<%= yeoman.dist %>',
                        src: [
                            '/app/lib/kawwa/scripts/directives'
                        ]
                    }
                ]
            },
            imageDoc: {
                files: [
                    {
                        expand: true,
                        dot: true,
                        cwd: '<%= yeoman.app %>',
                        dest: '<%= yeoman.doc %>',
                        src: [
                            'img/*', 'img/**/*'
                        ]
                    }
                ]
            },
            imageThemeDoc: {
                files: [
                    {
                        expand: true,
                        dot: true,
                        cwd: '<%= yeoman.app %>/components/kawwa/img',

                        dest: '<%= yeoman.doc %>/img',
                        src: [
                            '*', '**/*'
                        ]
                    }
                ]
            }

        },
        karma: {
            unit: {
                configFile: 'test/karma.conf.js',
                singleRun: true
            },
            e2e: {
                configFile: 'test/karma-e2e.conf.js',
                singleRun: true
            }
        },
        ngmin: {
            dist: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= yeoman.dist %>',
                        src: '*.js',
                        dest: '<%= yeoman.dist %>'
                    }
                ]
            }
        },
        uglify: {
            only: {
                files: {
                    '<%= yeoman.dist %>/kawwa-directives-only.min.js': [
                        '<%= yeoman.dist %>/kawwa-directives-only.js'
                    ]
                }
            },
            full: {
                files: {
                    '<%= yeoman.dist %>/kawwa-directives-full.min.js': [
                        '<%= yeoman.dist %>/kawwa-directives-full.js'
                    ]
                }
            }
        },
        zip: {
            'kawwa-release.zip': ['<%= yeoman.dist %>/*', '<%= yeoman.dist %>/**/*']
        },
        ngdocs: {
            options: {
                scripts: ['<%= yeoman.app %>/components/jquery/jquery.min.js',
                    '<%= yeoman.app %>/components/jquery-ui/ui/jquery.ui.core.js',
                    '<%= yeoman.app %>/components/jquery-ui/ui/jquery.ui.widget.js',
                    'util/angular.min.js',
                    'util/moduleApp.js',
                    '<%= yeoman.dist %>/kawwa-directives-full.js'
                ],
                styles: [
                    '<%= yeoman.app %>/components/kawwa/css/k-structure.css',
                    '<%= yeoman.app %>/components/kawwa/css/k-theme0.css',
                    'util/reset.css',
                    'util/kawwa-bootstrap-fix.min.css'
                ],
                dest: 'docs',
                html5Mode: true
            },
            api: {
                src: ['app/scripts/temp/scripts/directives/*.js'],
                title: 'API Documentation'
            }
        },
        replace: {
            doc: {
                src: ['<%= yeoman.doc %>/index.html'],
                overwrite: true,
                replacements: [
                    {
                        from: "addTag('link', {rel: 'stylesheet', href: 'css/bootstrap.min.css', type: 'text/css'});",
                        to: function (matchedWord) {
                            return "";
                        }
                    }
                ]
            }
        },
        ngTemplateCache : {
            options:{
              module:'kawwa2',
              trim:'app/'
            },
            files:{
                src:['<%= yeoman.app %>/template/**/*.html'],
                dest:'<%= yeoman.app %>/scripts/temp/scripts/templates.js'
            }
        },
        includes:{
            ngdocexample:{
                cwd:'app',
                src:['scripts/directives/*.js'],
                dest:'app/scripts/temp/',
                options:{

                }
            }
        }
        });

    grunt.registerTask('server', function (target) {
        if (target === 'dist') {
            return grunt.task.run(['build', 'open:server', 'connect:dist:keepalive']);
        }

        grunt.task.run([
            'clean:server',
            // 'concurrent:server',
            'connect:livereload',
            'open:server',
            'watch'
        ]);
    });

    grunt.registerTask('test:unit', [
        'clean:server',
        'connect:test',
        'karma:unit'
    ]);

    grunt.registerTask('test:e2e', [
        'clean:server',
        //'livereload-start',
        'connect:livereload',
        'karma:e2e'
    ]);



    grunt.registerTask('build', [
        'clean:dist',
        'ngTemplateCache',
        'includes:ngdocexample',
        'copy:basic',  //copy kawwa  to dist
        'copy:lib',  //copy kawwa  to dist
        'copy:template',
        'copy:plugin', //copy the plugins to dist
        'concat',      //concat the directives with the plugins
        'ngmin',
        'uglify',
        //'rev',
        'copy:declaremodule'

    ]);

    grunt.registerTask('doc-build', [
        'build',
        'clean:doc',
        'ngdocs',
        'copy:imageDoc',
        'copy:imageThemeDoc',
        'replace:doc',
        //'clean:ngdocexample'
    ]);

    grunt.registerTask('doc', [
        'doc-build',
        'open:doc',
        'connect:doc:keepalive'
    ]);


    grunt.registerTask('release', [
        'clean:doc',
        'build',
        'zip',
        'doc-build'
    ]);
    grunt.registerTask('default', [
        'test:unit',
        //'test:e2e',
        //'test:midway',
        'build'
    ]);
};
