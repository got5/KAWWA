(function($){
	$.extend(Tapestry.Initializer, {
		gmapselect: function(specs){

			$("#" + specs.gmapId).gmap3(
				{ action:'init',
					options:{
						zoom: 5
					}
				},
				{ action: 'addMarkers',
					markers: specs.gmapMarkers,
					marker:{
						options:{
							draggable: false
						},
						events:{
							click: function(marker, event, data){
								var map = $(this).gmap3('get');

								map.setCenter(marker.getPosition());
							},
							mouseover: function(marker, event, data){
								var map = $(this).gmap3('get'),
								infowindow = $(this).gmap3({action:'get', name:'infowindow'});
								if (infowindow){
									infowindow.open(map, marker);
									infowindow.setContent(data);
								} else {
									$(this).gmap3({action:'addinfowindow', anchor:marker, options:{content: data}});
								}
							},
							mouseout: function(){
								var infowindow = $(this).gmap3({action:'get', name:'infowindow'});
								if (infowindow){
									infowindow.close();
								}
							}
						}
					}
				},
				{ action: 'autofit'}
			);
			
			$("input[name=\"" + specs.locationName+"\"]").change( function () {
				
				var location_value = $("input[name=\"" + specs.locationName + "\"]:checked").val();
				
				var marker = $("#" + specs.gmapId).gmap3({ action:'get', name:'marker', tag:location_value});
				
				var map = $("#" + specs.gmapId).gmap3('get');
				
				var everyMarkers = $("#" + specs.gmapId).gmap3({ action: 'get', name: 'marker', all: true});
				$.each(everyMarkers, function(i, currentMarker){
					if(currentMarker != marker){currentMarker.setMap(null);}
				});
				marker.setMap(map);

				map.setCenter(marker.getPosition());				
			});
        }
	});
})(jQuery);