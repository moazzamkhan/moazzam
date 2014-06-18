// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.
var infowindow;
var marker;
var geocoder;
function initialize() {
	initializeMap();
	initializeBootstrap();
}

function initializeMap() {
	geocoder=new google.maps.Geocoder();
	var markers = [];
	var map = new google.maps.Map(document.getElementById('map-canvas'), {
		zoom : 13,
		center : new google.maps.LatLng(55.9520600, -3.1964800)
	});

	// Create the search box and link it to the UI element.
	var input = /** @type {HTMLInputElement} */
	(document.getElementById('pac-input'));

	var types = document.getElementById('type-selector');

	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);
	var searchBox = new google.maps.places.SearchBox(
	/** @type {HTMLInputElement} */
	(input));

	// [START region_getplaces]
	// Listen for the event fired when the user selects an item from the
	// pick list. Retrieve the matching places for that item.
	google.maps.event.addListener(searchBox, 'places_changed', function() {
		var places = searchBox.getPlaces();

		for (var i = 0, marker; marker = markers[i]; i++) {
			marker.setMap(null);
		}

		// For each place, get the icon, place name, and location.
		markers = [];
		var bounds = new google.maps.LatLngBounds();
		for (var i = 0, place; place = places[i]; i++) {
			var image = {
				url : place.icon,
				size : new google.maps.Size(71, 71),
				origin : new google.maps.Point(0, 0),
				anchor : new google.maps.Point(17, 34),
				scaledSize : new google.maps.Size(35, 35)
			};

			// Create a marker for each place.
			var marker = new google.maps.Marker({
				map : map,
				icon : image,
				title : place.name,
				position : place.geometry.location
			});

			markers.push(marker);

			bounds.extend(place.geometry.location);
		}

		map.fitBounds(bounds);
	});
	// [END region_getplaces]

	// Bias the SearchBox results towards places that are within the bounds of
	// the
	// current map's viewport.
	google.maps.event.addListener(map, 'bounds_changed', function() {
		var bounds = map.getBounds();
		searchBox.setBounds(bounds);
	});

	function getReverseGeoCode(latlng, infowindow, map, marker) {
		geocoder.geocode({
			'latLng' : latlng
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[1]) {
					infowindow.setContent(results[1].formatted_address);
					infowindow.open(map, marker);
				} else {
					alert('No results found');
				}
			} else {
				alert('Geocoder failed due to: ' + status);
			}
		});
	}

	google.maps.event.addListener(map, 'click', function(e) {
		if (inputOn || true) {
			marker = new google.maps.Marker({
				position : e.latLng,
				map : map
			});

			infowindow = new google.maps.InfoWindow(/*{
				// content : $("#user-input-form").html()
				content : ""
			}*/);
			getReverseGeoCode(e.latLng, infowindow, map, marker);

//			infowindow.open(map, marker);

			inputOn = false;
		}
	});
}

google.maps.event.addDomListener(window, 'load', initialize);

var inputOn = false;

function initializeBootstrap() {
	var btn = document.querySelector("#user-input");
	btn.addEventListener("click", function() {
		infowindow && infowindow.close()
		marker && marker.setMap(null);
		inputOn = true;
	}, false);
}