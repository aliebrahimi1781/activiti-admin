if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
            $(this).fadeOut();
        });

        



        // jQuery.validator.addMethod(
        //                "date",
        //                function (value, element) {
                            // console.log(Date.parse(value));
                            // return this.optional(element) || Date.parse(value);
                        // },
                        // $.validator.messages.date
                    // );

        //initialize form validation on all forms
        //$('form').validate()

    })(jQuery);
};

$("img").error(function(){
        $(this).hide();
});

