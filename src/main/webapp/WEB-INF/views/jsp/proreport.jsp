<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>满意吧</title>

    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/star-rating.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/report.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/css/themes-cust/myb-green/jquery-ui.css">

    <style type="text/css">
        /*
         * Customize borders and shading to suit this nested layout
         */

        .ui-layout-pane { /* all 'panes' */
            padding: 0px;
        }

        .ui-layout-pane-north {
            background-color: #F5F5F5;
        }

        .ui-layout-pane-south {
        }

        .ui-layout-pane-west {
        }

        .ui-layout-pane-east {
        }

        .ui-layout-pane-center {
            border-left: 0;
            border-right: 0;
        }

        .inner-center {
        }

        .outer-west {
            padding-bottom: 3px;
            background-color: #f5f5f5;
        }

        .outer-east {
        }

        .middle-north,
        .middle-south {
            line-height: 40px;
            background-color: #FFF;
        }

        .ui-layout-resizer { /* all 'resizer-bars' */
            background: #F5F5F5;
        }

        .ui-layout-resizer:hover { /* all 'resizer-bars' */
            background: #EFD;
        }

        .ui-layout-resizer-west {
        }

        .ui-layout-resizer-east {
        }

        .ui-layout-toggler { /* all 'toggler-buttons' */
            background: #AAA;
        }

        .ui-layout-toggler:hover { /* all 'toggler-buttons' */
            background: #FC3;
        }

        .outer-center,
        .middle-center {
            /* center pane that are 'containers' for a nested layout */
            padding: 0;
            border: 0;
            background-color: white;
        }

        .middle-south {
            border-top: 2px solid #f5f5f5;
        }

        .right-north,
        .right-south {
            background-color: white;
        }

    </style>

</head>
<body>
<div class="ui-layout-north">
    <div id="header">
        <div id="membershortcuts"></div>
        <div id="logo"></div>
        <ul class="main-navigator">
            <li><a href="${ctx}/page/main/home.html">首页</a></li>
            <li><a href="${ctx}/page/main/question.html">我的调查列表</a></li>
            <li><a href="${ctx}/page/main/product.html">产品介绍</a></li>
            <li><a href="${ctx}/page/main/price.html">服务及价格方案</a></li>
        </ul>
    </div>
    <div id="subHeader" style="padding:0 10px;">
        <div id="backToLast"><a>返回</a></div>
        <ul id="subNavigator">
            <li><a href="">问卷设置</a></li>
            <li><a href="">数据采集</a></li>
            <li><a href="" class="current">分析报告</a></li>
            <li><a href="">顾客管理</a></li>
        </ul>
    </div>
    <div id="titleBar" style="padding:0 10px;border-bottom:none">
        <button id="feed"></button>
        <span id="title">详细报告</span>
        <span id="titleTip"><a href="${ctx}/page/main/report/${id}.html">点击进入摘要报告</a></span>
    </div>
    <div class="clearfix"></div>
</div>

<div class="ui-layout-south">
    <div id="footer" style="padding:0 15px;">
        Copyright @2016 manyibar.com. All rights reserved.
    </div>
</div>

