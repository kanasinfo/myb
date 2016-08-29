$(function() {

  		var w = $(window), responsive_sliders;

  		responsive_sliders = function() {

			var after = {}, above_700 = w.width() >= 700;
			
		};

		responsive_sliders();
		if($('html').hasClass('no-touch'))
		{
			w.on('scroll', function(){

				if (width >= 800){
					var
					discover = $('#discover'),
					scrollTop = w.scrollTop(),
					offset = discover.offset().top - scrollTop - parseInt(discover.css('margin-top')),
					header = $('#head'),
					height = header.height(),
					x = offset - height < 0 ? offset - height : 0;

					header.css({'margin-top': x});
				}
			});
		}

		var width = $(window).width();
		var height = $(window).height();
		
	});
