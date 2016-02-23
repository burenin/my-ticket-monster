define([
	'utilities',
	'require',
	'configuration',
	'text!../../../../templates/desktop/booking-confirmation.html',
	'text!../../../../templates/desktop/create-booking.html',
	'text!../../../../templates/desktop/ticket-categories.html',
	'text!../../../../templates/desktop/ticket-summary-view.html',
	'bootstrap'
],function (
		utilities,
		require,
		config,
		bookingConfirmationTemplate,
		createBookingTemplate,
		ticketEntriesTemplate,
		ticketSummaryViewTemplate){
	
	var TicketCategoriesView = Backbone.View.extend({
		id:'categoriesView',
		intervalDuration : 100,
		formValues : [],
		events:{
			"change input":"onChange"
		},
		render:function () {
			if (this.model != null) {
				var ticketPrices = _.map(this.model, function (item) {
					return item.ticketPrice;
				});
				utilities.applyTemplate($(this.el), ticketEntriesTemplate,
						{ticketPrices:ticketPrices});
			} else {
				$(this.el).empty();
			}
			this.watchForm();
			return this;
		},
		onChange:function (event) {
			var value = event.currentTarget.value;
			var ticketPriceId = $(event.currentTarget).data("tm-id");
			var modifiedModelEntry = _.find(this.model, function (item) {
				return item.ticketPrice.id == ticketPriceId
			});
//			update model
			if ($.isNumeric(value) && value > 0) {
				modifiedModelEntry.quantity = parseInt(value);
			}
			else {
				delete modifiedModelEntry.quantity;
			}
//			display error messages
			if (value.length > 0 &&
					(!$.isNumeric(value) // is a non-number, other than empty string
							|| value <= 0 // is negative
							|| parseFloat(value) != parseInt(value))) { // is not an integer
				$("#error-input-"+ticketPriceId).empty().append("Please enter a positive integer value");
						$("#ticket-category-fieldset-"+ticketPriceId).addClass("error");
			} else {
				$("#error-input-"+ticketPriceId).empty();
				$("#ticket-category-fieldset-"+ticketPriceId).removeClass("error");
			}
//			are there any outstanding errors after this update?
//			if yes, disable the input button
			if (
					$("div[id^='ticket-category-fieldset-']").hasClass("error") ||
					_.isUndefined(modifiedModelEntry.quantity) ) {
				$("input[name='add']").attr("disabled", true)
			} else {
				$("input[name='add']").removeAttr("disabled")
			}
		},
		watchForm: function() {
			if($("#sectionSelectorPlaceholder").length) {
				var self = this;
				$("input[name*='tickets']").each( function(index,element) {
					if(element.value !== self.formValues[element.name]) {
						self.formValues[element.name] = element.value;
						$("input[name='"+element.name+"']").change();
					}
				});
				this.timerObject = setTimeout(function() {
					self.watchForm();
				}, this.intervalDuration);
			} else {
				this.onClose();
			}
		},
		onClose: function() {
			if(this.timerObject) {
				clearTimeout(this.timerObject);
				delete this.timerObject;
			}
		}
	});