<div class="outer-center">

    <div class="middle-center">
        <div class="pro-report report active">
            <div id="page" class="report-page">
                <div class="pcont report-page">
                    <div class="title">总体顾客满意度</div>
                    <div class="chartdiv">
                        <div class="chart-main"></div>
                        <div class="table-main"></div>
                        <div class="chartlegend">
                            <div style="line-height:20px"></div>
                        </div>
                    </div>
                    <div class="comments">
                        <div class="title">详细发现</div>
                        <div class="commentscontent">
                            1、从本次调查来看，有<span class="satisfied">xx%</span>的顾客对<span class="store">xx餐厅</span>表示满意（8-10分），
                            <span class="unsatisfied">xx%</span>的顾客表示不满意（1-5分），其余<span class="neutral">xx%</span>的顾客态度中立；<br>
                            2、与行业水平相比，<span class="store">xxx餐厅</span>的满意顾客占比<span
                                class="comment-desc">&lt;插入判别描述&gt;</span>.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="middle-north">
        <div class="wsToolbar">
            <button class="myButton sample" style="width:100px;">样本筛选</button>
            <ul class="filter tempfilter"></ul>
            <ul class="filter savedfilter"></ul>
            <ul class="filter filterlabel"></ul>
            <ul class="filter timeperiodlabel"></ul>
        </div>

        <div class="wsToolbar">
            <button class="myButton dimensions dropdown-toggle" id="dimnsnbutton" style="width:100px;float:left;">
                +细分维度
            </button>
            <ul class="dropdown-menu dimensions-dropdown"
                style="display:none; position:absolute;top:70px;background-color:#f5f5f5;width:320px">
                <li role="presentation" class="dropdown-header">请选择要添加的细项维度</li>
                <div class="dimensions-dropdown-div-left">
                    <li><input name="dimnsncheck" type="checkbox" value="store"/>门店</li>
                </div>
                <div class="dimensions-dropdown-div-right">
                </div>

                <li class="divider"></li>
                <li>
                    <button class="btn dimn-new" style="width:100%;padding:3px 12px;border:1px solid #c6e38e">+ 自定义维度
                    </button>
                </li>
                <li>
                    <button class="btn dimn-confirm" style="width:100%;padding:3px 12px;border:1px solid #c6e38e">确认
                    </button>
                </li>
            </ul>
            <ul class="dimnsn">
                <li>
				<span>
				<div class="btn-group">				   
				<select id="showmenu" style="width:100px;">
					<option selected="selected" value="">按时间</option>
					<option value="">月</option>
					<option value="">季</option>
					<option value="">年</option>
				</select>
				</div></span>
                </li>
            </ul>

        </div>


        <div class="left-top-title">
            <div class="param question_id"></div>
            <div class="param question_group_id"></div>
            <div class="param dim one_dimnsn"></div>
            <div class="param dim dimnsn"></div>
            <div class="param dim store"></div>
            <div class="param dim time"></div>
            <div class="param dim time_dimnsn"></div>
        </div>
    </div>
    <div class="middle-south" style="text-align:center">
        <img src="${ctx}/assets/images/comments.png" width="16px" id="commenticon" alt="Open Comments"></img>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" id="saveReportImg">
            <img src="${ctx}/assets/images/savepdf.png" width="16px" id="savepdficon" alt="Save the report."></img>
        </a>
    </div>

</div>

<div class="outer-west">
    <div id="reportCategory" class="left-nav">
    </div>
</div>
<div class="outer-east">
    <div class="right-north">
        <div id="slideUp"></div>
    </div>
    <div class="right-south">
        <div id="slideDown"></div>
        <div id="slideToolbar" style="text-align:center">
        </div>
    </div>
    <div class="right-center" style="background-color:white; padding: 0 5px; overflow-y: auto">
        <div class="thumb-img hide" >
            <a class="fancybox-close " style="display: none;"></a>
        </div>
    </div>
</div>

<div class="sample-dialog">
    <div class="content col-md-12">
        <button type="button" class="close close-dialog">×</button>
        <div class="heading">时间期:</div>
        <div class="timerange">
            <div class="input-daterange input-group" id="datepicker">
                <input type="text" class="input-sm form-control" name="start" style="width:120px"/>
                <span class="input-addon">to</span>
                <input type="text" class="input-sm form-control" name="end" style="width:120px"/>
                <input type="checkbox" name="nowtime">当前时间</input>
                <button type="button" class="myButton filterbutton">筛选</button>
            </div>
        </div>
        <div class="dialogAlertMessage">请选择时间区间。</div>
        <div class="splitter"></div>
        <div class="store"></div>
        <div class="questionaire"></div>
    </div>
    <div class="col-md-12 carddivider"></div>
    <div style="text-align: center; height: 50px; padding-top: 10px;" class="col-md-12">
        <button type="button" class="btn btn-default savefilterbtn">作为常用筛选</button>
        <button type="button" class="btn btn-default tempfilterbtn">作为临时筛选</button>
    </div>
</div>
<div id="storedialog" class="storedialog" title="分店"></div>
<div id="storegroupdialog" class="storedialog" title="分店组"><input type="checkbox" value="all">全选</input><br></div>
<div class="dimension-dialog">
    <div class="row" style="height:30px">
        <div class="col-md-10 dimension-name">
            <input type="text" value="输入自定义名称" onfocus="javascript:if(this.value=='输入自定义名称')this.value='';">
        </div>
        <div style="text-align: right;" class="col-md-1">
            <button type="button" class="close close-dialog">×</button>
        </div>
    </div>
    <div class="row ui-tabs ui-widget ui-widget-content ui-corner-all ui-tabs-vertical ui-helper-clearfix"
         id="dimension-tabs">
        <div class="dimn-left-nav">
            <ul class="dimn-sg-name">
                <li>
                    <div class="newsmplbtn" onclick="newsamplegroup(this);">+</div>
                </li>
                <li style="height:400px;background-color: #f5f5f5;border:none;"></li>
            </ul>
        </div>
        <div id="dimn-tabs-panel" class="ui-tabs-panel ui-widget-content ui-corner-bottom">
            <div class="content">
                <div class="heading">时间期:</div>
                <div class="timerange">
                    <div class="input-daterange input-group" id="datepicker">
                        <input type="text" class="input-sm form-control" name="start" style="width:120px"/>
                        <span class="input-addon">to</span>
                        <input type="text" class="input-sm form-control" name="end" style="width:120px"/>
                        <input type="checkbox" name="nowtime">当前时间</input>
                        <button type="button" class="myButton filterbutton">筛选</button>
                    </div>
                </div>
                <div class="dialogAlertMessage">请选择时间区间。</div>
                <div class="splitter"></div>
                <div class="store"></div>
                <div class="questionaire"></div>
            </div>
        </div>
    </div>

    <div class="col-md-12 carddivider"></div>
    <div style="text-align: left; height: 50px; padding-top: 10px;" class="col-md-12">
        <button type="button" class="btn btn-default savedimnsnbtn">保存此维度</button>
    </div>
