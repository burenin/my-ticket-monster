/**
 * Module for the Events collection
 */
define([
        // The collection element type and configuration are dependencies
        'app/models/event',
        'configuration'
], function (Event, config) {
	/**
	 * Here we define the Events collection
	 * We will use it for CRUD operations on events
	 */
	var Events = Backbone.Collection.extend({
		url: config.baseUrl + "rest/events",	// the URL for performing CRUD operations
		model: Event,
		id:"id", 		// the 'id' property of the model
		comparator: function(model) {
			return model.get('category').id;
		}
	});
	return Events;
});