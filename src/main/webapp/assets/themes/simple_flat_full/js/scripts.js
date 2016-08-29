/*
Theme Name: Athena
Version: 1.0.0
Author: Mkscoder
Author URI: http://www.mkscoder.com
Description: One Page Multi-Purpose Theme by Mkscoder
*/

/*	IE 10 Fix*/

var doc = document.documentElement;
doc.setAttribute('data-useragent', navigator.userAgent);


$(document).ready(function($) {
    "use strict";

 
    /* ======= Animated Custom ======= */
        try {
            if ($(".animated")[0]) {
                $('.animated').css('opacity', '0');
            }
            $('.triggerAnimation').waypoint(function() {
                var animation = $(this).attr('data-animate');
                $(this).css('opacity', '');
                $(this).addClass("animated " + animation);

            }, {
                offset: '80%',
                triggerOnce: true
            });
        } catch (err) {

        }

});