</div>
<div id="saveFilterNameDialog">
    <label for="filterName">名字</label><input type="text" name="filterName" id="filterName"
                                             class="text ui-widget-content ui-corner-all">
    <p class="validateTips">所有的表单字段都是必填的。</p>
</div>
<script type="text/javascript" src="${ctx}/assets/jqueryui/jquery.js"></script>
<script type="text/javascript" src="${ctx}/assets/jqueryui/jquery-ui.js"></script>
<script type="text/javascript" src="${ctx}/assets/jqueryui/jquery.layout/jquery.layout.min.js"></script>
<script type="text/javascript"
        src="${ctx}/assets/jqueryui/jquery.layout/callbacks/jquery.layout.resizePaneAccordions.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/echarts/echarts-3.1.10.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/proreport.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/canvas2image.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/proreport-export.js"></script>
<script type="text/javascript">
    //Load the data of the page
    reportGlobal.ctx = '${ctx}';
    reportGlobal['dimn-tabs-panel'] = $('#dimn-tabs-panel').html().toString();

    $(function ($) {
        var url = ""; //= reportGlobal.ctx+'/assets/report/questionnairecollection.js';
        var postdata = {};
        if ('${id}') {
            url = reportGlobal.ctx + '/page/proreport/queryTemplateById.json';
            postdata = {id: "${id}"};
        }
        reportGlobal.id = '${id}';
        $.ajax({
            type: 'post',
            url: url,
            data: postdata,
            dataType: 'json',
            cache: false,
            success: function (data) {
                var jsondata = data.data;
                reportGlobal.jsondata = jsondata;
                startInits();
                buildLeftNav(jsondata);
                buildTopNav(jsondata);
                buildSampleDialogButtons(jsondata);
                buildDimensionDialogButtons(jsondata);
                //reloadPageMapping();
                finalInits();
            }
        });
        bindButtons();
        setTimeout('$(".left-nav a:first").click()', 2000);
    });

    //Load the layout of the page
    var outerLayout, middleLayout, innerLayout;
    $(document).ready(function () {
        outerLayout = $('body').layout({
            center__paneSelector: ".outer-center"
            , west__paneSelector: ".outer-west"
            , east__paneSelector: ".outer-east"
            , west__size: 200
            , east__size: 150
            , spacing_open: 5 // ALL panes
            , spacing_closed: 10 // ALL panes
            , north__spacing_open: 0
            , south__spacing_open: 0
            , center__onresize: "middleLayout.resizeAll"
            , west__onresize: $.layout.callbacks.resizePaneAccordions
        });

        middleLayout = $('div.outer-center').layout({
            center__paneSelector: ".middle-center"
            , north__paneSelector: ".middle-north"
            , south__paneSelector: ".middle-south"
            , north__size: 70
            , south__size: 30
            , spacing_open: 0  // ALL panes
            , spacing_closed: 0 // ALL panes
            , center__onresize: "innerLayout.resizeAll"
        });

        rightLayout = $('div.outer-east').layout({
            center__paneSelector: ".right-center"
            , north__paneSelector: ".right-north"
            , south__paneSelector: ".right-south"
            , north__size: 30
            , south__size: 60
            , spacing_open: 0  // ALL panes
            , spacing_closed: 0 // ALL panes
            , center__onresize: "innerLayout.resizeAll"
        });

        //$("#reportCategory").accordion({
        //heightStyle:	"fill"
        //,	active:			0
        //});
        $(".myButton").button();
        $("#showmenu").selectmenu();
        $("#dimension-tabs").tabs().addClass("ui-tabs-vertical ui-helper-clearfix");
        $("#dimension-tabs li").removeClass("ui-corner-top").addClass("ui-corner-left");
        $(document).tooltip();
    });
</script>
</body>
</html>