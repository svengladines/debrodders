var $jq = jQuery.noConflict();

var baseURL = "http://www.debrodders.be/svekke/data";

var url = function ( sub ) {
	return baseURL + "/" + sub;
}

var previousMatchURL = function() {
	return url("previous-matches.json" );
}

var nextMatchURL = function() {
	return url("next-matches.json" );
}

var getPreviousMatch = function ( container, callback ) {
	
	$jq.ajax( {
		type: "get",
		url: previousMatchURL(),
		dataType: "json",
	    processData: false,
		success: function( matches ) {
			if ( callback ) {
				callback( container, matches );
			}
			else {
				// success( button, statusElement, "Opgeslagen" );
			}
		},
		error: function(  jqXHR, textStatus, errorThrown ) {
			console.log( errorThrown );
		}
	});
	
};

var getNextMatch = function ( container, callback ) {
	
	$jq.ajax( {
		type: "get",
		url: nextMatchURL(),
		dataType: "json",
	    processData: false,
		success: function( matches ) {
			if ( callback ) {
				callback( container, matches );
			}
			else {
				// success( button, statusElement, "Opgeslagen" );
			}
		},
		error: function(  jqXHR, textStatus, errorThrown ) {
			console.log( errorThrown );
		}
	});
	
};

var renderPreviousMatch = function( container, matches ) {
	var template = $jq("#previousMatchTemplate").html();
	var lastMatch = matches[ matches.length - 1 ]; 
	var html = Mustache.to_html(template, lastMatch );
	container.html( html );
};

var renderNextMatch = function( container, matches ) {
	var template = $jq("#nextMatchTemplate").html();
	var nextMatch = matches[ 0 ];
	nextMatch.date = moment( nextMatch.kickOff ).format('DD/MM/YYYY (HH:mm)');
	var html = Mustache.to_html(template, nextMatch );
	container.html( html );
};

var loadMatches = function () {
	getPreviousMatch( $jq("#previousMatch"), renderPreviousMatch );
	getNextMatch( $jq("#nextMatch"), renderNextMatch );
	return true;
}