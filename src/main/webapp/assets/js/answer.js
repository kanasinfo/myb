jQuery(function($) {
	var data = decodeURIComponent($("#data").val());
	var data1 = JSON.parse(data);

    $('.abcd').rating({
        min: 0,
        max: 10,
        step: 1,
        size: 'xs',
        stars:10,
        showClear: false,
        clearButtonTitle:false,
        starCaptions:{
            1: '1分',
            2: '2分',
            3: '3分',
            4: '4分',
            5: '5分',
            6: '6分',
            7: '7分',
            8: '8分',
            9: '9分',
            10: '10分'
        }
    });
    $('.abcd').on('rating.change', function(event, value, caption,target) {
        $('#'+event.currentTarget.attributes.id.nodeValue).rating('update', value);
        console.log(value);
        console.log(caption);
        alert($("#"+event.currentTarget.attributes.id.nodeValue).val());

    });
	
	
})