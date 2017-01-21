<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="chartsCanvas">
    <div class="loading-text" style="padding: 0 30px;">数据努力加载中，请稍后...</div>
    <div class="charts-content" style="height: 600px; padding: 0 30px;">

    </div>
    <div class="charts-bar" style="position: absolute;
                                    padding: 10px 20px;
                                    bottom: 0;
                                    background: #707070;
                                    color: #ffffff;
                                    border-radius: 0 0 8px 8px; display: none;">

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
</div>
<script>
    (function () {
        var loadModelData = function (fragementId) {
            if (fragementId) {
                $.post("${ctx}/page/proreport/modal/" + fragementId + "?questionnaireId=${questionnaireId}", {}, function (req) {
                    console.log('current fragementId: ' + req.data.fragementId)
                    window.currentFragement = req.data;
                    console.log(req)
                    $('#chartsCanvas>a.charts-left').attr('data-fragement-id', req.data.left);
                    $('#chartsCanvas>a.charts-right').attr('data-fragement-id', req.data.right);
                    loadCharts(req.data)
                });
            }
        };
        loadModelData('${fragementId}')

        $('#chartsCanvas>a.charts-left').on('click', function (e) {
            e.preventDefault();
            loadModelData(window.currentFragement.left)
        });
        $('#chartsCanvas>a.charts-right').on('click', function (e) {
            e.preventDefault();
            loadModelData(window.currentFragement.right)
        });
    })();
</script>