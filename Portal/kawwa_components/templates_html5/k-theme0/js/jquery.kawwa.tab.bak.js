(function($) {
    $.fn.kTabs = function(options) {
        var settings = $.extend({
            'selected' : '0',
            'disabled' : null
        }, options);

        return this.each(function() {
            var $this = $(this);
            var $tabsList = $this.find('ul.tabs');
            var $tabsPres = $this.find('ul.tabs li');
            var $tabs = $this.find('ul.tabs a');
            var $panels = $this.find('.content');
            var tabHref;
            var tabHeight;
            var tabId;
            var panelId;

            // PREPARING CONTENT...
            $panels.css('display', 'none');

            // If tabs disabled, replaces link
            if(settings.disabled != null) {
                var $father = $tabs.eq(settings.disabled).parent('li');
                var fatherHtml = $father.text();
                fatherHtml = "<strong class=\"off\">" + fatherHtml + "</strong>";
                $father.html(fatherHtml);
            }

            // Add ARIA roles/states
            $tabsList.attr('role', 'tablist');
            $tabsPres.attr('role', 'presentation');
            $panels.attr({
                'aria-hidden' : 'true',
                'aria-expanded' : 'false'
            });

            // SETS ARIA CONTROLS
            $tabs.attr({
                'role' : 'tab',
                'aria-selected' : 'false',
                'tabindex' : '-1'
            });
            $tabs.each(function(index) {
                tabId = "tab" + index;
                tabHref = $(this).attr('href');
                $(this).attr({
                    'id' : tabId,
                    'aria-controls' : tabHref.replace('#', '')
                });
                $(tabHref).attr({
                    'role' : 'tabpanel',
                    'aria-labelledby' : tabId
                });
            });
            // SETS ACTIVE PANEL
            if($this.find('a.active').length != 0) {
                $theOne = $this.find('a.active');
            } else {
                $theOne = $tabs.eq(settings.selected);
                $theOne.addClass('active');
            }
            $theOne.attr('aria-selected', 'true').attr('tabindex', '0');
            panelId = $theOne.attr('aria-controls');
            $('#' + panelId).css('display', 'block').attr({
                'aria-hidden' : 'false',
                'aria-expanded' : 'true'
            });

            // CONTROL EVENTS
            $tabs.click(function(event) {
                event.preventDefault();
                $tabs.toggleClass('transformed');

                if(!$(this).hasClass("active")) {
                    // Close all
                    $panels.css('display', 'none').attr({
                        'aria-hidden' : 'true',
                        'aria-expanded' : 'false'
                    });
                    $tabs.removeClass('active').attr('aria-selected', 'false').attr('tabindex', '-1');
                    // Open clicked
                    $(this).addClass('active').attr('aria-selected', 'true').attr('tabindex', '0');

                    panelId = $(this).attr('aria-controls');

                    $('#' + panelId).css('display', 'block').attr({
                        'aria-hidden' : 'false',
                        'aria-expanded' : 'true'
                    });

                    if($('#' + panelId).parent().hasClass('adaptive')) {
                        tabHeight = $('a.transformed.active').height() + 20;
                        $('#' + panelId).css('padding-top', tabHeight);
                    }

                }
            });


            // RESPONSIVE BEHAVIOUR
            var resizer = function() {
                $this.removeClass('adaptive');
                if($tabsPres.eq(0).offset().top != $tabsPres.eq($tabsPres.length - 1).offset().top) {
                    $this.addClass('adaptive');
                    $tabs.addClass('transformed');
                    tabHeight = $('a.transformed.active').height() + 20;
                    $this.find('> .content').css('padding-top', tabHeight);
                } else {
                    $tabs.removeClass('transformed');
                    $this.find('> .content').css('padding-top', '5px');
                }
            }
            // Call once to set.
            resizer();

            // Call on resize.
            $(window).resize(resizer);
        });
    };
})(jQuery);