/**
 * Created by gefangshuai on 2017/1/13.
 */
(function(){
    /**
     * 推送数据到后台
     * @param chartsData
     * @param img
     */
    var pushChartsData = function (chartsData, img) {
        console.log(reportGlobal)
        var postData = {
            questionId: reportGlobal.id,
            data: JSON.stringify(chartsData),
            image: img.src,

            questionnaireId: reportGlobal.postdata.questionnaire_id,
            page: reportGlobal.postdata.page,
            filter: reportGlobal.postdata.filter,
            dimensiontype: reportGlobal.postdata.dimensiontype,
            dimension: reportGlobal.postdata.dimension,
            groupId: reportGlobal.postdata.groupId,
            questionName: reportGlobal.postdata.questionName,
            specialQuestions: reportGlobal.postdata.specialQuestions,
            store: reportGlobal.postdata.store
        };
        $.post(reportGlobal.ctx + '/page/proreport/saveChartsFragment', postData, function (data) {
            if(data.success) {
                drawImg(data.data, img);
                console.log(data.message);
            }
        });
    };

    /**
     * 画图
     * @param id
     * @param img
     */
    var drawImg = function (id, img) {
        var $div = $('div.thumb-img').first().remove('img').clone(true);
        var $a_img = $div.children('a');
        $a_img.attr('href', reportGlobal.ctx+'/page/proreport/modal/' + id + "?questionId=" + reportGlobal.id);
        $a_img.html(img);
        $div.prepend($a_img[0]);
        $div.removeClass('hide');
        $div.find('a.fancybox-close').attr('data-id', id);
        $('div.right-center').append($div)
    };

    /**
     * 初始化侧边图片
     * @param jsondata
     */
    var buildChartsFragementsSlide = function (jsondata) {
        reportGlobal.chartsFragements = jsondata.chartsFragements;
        $.each(reportGlobal.chartsFragements, function (i, o) {
            var img = document.createElement('img');
            img.src = o.image;
            drawImg(o.fragementId, img);
        });
    };

    /**
     * 保存图片按钮
     */
    $('#saveReportImg').on('click', function(e){
        e.preventDefault();
        var $canvas = $('div.chart-main canvas');
        var img = Canvas2Image.convertToImage($canvas[0]);
        pushChartsData(reportGlobal.chartsData, img);
    });

    $('div.thumb-img').on('mouseover', function () {
        var $this = $(this);
        $this.find('a.fancybox-close').css('display', 'inline');
    }).on('mouseout', function () {
        var $this = $(this);
        $this.find('a.fancybox-close').css('display', 'none');
    });

    /**
     * 删除侧边图片
     */
    $('.fancybox-close').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);

        // 后台删除数据
        $.get(reportGlobal.ctx + "/page/proreport/deleteChartsFragment", {
            id: reportGlobal.id,
            fragementId: $this.data('id')
        }, function(data){
            if(data.success) {
                console.log(data.message);
                $this.closest('div.thumb-img').remove();
            }
        });
    });

    var drawCurrentCharts = function (data) {
        console.log(data);
        var currentChart = echarts.init($('#chartsCanvas>div.charts-content')[0]);
        var option = data.option;


        option.title = {
            text: data.title,
            left: 'middle'
        };
        currentChart.setOption(option);
        $('#chartsCanvas div.charts-bar').text(data.comments).css('display', '');
        $('#chartsCanvas div.loading-text').hide();
    };

    /**
     * 画图
     */
    var loadCharts = function (postdata) {
        $('#chartsCanvas div.loading-text').show();
        $('#chartsCanvas div.charts-content').empty();
        var postdata = {
            questionnaire_id: postdata.questionnaireId,
            question_id: postdata.questionId,
            page: postdata.page,
            filter: postdata.filter,
            dimensiontype: postdata.dimensiontype,
            dimension: postdata.dimension,
            questionGroup: postdata.questionGroup,
            groupId: postdata.groupId,
            questionName: postdata.questionName,
            specialQuestions: postdata.specialQuestions,
            store: postdata.store
        };

        reportGlobal.postdata = postdata;

        $.ajax({
            type: 'post',
            url: reportGlobal.ctx + '/page/reportEchart/getChartAllInfo.json',
            data: postdata,
            dataType: 'json',
            cache: false,
            success: function (data) {
                drawCurrentCharts(data.data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.readyState);
                console.log(textStatus);
            }
        });
    };

    window.buildChartsFragementsSlide = buildChartsFragementsSlide;
    window.loadCharts = loadCharts;
})();