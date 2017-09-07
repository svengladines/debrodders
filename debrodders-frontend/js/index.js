var $jq = jQuery.noConflict();

var renderMatchStatus = function( matchStatus ) {

    $jq("#homeTeamName").html( matchStatus.homeTeam.name );
    $jq("#homeGoals").html( matchStatus.homeGoals );
    $jq("#visitorTeamName").html( matchStatus.visitorTeam.name );
    $jq("#visitorGoals").html( matchStatus.visitorGoals );
    $jq("#status").html( matchStatus.status );
    $jq("#announcement").html( matchStatus.announcement );

};

var loadMatchStatus = function() {

    $jq.getJSON( "http://www.debrodders.be/svekke/debrodders-board.json", { format: "json", x:"y" })
	    .done(function( matchStatus ) {
			renderMatchStatus( matchStatus);
		})
  	    .always(function( xhr ) {
			var i = xhr;
		})
	.fail(function( jqxhr, textStatus, error ) {
    var err = textStatus + ", " + error;
    console.log( "Request Failed: " + err );
})

};

jQuery( document ).ready(function() {

	loadMatchStatus();

});
