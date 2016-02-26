
angular.module('myticketmonster').controller('NewTicketPriceController', function ($scope, $location, locationParser, flash, TicketPriceResource , ShowResource, SectionResource, TicketCategoryResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.ticketPrice = $scope.ticketPrice || {};
    
    $scope.showList = ShowResource.queryAll(function(items){
        $scope.showSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.displayTitle
            });
        });
    });
    $scope.$watch("showSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.ticketPrice.show = {};
            $scope.ticketPrice.show.id = selection.value;
        }
    });
    
    $scope.sectionList = SectionResource.queryAll(function(items){
        $scope.sectionSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.name
            });
        });
    });
    $scope.$watch("sectionSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.ticketPrice.section = {};
            $scope.ticketPrice.section.id = selection.value;
        }
    });
    
    $scope.ticketCategoryList = TicketCategoryResource.queryAll(function(items){
        $scope.ticketCategorySelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("ticketCategorySelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.ticketPrice.ticketCategory = {};
            $scope.ticketPrice.ticketCategory.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The ticketPrice was created successfully.'});
            $location.path('/TicketPrices');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        TicketPriceResource.save($scope.ticketPrice, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/TicketPrices");
    };
});