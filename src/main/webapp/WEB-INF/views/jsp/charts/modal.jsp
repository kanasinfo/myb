<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="chartsCanvas">
    <div class="loading-text" style="padding: 0 30px;">数据努力加载中，请稍后...</div>
    <div class="charts-content" style="height: 600px; padding: 0 30px;">

    </div>
    <div class="charts-bar" style="position: absolute;
                                    bottom: 0;
                                    background: #707070;
                                    color: #ffffff;
                                    width: 100%;
                                    border-radius: 0 0 8px 8px; display: none;">
        <div id="cmt" style="margin: 10px 20px;"></div>
        <a href="#" id="copyCmt" data-clipboard-action="copy" data-clipboard-target="#cmt" style="
                        position: absolute;right: 3px;top: 5px; display: none;">
            <img src="${ctx}/assets/images/copy.png" alt="" style="width: 24px; height: 24px;" title="复制文字">
        </a>
    </div>
    <a href="#" style="position: absolute;
            color: #fff;
            top: 40%;
            left: -150px;" class="charts-left" data-fragement-id>
        <img src="${ctx}/assets/images/left_arrow.png" alt="" style="height: 120px; width: 50px;">
    </a>
    <a href="#" style="position: absolute;
            color: #fff;
            top: 40%;
            right: -150px;" class="charts-right" data-fragement-id>
        <img src="${ctx}/assets/images/right_arrow.png" alt="" style="height: 120px; width: 50px;">
    </a>
    <div style="position: absolute;
        bottom: -30px;
        width: 100%;
        text-align: center;
        color: #fff;
        font-size: 18px;
        letter-spacing: 0.2em;"><span id="dling"></span> <span id="index"></span> / <span id="size"></span></div>
</div>
<script type="text/javascript" src="${ctx}/assets/js/clipboard.js"></script>

<script>
    (function () {

        $('div.charts-bar').hover(function () {
            console.log('hover')
            $('#copyCmt').css('display', '');
        }, function () {
            $('#copyCmt').css('display', 'none');
        });


        var clipboard = new Clipboard('#copyCmt');

        clipboard.on('success', function (e) {
            console.log(e);
            toastr.success('文字已复制到剪切板!');
        });

        clipboard.on('error', function (e) {
            console.log(e);
        });

        if (window.downloadImgs) {
            $('#chartsCanvas a').hide();
        }
        var addDownloadImgs = function (image) {
            if (window.downloadImgs) {
                window.downloadImgs.push({
                    name: new Date().getTime() + '.png',
                    image: image.replace("data:image/png;base64,", "")
                });
            }
        };
        var loadModelData = function (fragementId) {
            if (fragementId) {
                $.ajax({
                    type: 'post',
                    url: '${ctx}/page/proreport/modal/' + fragementId + '?questionId=${questionnaireId}',
                    async: false,
                    cache: false,
                    success: function (req) {
                        $('span#index').text(req.data.index + 1);
                        $('span#size').text(req.data.size);
                        console.log('current fragementId: ' + req.data.fragementId);
                        window.currentFragement = req.data;
                        $('#chartsCanvas>a.charts-left').attr('data-fragement-id', req.data.left);
                        $('#chartsCanvas>a.charts-right').attr('data-fragement-id', req.data.right);
                        addDownloadImgs(req.data.image);
                        if (window.downloadImgs) {
                            $('span#dling').text('下载中, 当前进度');
                            loadCharts(req.data, function () {
                                setTimeout(function () {
                                    $('#chartsCanvas>a.charts-right').trigger('click');
                                }, 1500);

                            });
                        } else {
                            $('span#dling').text('');
                            loadCharts(req.data);
                        }
                    }
                });
            } else {
                if (window.downloadImgs) {
                    var zip = new JSZip();
                    var img = zip.folder("images");
                    $.each(window.downloadImgs, function (i, o) {
                        img.file(o.name, o.image, {base64: true});
                    });
                    zip.generateAsync({type: "blob"})
                        .then(function (content) {
                            saveAs(content, "charts-images.zip");
                        });
                    window.downloadImgs = null;
                    $.modal.close();
                }
            }
        };
        loadModelData('${fragementId}');

        $('#chartsCanvas>a.charts-left').on('click', function (e) {
            e.preventDefault();
            var left = window.currentFragement.left;
            loadModelData(left);
        });
        $('#chartsCanvas>a.charts-right').on('click', function (e) {
            e.preventDefault();
            var right = window.currentFragement.right;
            loadModelData(right);
        });


    })();
</script>