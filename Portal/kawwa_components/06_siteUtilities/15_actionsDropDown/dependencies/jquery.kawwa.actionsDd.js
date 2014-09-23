/* KAWWA - May, 2013
 *
 * A dropdown list of actions
 */


(function($) {
    $.fn.actionsDd = function(options) {
        var $this = $(this);

        $this.each(function() {

            var $zControl = $(this).children('button');

            var zListId = 'list' + Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
            var zList = $(this).find('div.content');

            // ARIA
            zList.attr('id', zListId);
            $zControl.attr('aria-owns', zListId);
            $zControl.attr('aria-expanded', false);

            // Prepares list
            zList.css('display', 'none');

            zList.find('a').click(function(){
                zList.slideToggle('fast');
            });
            // Activates control
            $zControl.click(function(event){
                event.preventDefault();
                zList.slideToggle('fast');
                $(this).toggleClass('active');
                $(this).attr('aria-expanded', $(this).hasClass('active'));
            });
        });

    };

})(jQuery);