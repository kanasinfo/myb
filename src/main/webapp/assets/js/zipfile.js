(function (obj) {
    var zip = new JSZip();

    $('#downloadCharts').on('click', function (e) {
        e.preventDefault();
        $($('a.a-img')[1]).trigger('click', ['download']);
    })
})(this);
