(function() {
  requirejs.config({
  	'shim' : {
  		'kawwa/vendor/gmap3': ['jquery', 'http://maps.googleapis.com/maps/api/js?sensor=false']
  	}
  });
  define(['kawwa/vendor/gmap3'], function() {
  	return exports = function(spec) {
  	    jQuery('#' + spec.gmapId).gmap3({
            map:{
              options:{
                zoom: 5
              }
            },
            marker:{
              values: spec.gmapMarkers,
              options:{
                draggable: false
              },
              events:{
                click: function(marker, event, data){
                    var map = jQuery(this).gmap3('get');
                    map.setCenter(marker.getPosition());
                },
                mouseover: function(marker, event, context){
                  var map = $(this).gmap3("get"),
                    infowindow = $(this).gmap3({get:{name:"infowindow"}});
                  if (infowindow){
                    infowindow.open(map, marker);
                    infowindow.setContent(context.data);
                  } else {
                    $(this).gmap3({
                      infowindow:{
                        anchor:marker,
                        options:{content: context.data}
                      }
                    });
                  }
                },
                mouseout: function(){
                  var infowindow = $(this).gmap3({get:{name:"infowindow"}});
                  if (infowindow){
                    infowindow.close();
                  }
                }
              }
            }
          });

          jQuery('input[name=\"' + spec.locationName + '\"]').change( function () {
              var location_value = $("input[name=\"" + spec.locationName + "\"]:checked").val();
              var jMap = jQuery('#' + spec.gmapId);
              var  marker = $("#" + spec.gmapId).gmap3({ get: { name:'marker', tag:location_value}});
              console.log(marker);
              var map = jMap.gmap3('get');

              jQuery.each(jMap.gmap3({get: { name: 'marker', all: true}}), function(i, currentMarker){
                  if(currentMarker != marker){currentMarker.setMap(null);}
              });

              marker.setMap(map);

              map.setCenter(marker.getPosition());
          });
      };
  });
})();

