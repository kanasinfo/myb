/**
 * Created by gefangshuai on 2017/1/13.
 */
(function(){
    $('#saveReportImg').on('click', function(e){
        e.preventDefault();
        var $canvas = $('div.chart-main canvas');
        var img = Canvas2Image.convertToImage($canvas[0]);
        var $div = $('div.thumb-img').first().remove('img').clone(true);
        $div.prepend(img);
        $div.removeClass('hide')
        $('div.right-center').append($div)
    });

    $('div.thumb-img').on('mouseover', function(){
        var $this = $(this);
        $this.find('a').css('display', 'inline');
    }).on('mouseout', function(){
        var $this = $(this);
        $this.find('a').css('display', 'none');
    })
})();