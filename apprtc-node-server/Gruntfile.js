/*
 * Gruntfile.js
 * Copyright (C) 2019 xiaominfc(武汉鸣鸾信息科技有限公司) <xiaominfc@gmail.com>
 *
 * Distributed under terms of the MIT license.
 */

'use strict';

module.exports = function (grunt) {
  // configure project
  grunt.initConfig({
    // make node configurations available
    pkg: grunt.file.readJSON('package.json'),
    closurecompiler: {
      debug: {
        files: {
          // Destination: [source files]
            'public/js/apprtc.debug.js': [
            'node_modules/webrtc-adapter/out/adapter.js',
            'public/js_src/analytics.js',
            'public/js_src/enums.js',
            'public/js_src/appcontroller.js',
            'public/js_src/call.js',
            'public/js_src/constants.js',
            'public/js_src/infobox.js',
            'public/js_src/peerconnectionclient.js',
            'public/js_src/remotewebsocket.js',
            'public/js_src/roomselection.js',
            'public/js_src/sdputils.js',
            'public/js_src/signalingchannel.js',
            'public/js_src/stats.js',
            'public/js_src/storage.js',
            'public/js_src/util.js',
            'public/js_src/windowport.js',
          ]
        },
        options: {
          'compilation_level': 'WHITESPACE_ONLY',
          'language_in': 'ECMASCRIPT5',
          'formatting': 'PRETTY_PRINT'
        },
      },
    },
  });
  grunt.loadNpmTasks('grunt-closurecompiler-new-grunt');
  grunt.registerTask('build', ['closurecompiler:debug']);
};
