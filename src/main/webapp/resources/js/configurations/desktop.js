/**
* Shortcut alias definitions - will come in handy when declaring dependencies
* Also, they allow you to keep the code free of any knowledge about library
* locations and versions
*/
requirejs.config({
	baseUrl: "resources/js",
	paths: {
		jquery:'libs/jquery-2.0.3',
		underscore: 'libs/underscore',
		text: 'libs/text',
		bootstrap: 'libs/bootstrap',
		backbone: 'libs/backbone',
		utilities: 'app/utilities',
		router: 'app/router/desktop/router'
	},
	
	shim: {
		'backbone': {
			deps: ['jquery', 'underscore'],
			exports: 'Backbone'
		},
		
		'underscore': {
			exports: '_'
		}
	}
});

define("initializer", ["jquery"],
		function($) {
			// Configure jQuery to append timestamps to requests, to bypass browser caches
			// Important for MSIE
			$.ajaxSetup
		}
);