(function($){
    'use strict';
    $(document).ready(function(){
        $('a.bt-print').click(function(){
            window.print();
            return false;
        });
    });
})(jQuery);
