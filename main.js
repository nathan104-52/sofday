function getDirections() {
    // Get the user's location.
    var userLocation = navigator.geolocation.getCurrentPosition(function(position) {
      // Get the directions from the user's location to your location.
      var directionsService = new google.maps.DirectionsService();
      var directionsRequest = {
        origin: position.coords.latitude + "," + position.coords.longitude,
        destination: "123 Main Street, Anytown, CA 91234"
      };
      directionsService.route(directionsRequest, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
          // Display the directions on the map.
          var directionsRenderer = new google.maps.DirectionsRenderer();
          directionsRenderer.setMap(map);
          directionsRenderer.setDirections(response);
        } else {
          console.log("Error getting directions: " + status);
        }
      });
    }, function(error) {
      console.log("Error getting user location: " + error.code);
    });
  }