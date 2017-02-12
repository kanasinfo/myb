/**
 * Created by gefangshuai on 2017/1/13.
 */
(function () {
    $(document).on('click', function (e) {
        if (!$(e.target).is('div.popover') && !$(e.target).is('div.popover-content') && !$(e.target).is('div.popover-content>input') && !$(e.target).is('div.popover-content>div>input') && !$(e.target).is('button.edit-cmt'))
            $('div.thumb-img').popover('hide');
    });

    /**
     * 推送数据到后台
     * @param chartsData
     * @param img
     */
    var pushChartsData = function (chartsData, img) {
        console.log(img);
        var data = JSON.stringify(chartsData);
        console.log('save charts: ' + JSON.stringify(reportGlobal.postdata));
        var postData = {
            questionnaireId: reportGlobal.id,
            data: data,
            image: img.src,

            questionId: reportGlobal.postdata.question_id,
            page: reportGlobal.postdata.page,
            filter: reportGlobal.postdata.filter,
            dimensiontype: reportGlobal.postdata.dimensiontype,
            dimension: reportGlobal.postdata.dimension,
            questionGroup: reportGlobal.postdata.questionGroup,
            groupId: reportGlobal.postdata.groupId,
            questionName: reportGlobal.postdata.questionName,
            specialQuestions: reportGlobal.postdata.specialQuestions,
            store: reportGlobal.postdata.store
        };
        $.post(reportGlobal.ctx + '/page/proreport/saveChartsFragment', postData, function (data) {
            if (data.success) {
                var fragement = data.data;
                console.log(fragement);
                drawImg(fragement.fragementId, fragement.comment, img);
                console.log('drawImg: ' + data.message);
            }
        });
    };

    /**
     * 画图
     * @param id
     * @param img
     */
    var drawImg = function (id, comment, img) {
        console.log('comment: ' + comment);
        var $div = $('div.thumb-img').first().remove('img').clone(true);
        var $a_img = $div.children('a.a-img');
        $a_img.attr('href', reportGlobal.ctx + '/page/proreport/modal/' + id + "?questionId=" + reportGlobal.id);
        $a_img.html(img);
        $div.prepend($a_img[0]);
        $div.removeClass('hide');
        $div.attr('data-id', id);
        $div.attr('data-comment', comment);
        $div.find('a.fancybox-close').attr('data-id', id);
        $div.find('a.fancybox-edit').attr('data-id', id);
        $('div.right-center').append($div)
    };

    /**
     * 初始化侧边图片
     * @param jsondata
     */
    var buildChartsFragementsSlide = function (jsondata) {
        console.log('buildChartsFragementsSlide');
        $('div.thumb-img').each(function (i, o) {
            if (i > 0)
                $(o).remove();
        });
        reportGlobal.chartsFragements = jsondata.chartsFragements;
        $.each(reportGlobal.chartsFragements, function (i, o) {
            var img = document.createElement('img');
            img.src = o.image;
            drawImg(o.fragementId, o.comment, img);
        });
    };

    /**
     * 保存图片按钮
     */
    $('#saveReportImg').on('click', function (e) {
        e.preventDefault();
        var $canvas = $('div.chart-main canvas');
        var img = Canvas2Image.convertToImage($canvas[0]);
        pushChartsData(reportGlobal.chartsData, img);
    });

    $('div.thumb-img').hover(function () {
        var $this = $(this);
        var comment;
        $.ajax({
            type: 'post',
            url: reportGlobal.ctx + '/page/proreport/modal/' + $this.data('id') + '?questionId=' + reportGlobal.id,
            async: false,
            cache: false,
            success: function (req) {
                console.log(req);
                comment = req.data.comment == null ? '': req.data.comment;
            }
        });
        console.log('load commnet:' + comment);
        $this.attr('data-comment', comment);
        var cmtHtml = comment ?
            '<div class="div-cmt">' + comment + ' <button class="edit-cmt" data-id="' + $this.data('id') + '" data-comment="' + comment + '">编辑</button> </div>'
            :
            '<input type="text" placeholder="添加批注" value="' + comment + '"> <button class="save-cmt" data-id="' + $this.data('id') + '">保存</button>';
        console.log('cmtHtml: ' + cmtHtml);

        $this.find('a.fancybox-close').css('display', 'inline');
        $this.find('a.fancybox-edit').css('display', 'inline');
        $this.popover({
            placement: 'left',
            html: true,
            content: cmtHtml,
            container: 'body'
        }).popover('show');
        $('div.popover-content').empty().html(cmtHtml);
        $('div.thumb-img').each(function (i, o) {
            if ($(o).data('id') != $this.data('id')) {
                $(o).popover('hide');
            }
        });
    }, function () {
        var $this = $(this);
        $this.find('a.fancybox-close').css('display', 'none');
        $this.find('a.fancybox-edit').css('display', 'none');
    });

    $('body').on('click', 'button.edit-cmt', function () {
        var $this = $(this);
        $this.closest('div.div-cmt').html(
            '<input type="text" placeholder="添加批注" value="' + $this.data('comment') + '"> <button class="save-cmt" data-id="' + $this.data('id') + '">保存</button>'
        );
        return false;
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
        }, function (data) {
            if (data.success) {
                console.log(data.message);
                $this.closest('div.thumb-img').remove();
            }
        });
    });

    $('a.fancybox-edit').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var $thumb = $this.closest('div.thumb-img');
        var comment = $thumb.data('comment') || '';
        $thumb.popover({
            placement: 'left',
            html: 'true',
            content: '<input type="text" placeholder="添加批注" value="' + comment + '"> <button class="save-cmt" data-id="' + $this.data('id') + '">保存</button>',
            container: 'body'
        }).popover('show');
    });

    /**
     * 保存批注
     */
    $('body').on('click', 'button.save-cmt', function () {
        var $this = $(this);
        var $inp = $this.siblings('input');
        var comment = $inp.val();
        if (!comment) {
            toastr.error('请填写内容!');
            return false;
        }
        if (comment.length > 30) {
            toastr.error('内容不要超过30个字符!')
            return false;
        }
        console.log('保存批注：' + comment);
        $.post(reportGlobal.ctx + '/page/proreport/comment/' + $this.data('id'), {
            questionId: reportGlobal.id,
            comment: comment
        }, function () {
            $('div.thumb-img').popover('hide');
            toastr.success('保存成功!');
        });
    });

    /**
     * 打开图表
     */
    $('a[rel="modal:open"]').click(function (event, args) {
        var $this = $(this);
        $('div.thumb-img').popover('hide');
        if (args && args == 'download') {     // 文件自动下载
            window.downloadImgs = [];
            $.modal.defaults.showClose = false;
        } else {
            $.modal.defaults.showClose = true;
            window.downloadImgs = null;
            event.preventDefault();
            var url = $this.attr('href');
            $.get(url, function (html) {
                $(html).modal();
                return false;
            });
        }
    });

    var drawCurrentCharts = function (data) {
        console.log('load charts: ' + JSON.stringify(data));
        var $currentChart = $('#chartsCanvas>div.charts-content');

        var chart = echarts.init($currentChart[0]);
        var option = data.option;
        option.title = {
            text: data.title,
            left: 'middle'
        };
        chart.setOption(typeof(option) == 'string' ? _string2Json(option) : option);
        console.log('cmt: ' + data.comments);
        if (data.comments && data.comments.length > 0) {
            $('div#cmt').text(data.comments);
            $('#cmtValInput').val(data.comments);
            $('#chartsCanvas div.charts-bar').css('display', '');
        } else {
            $('#chartsCanvas div.charts-bar').css('display', 'none');
            $('div#cmt').text(data.comments);
            $('#cmtValInput').val('');
        }
        $('#chartsCanvas div.loading-text').hide();
    };

    /**
     * 画图
     */
    var loadCharts = function (postdata, callback) {
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
            url: reportGlobal.ctx + '/page/reportEchart/getChartAllInfo.json?_' + new Date().getTime(),
            data: postdata,
            dataType: 'json',
            cache: false,
            success: function (data) {
                console.log(data);
                drawCurrentCharts(data.data);
                if (typeof callback == 'function') {
                    callback();
                }
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