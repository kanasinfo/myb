window.jQuery(document).ready(function($){
	
	'use strict';
	
	//-------------------- Main navigation menu affix function --------------------//
	
	$('.stickem-container').stickem();
	
	//-------------------- End main navigation menu affix function  --------------------//
	
	
	
	//-------------------- Responsive menu --------------------//
	
	// Create a top navigation menu for the responsive navigation
	$('#navigation .menu').mobileMenu({
		defaultText:'Please select one option....',
		className:'select-top-nav',
		subMenuDash:'&mdash;'
	});
	
	// Make the drop-down work
	$('#navigation select').change(function() {
		window.location = $(this).find('option:selected').val();
	});
	
	//-------------------- End responsive menu --------------------//
	
	//-------------------- jQuery tooltips --------------------//
	
	$('#header-top .pull-left .top-social-bar .social a').tooltip({placement: 'bottom'});
	 
	//-------------------- End jQuery tooltips --------------------//
	
	//-------------------- Customizer --------------------//
	
	$('#customize .popup-open').click(function() {
		$(this).prev().toggle();
	});
	
	$('#customize .colors-panel a').click(function(e) {
		var $color = $(this).attr('class');
		$('head').append('<link rel="stylesheet" type="text/css" href="css/colors/'+ $color +'/color.css">');
		e.preventDefault();
	});
	
	//-------------------- End customizer --------------------//
	
	//-------------------- Header Search --------------------//
	$(function () {
		function closeSearch() {
            var $form = $('.top-social-bar form[role="search"].active')
    		$form.find('input').val('');
			$form.removeClass('active');
		}

		// Show Search if form is not active // event.preventDefault() is important, this prevents the form from submitting
		$(document).on('click', '.top-social-bar form[role="search"]:not(.active) button[type="submit"]', function(event) {
			event.preventDefault();
			var $form = $(this).closest('form'),
				$input = $form.find('input');
			$form.addClass('active');
			$input.focus();
		});
		// ONLY FOR DEMO // Please use $('form').submit(function(event)) to track from submission
		// if your form is ajax remember to call `closeSearch()` to close the search container
		$(document).on('click', '.top-social-bar form[role="search"].active button[type="submit"]', function(event) {
			event.preventDefault();
			var $form = $(this).closest('form'),
				$input = $form.find('input');
			$('#showSearchTerm').text($input.val());
            closeSearch()
		});
    });
	//-------------------- End Header Search --------------------//
	
	//-------------------- Back to top scroll --------------------//
	$(document).ready(function(){
		$(window).scroll(function () {
            if ($(this).scrollTop() > 200) {
                $('#back-to-top').fadeIn();
            } else {
                $('#back-to-top').fadeOut();
            }
        });
        // scroll body to 0px on click
        $('#back-to-top').click(function () {
            $('#back-to-top').tooltip('hide');
            $('body,html').animate({
                scrollTop: 0
            }, 2000);
            return false;
        });
        
        $('#back-to-top').tooltip('show');

	});
	
	//-------------------- End back to top scroll --------------------//
	
	//-------------------- Twitter integration with jQuery --------------------//
	
	/*
	$.getJSON('includes/get-tweets.php',
        function(feeds) {
            // alert(feeds);
			var displaylimit		= 2;
			var showdirecttweets	= false;
			var showretweets		= true;
            var feedHTML			= '';
            var displayCounter		= 1;
			var $tweets				= $('.etrybiz_tweets_widget');
			
			if(feeds !== null) {
				for (var i=0; i<feeds.length; i++) {
					var tweetscreenname	= feeds[i].user.name;
					var tweetusername	= feeds[i].user.screen_name;
					var profileimage	= feeds[i].user.profile_image_url_https;
					var status			= feeds[i].text;
					var isaretweet		= false;
					var isdirect		= false;
					var tweetid			= feeds[i].id_str;
	 
					// If the tweet has been retweeted, get the profile pic of the tweeter
					if (typeof feeds[i].retweeted_status !== 'undefined') {
						profileimage	= feeds[i].retweeted_status.user.profile_image_url_https;
						tweetscreenname	= feeds[i].retweeted_status.user.name;
						tweetusername	= feeds[i].retweeted_status.user.screen_name;
						tweetid			= feeds[i].retweeted_status.id_str;
						isaretweet		= true;
					}
					
					// Check to see if the tweet is a direct message
					if (feeds[i].text.substr(0,1) === '@') {
						isdirect = true;
					}
					
					// console.log(feeds[i]);
					
					if (((showretweets === true) || ((isaretweet === false) && (showretweets === false))) && ((showdirecttweets === true) || ((showdirecttweets === false) && (isdirect === false)))) {
						if ((feeds[i].text.length > 1) && (displayCounter <= displaylimit)) {
	 
							if (displayCounter === 1) {
								feedHTML = '';
							}
							
							feedHTML	+= '<li>';
							feedHTML	+= '<div class="etrybiz">';
							feedHTML	+= '<img src="' + profileimage + '" alt="Etrybiz" /><a href="http://twitter.com/' + tweetusername + '/status/' + tweetid + '" target="_blank"><i class="fa fa-link"></i></a>';
							feedHTML	+= '</div>';
							feedHTML	+= '<p>' + JQTWEET.ify.clean(status) + '</p>';
							if (JQTWEET.timeAgo(feeds[i].created_at) !== '') {
								feedHTML += '<div class="date"><a href="http://twitter.com/' + tweetusername + '/status/' + tweetid + '" target="_blank">' +  JQTWEET.timeAgo(feeds[i].created_at) + '</a></div>';
							}
							feedHTML	+= '</li>';
							
							displayCounter++;
						}
					}
				}
	 
				$tweets.html(feedHTML);
				$tweets.hide().fadeIn(1000);
			}
		}
	); */
	
	// Twitter data format function
	var JQTWEET = {
		timeAgo: function(dateString) { // twitter date string format function
			var rightNow = new Date();
			var then = new Date(dateString);
			
			if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
				jQuery.browser.msie = true;
				jQuery.browser.version = RegExp.$1;
			}
			
			var diff = rightNow - then;
			var second = 1000,
			minute = second * 60,
			hour = minute * 60,
			day = hour * 24;
	 
			if (isNaN(diff) || diff < 0) { return ""; }
			if (diff < second * 2) { return "right now"; }
			if (diff < minute) { return Math.floor(diff / second) + " seconds ago"; }
			if (diff < minute * 2) { return "1 minute ago"; }
			if (diff < hour) { return Math.floor(diff / minute) + " minutes ago"; }
			if (diff < hour * 2) { return "1 hour ago"; }
			if (diff < day) { return  Math.floor(diff / hour) + " hours ago"; }
			if (diff > day && diff < day * 2) { return "1 day ago"; }
			if (diff < day * 365) { return Math.floor(diff / day) + " days ago"; }
			else { return "over a year ago"; }
		}, // timeAgo()
		 
		ify: {
			link: function(tweet) { // twitter link string replace function
				return tweet.replace(/\b(((https*\:\/\/)|www\.)[^\"\']+?)(([!?,.\)]+)?(\s|$))/g, function(link, m1, m2, m3, m4) {
					var http = m2.match(/w/) ? 'http://' : '';
					return '<a class="twtr-hyperlink" target="_blank" href="' + http + m1 + '">' + ((m1.length > 25) ? m1.substr(0, 24) + '...' : m1) + '</a>' + m4;
				});
			},
			
			at: function(tweet) { // twitter at (@) character format function
				return tweet.replace(/\B[@@]([a-zA-Z0-9_]{1,20})/g, function(m, username) {
					return '<a target="_blank" class="twtr-atreply" href="http://twitter.com/intent/user?screen_name=' + username + '">@' + username + '</a>';
				});
			},
			
			list: function(tweet) { // twitter list string format function
				return tweet.replace(/\B[@@]([a-zA-Z0-9_]{1,20}\/\w+)/g, function(m, userlist) {
					return '<a target="_blank" class="twtr-atreply" href="http://twitter.com/' + userlist + '">@' + userlist + '</a>';
				});
			},
			
			hash: function(tweet) { // twitter hash (#) string format function
				return tweet.replace(/(^|\s+)#(\w+)/gi, function(m, before, hash) {
					return before + '<a target="_blank" class="twtr-hashtag" href="http://twitter.com/search?q=%23' + hash + '">#' + hash + '</a>';
				});
			},
			
			clean: function(tweet) { // twitter clean all string format function
				return this.hash(this.at(this.list(this.link(tweet))));
			}
		} // ify
	};
	
	//-------------------- End twitter integration with jQuery --------------------//
		
});