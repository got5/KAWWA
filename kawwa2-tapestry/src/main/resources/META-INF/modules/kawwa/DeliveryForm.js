(function() {
  define(['t5/core/dom', 't5/core/zone', 't5/core/events'], function(dom, zone, events) {
  	return exports = function(spec) {
          jQuery('input[name=\"' + spec.modeName + '\"]')
              .attr('data-update-zone', spec.zoneId)
              .change( function () {

                  var parameters = {};
                  parameters['deliveryModeSelectedRef'] = jQuery('input[name=\"' + spec.modeName + '\"]:checked').val();

                  var z = zone.findZone(dom(jQuery(this).attr('id')));

                  z.trigger(events.zone.refresh, {
                      url: spec.link,
                      parameters: parameters
                  });
          });
      };
  });
})();
