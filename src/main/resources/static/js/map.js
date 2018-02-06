"use strict";
(function () {


    //     var map;
    //
    //     function initMap() {
    //     map = new google.maps.Map(document.getElementById('map'), {
    //         center: {lat: 29.301674, lng: -98.173226},
    //         zoom: 20
    //     });
    // }
    //     initMap();


// ============== BUILD MAP
    var mapOptions = {
        zoom: 15,
        center: {
            lat:  29.301674,
            lng: -98.173226
        }
    };

    var daycare = [
        {
            name: "Discovery Play House",
            address: "7218 FM 775, La Vernia, TX 78121",
            info: "The best child care provider in the area!"
        }


    ];

    function initMap(optionsObject) {
        return new google.maps.Map(document.getElementById("map"), optionsObject);
    }

    var map = initMap(mapOptions);


    //  ============== GEOCODING

    function getLocation(childCareOpt) {

        var geocoder = new google.maps.Geocoder();

        geocoder.geocode({address: childCareOpt.address}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var position = results[0].geometry.location;
                addMarkerAndInfoWindow(position, childCareOpt.name, childCareOpt.info);
            } else {
                alert("Geocoding unsuccessful");
            }
        });

    };

    getLocation(daycare[0]);


    //  ============== ADD MARKER AND INFO WINDOW

    function addMarkerAndInfoWindow(position, name, infoText) {
        console.log(position);
        console.log(name);
        console.log(infoText);


        var marker = new google.maps.Marker({
            position: position,
            map: map
        });

        var infoWindowContent = '<h3>' + name + '</h3>';
        infoWindowContent += '<p>' + infoText + '</p>';

        var infoWindow = new google.maps.InfoWindow({
            content: infoWindowContent
        });

        infoWindow.open(map, marker);

    };




}) ()


