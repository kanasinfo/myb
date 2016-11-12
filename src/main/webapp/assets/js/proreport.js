/*!
 * Myb report js v1.8.3
 * William
 * Date: Nov 1 2015
 *
 *
 *
 */
/**
 * TODO: 1. Save Filters: Time, Store - _saveFilters 2. Save Customize Dimension
 * 3. Upload changes to server 4. Retrieve restults from server
 */

var reportGlobal = {};

// For CallBack
reportGlobal.samplefilter = "";
reportGlobal.dimensions = {};
reportGlobal.dimensionType = "chartOneDimnsn";
// For Top Dimension Bar
reportGlobal.dimensionslist = "";
reportGlobal.savedDimns = [];
// For recording status
reportGlobal.savedFilterDimensions = "";
reportGlobal.specialQuestions = "";
reportGlobal.store = "";
reportGlobal.storeGroup = "";
reportGlobal.P52 = {};
reportGlobal.P52.labelTop = {
	normal: {
		label: {
			show: true,
			position: 'center',
			formatter: '{b}',
			textStyle: {
				baseline: 'bottom'
			}
		},
		labelLine: {
			show: false
		}
	}
};
reportGlobal.P52.labelFromatter = {
	normal: {
		label: {
			formatter: function(params) {
				return 100 - params.value + '%'
			},
			textStyle: {
				baseline: 'top'
			}
		}
	}
};
reportGlobal.P52.labelBottom = {
	normal: {
		color: '#ccc',
		label: {
			show: true,
			position: 'center'
		},
		labelLine: {
			show: false
		}
	},
	emphasis: {
		color: 'rgba(0,0,0,0)'
	}
};
reportGlobal.P52.tooltip = {
	trigger: 'item',
	formatter: '{a} <br/>{b} : {c} ({d}%)'
};
/*
 * 函数：格式化日期 参数：formatStr-格式化字符串 d：将日显示为不带前导零的数字，如1 dd：将日显示为带前导零的数字，如01
 * ddd：将日显示为缩写形式，如Sun dddd：将日显示为全名，如Sunday M：将月份显示为不带前导零的数字，如一月显示为1
 * MM：将月份显示为带前导零的数字，如01 MMM：将月份显示为缩写形式，如Jan MMMM：将月份显示为完整月份名，如January
 * yy：以两位数字格式显示年份 yyyy：以四位数字格式显示年份 h：使用12小时制将小时显示为不带前导零的数字，注意||的用法
 * hh：使用12小时制将小时显示为带前导零的数字 H：使用24小时制将小时显示为不带前导零的数字 HH：使用24小时制将小时显示为带前导零的数字
 * m：将分钟显示为不带前导零的数字 mm：将分钟显示为带前导零的数字 s：将秒显示为不带前导零的数字 ss：将秒显示为带前导零的数字
 * l：将毫秒显示为不带前导零的数字 ll：将毫秒显示为带前导零的数字 tt：显示am/pm TT：显示AM/PM 返回：格式化后的日期
 */
Date.prototype.format = function(formatStr) {
	var date = this;
	/*
	 * 函数：填充0字符 参数：value-需要填充的字符串, length-总长度 返回：填充后的字符串
	 */
	var zeroize = function(value, length) {
		if (!length) {
			length = 2;
		}
		value = new String(value);
		for (var i = 0, zeros = ''; i < (length - value.length); i++) {
			zeros += '0';
		}
		return zeros + value;
	};
	return formatStr
		.replace(
			/"[^"]*"|'[^']*'|\b(?:d{1,4}|M{1,4}|yy(?:yy)?|([hHmstT])\1?|[lLZ])\b/g,
			function($0) {
				switch ($0) {
					case 'd':
						return date.getDate();
					case 'dd':
						return zeroize(date.getDate());
					case 'ddd':
						return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri',
							'Sat'
						][date.getDay()];
					case 'dddd':
						return ['Sunday', 'Monday', 'Tuesday',
							'Wednesday', 'Thursday', 'Friday',
							'Saturday'
						][date.getDay()];
					case 'M':
						return date.getMonth() + 1;
					case 'MM':
						return zeroize(date.getMonth() + 1);
					case 'MMM':
						return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
							'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
						][date
							.getMonth()
						];
					case 'MMMM':
						return ['January', 'February', 'March', 'April',
							'May', 'June', 'July', 'August',
							'September', 'October', 'November',
							'December'
						][date.getMonth()];
					case 'yy':
						return new String(date.getFullYear()).substr(2);
					case 'yyyy':
						return date.getFullYear();
					case 'h':
						return date.getHours() % 12 || 12;
					case 'hh':
						return zeroize(date.getHours() % 12 || 12);
					case 'H':
						return date.getHours();
					case 'HH':
						return zeroize(date.getHours());
					case 'm':
						return date.getMinutes();
					case 'mm':
						return zeroize(date.getMinutes());
					case 's':
						return date.getSeconds();
					case 'ss':
						return zeroize(date.getSeconds());
					case 'l':
						return date.getMilliseconds();
					case 'll':
						return zeroize(date.getMilliseconds());
					case 'tt':
						return date.getHours() < 12 ? 'am' : 'pm';
					case 'TT':
						return date.getHours() < 12 ? 'AM' : 'PM';
				}
			});
}

function startInits() {
	// Init Date picker in Filter Dialog
	$('input[name="start"]').datepicker({
		maxDate: 0
	});
	$('input[name="end"]').datepicker({
		maxDate: 0
	});
	_initSampleFilter();

	$('.chart-main')
		.height(
			$('.middle-center.ui-layout-pane.ui-layout-pane-center')
			.height() * 0.9);
	$('.chart-main')
		.width(
			$('.middle-center.ui-layout-pane.ui-layout-pane-center')
			.width() * 0.9);
}

function _initSampleFilter() {
	var cdate = new Date();
	cdate.setMonth(cdate.getMonth() - 6);
	var value = {};
	var period = {
		start_time: cdate.format('yyyy/MM/dd'),
		end_time: ''
	};
	var store = {
		storeType: "",
		storeId: [""],
		storeName: [""]
	};
	reportGlobal.samplefilter = {};
	reportGlobal.samplefilter.filter_id = uuid(32, 16);
	reportGlobal.samplefilter.period = JSON.stringify(period);
	reportGlobal.samplefilter.store = JSON.stringify(store);
	reportGlobal.samplefilter.value = JSON.stringify(value);
	reportGlobal.store = {};
	reportGlobal.storeGroup = {};
}

function _initDimnDialog() {
	// restore the dimension name
	$('.dimension-name input').attr('id', 'CD' + uuid(30, 16));
	$('.dimension-name input').val('输入自定义名称');

	// restore the left nav
	var lisize = $('.newsmplbtn').parent().parent().find('li').length;
	if (lisize > 2) {
		for (var i = 0; i < lisize - 2; i++) {
			console.log($($('.newsmplbtn').parent().parent().find('li')[i])
				.text());
			$('.newsmplbtn').parent().parent().find('li')[0].remove();
		}
		$('.newsmplbtn').parent().next().css('height', '500px');
	}
	// redraw the dimensnion filter content pane
	_redrawdimnsn4tabs();
}

function dimn_remove_samplegroup(dom) {
	// var label = $(dom).parent().find('a').text();
	// $('#dimn-tabs-pane-'+label).remove();
	$(dom).parent().before().addClass('active');
	// $('#dimn-tabs-pane-'+$(dom).parent().before().find('a').text()).show();
	$(dom).parent().parent().find('li:last').height(
		$(dom).parent().parent().find('li:last').height() + 35);
	_removesamplegroup(dom);
	$(dom).parent().remove();
}

function deletedimension(dom) {
	var dimnId = $(dom).parent().find('input').val();
	var url = reportGlobal.ctx + '/page/proreport/delTemplateDimensionsById.json';
	var postdata = {
		id: reportGlobal.jsondata.id,
		dimensionId: dimnId
	};

	$.ajax({
		type: 'post',
		url: url,
		data: postdata,
		dataType: 'json',
		cache: false,
		success: function(data) {
			if (data.success) {
				$(dom).parent().remove();
				console.log("自定义维度'" + $(dom).parent().text() + "'删除成功！");
			}
		}
	});
}

function _removesamplegroup(dom) {

}

function newsamplegroup(dom) {
	var lastsg = $(dom).parent().prev().find('a').text();
	// Save current selected member
	if ($('.dimension-dialog .store').html() != '') {
		_saveDimnsnFilters($(dom).parent().parent().find("li[class='active']")
			.find('span').attr('id'), $(dom).parent().parent().find(
				"li[class='active']").find('span').text(), false);
	}
	$(dom).parent().parent().find("li").removeClass("active");
	// Add new member tab
	var rn = uuid(30, 16);
	$(dom)
		.parent()
		.before(
			' <li><span onclick="_leftDimnGroupClick(this);" class="dimnSGtextspan" id="SG' +
			rn + '" >自定义样品组0' + ($('.dimn-left-nav').find('ul li').length - 1) +
			'</span> <button onclick="dimn_remove_samplegroup(this);" type="button" class="close">×</button></li>'
		);
	$(dom).parent().prev().addClass("active");
	$(dom).parent().next().height($(dom).parent().next().height() - 35);
	// Redraw the content pane
	_redrawdimnsn4tabs();

	$('#SG' + rn).dblclick(function() {
		var span = $(this);
		// 根据文本创建文本框 并加入表表中--文本框的样式自己调整
		var text = span.text();
		var txt = $("<input type='text' class='dimnSGinput'>").val(text);
		txt.blur(function() {
			// 失去焦点，保存值。
			var newText = $(this).val();

			// 移除文本框,显示新值
			$(this).remove();
			span.text(newText);
			_saveTempDimnsnName(span.attr('id'), newText);
		});
		span.text("");
		span.after(txt);
	});

}

function _leftDimnGroupClick(dom) {
	// Save current selected member
	if ($('.dimension-dialog .store').html() != '') {
		_saveDimnsnFilters($(dom).parent().parent().find("li[class='active']")
			.find('span').attr('id'), $(dom).parent().parent().find(
				"li[class='active']").find('span').text(), false);
	}
	$(dom).parent().parent().find('li').removeClass('active');
	$(dom).parent().addClass('active');
	_reloaddimnsn4tabs($(dom).attr('id'));
}

// redraw and clear the dimension content pane
function _redrawdimnsn4tabs() {
	$('.dimension-dialog input[name="start"]').datepicker('setDate', '');
	$('.dimension-dialog input[name="end"]').datepicker("option", "disabled",
		false);
	$('.dimension-dialog input[name="end"]').datepicker('setDate', '');
	if ($('.dimension-dialog input[name="nowtime"]').is(':checked')) {
		$('.dimension-dialog input[name="nowtime"]').prop('checked', false);
	}
	$('#storedialog input').prop('checked', false)
	$('#storegroupdialog input').prop('checked', false)
	$('.dimension-dialog .store').html('');
	$('.dimension-dialog .questionaire').html('');
}

function _initDimnTempData() {
	if (!reportGlobal.dimensionstemp) {
		reportGlobal.dimensionstemp = {
			"dimensionId": $('.dimension-name input').attr('id'),
			"name": $('.dimension-name input').val(),
			"members": []
		};
	} else if (reportGlobal.dimensionstemp.dimensionId != $(
			'.dimension-name input').attr('id')) {
		reportGlobal.dimensionstemp.dimensionId = $('.dimension-name input')
			.attr('id');
		reportGlobal.dimensionstemp.name = $('.dimension-name input').val();
		reportGlobal.dimensionstemp.members = [];
	} else {
		reportGlobal.dimensionstemp.name = $('.dimension-name input').val();
	}
}
// reload the selected member in the dimension content pane
function _reloaddimnsn4tabs(id) {
	_initDimnTempData();
	// clear the pane
	_redrawdimnsn4tabs();
	for (var i = 0; i < reportGlobal.dimensionstemp["members"].length; i++) {
		if (reportGlobal.dimensionstemp["members"][i].memberId == id) {
			_loadSelectedOptions(reportGlobal.dimensionstemp["members"][i]);
		}
	}
}

/**
 * <li><input name="dimnsncheck" type="checkbox" value="time" />就餐时间</li>
 * <li><input name="dimnsncheck" type="checkbox" value="who" />和谁一起</li>
 * <li><input name="dimnsncheck" type="checkbox" value="newold" />新老客户</li>
 * <li><input name="dimnsncheck" type="checkbox" value="age" />年龄</li>
 * com.myb.questiontype.Degree, com.myb.questiontype.Judge,
 * com.myb.questiontype.MultiSelect
 */

function _loadSavedDimnsnList(jsondata) {
	var qdata = jsondata["questions"];
	var html = "";
	var v = 0;
	for (var i = 0; i < qdata.length; i++) {
		// if (qdata[i]['sortNumber'] == 0 && !qdata[i]['filterFlag']) {
		// v++;
		// }

		if (qdata[i]['activeFlag'] && qdata[i]['questionType'] ==
			'com.myb.questiontype.SingleSelect') {
			html += "<li><input name='dimnsncheck' type='checkbox' value='" + qdata[i][
				'questionId'
			] + "' />" + qdata[i]['questionName'] + "</li>";
		}
	}
	if (reportGlobal.selectedStoreType == 'store') {
		$('.dimensions-dropdown-div-left').children().first().show();
		$('.dimensions-dropdown-div-left')
			.children()
			.first()
			.parent()
			.html(
				'<li><input type="checkbox" value="store" name="dimnsncheck">门店</li>');
	} else if (reportGlobal.selectedStoreType == 'storegroup') {
		$('.dimensions-dropdown-div-left').children().first().show();
		$('.dimensions-dropdown-div-left')
			.children()
			.first()
			.parent()
			.html(
				'<li><input type="checkbox" value="storegroup" name="dimnsncheck">门店组</li>'
			);
	} else if (reportGlobal.selectedStoreType == 'nostore') {
		$('.dimensions-dropdown-div-left').children().first().hide();
	} else {
		$('.dimensions-dropdown-div-left').children().first().hide();
	}
	$('.dimensions-dropdown-div-left').children().first().siblings().remove();
	$('.dimensions-dropdown-div-left').children().first().after(html);
	qdata = jsondata["dimensions"]
	html = "";
	if (qdata && qdata.length > 0) {
		for (var i = 0; i < qdata.length; i++) {
			html += "<li><input name='dimnsncheck' type='checkbox' value='" + qdata[i][
					'dimensionId'
				] + "' />" + qdata[i]['name'] +
				"<span class='dimensions-edit-span' onclick='editdimension(this)'>编辑</span><span class='dimensions-delete-span' onclick='deletedimension(this)'>删除</span></li>";
		}
		$('.dimensions-dropdown-div-right').html(html);
		$('.dimensions-dropdown-div-left').css("width", "40%");
	} else {
		$('.dimensions-dropdown-div-right').html(html);
		$('.dimensions-dropdown-div-left').css("width", "100%");
	}
}

function editdimension(dom) {
	var dimnid = $(dom).prev().val();
	var dimnname = $(dom).prev().attr('name');
	var qdata = reportGlobal.jsondata["dimensions"];
	var mdata
	if (qdata && qdata.length > 0) {
		for (var i = 0; i < qdata.length; i++) {
			if (qdata[i]['dimensionId'] == dimnid) {
				mdata = qdata[i];
				_loadSavedDimnsnDialog(mdata)
			}
		}
	} else {
		buildDimensionDialog(reportGlobal.jsondata);
	}
}

function _loadSavedDimnsnDialog(mdata) {
	$('.dimensions-dropdown').toggle();
	$(".dimension-dialog").toggle();
	reportGlobal.dimensionstemp = mdata;
	// _reloaddimnsn4tabs(mdata.members.memberId);

	// clear the pane
	_initDimnDialog();

	// restore the dimension name
	$('.dimension-name input').attr('id', mdata.dimensionId);
	$('.dimension-name input').val(mdata.name);

	// restore the left nav
	for (var i = 0; i < reportGlobal.dimensionstemp["members"].length; i++) {
		$('.newsmplbtn')
			.parent()
			.before(
				' <li><span onclick="_leftDimnGroupClick(this);" class="dimnSGtextspan" id="' +
				reportGlobal.dimensionstemp["members"][i]["memberId"] + '" >' +
				reportGlobal.dimensionstemp["members"][i]["name"] +
				'</span> <button onclick="dimn_remove_samplegroup(this);" type="button" class="close">×</button></li>'
			);
		// $(dom).parent().prev().addClass("active");
		$('.newsmplbtn').parent().next().height(
			$('.newsmplbtn').parent().next().height() - 35);

		$('#' + reportGlobal.dimensionstemp["members"][i]["memberId"])
			.dblclick(
				function() {
					var span = $(this);
					// 根据文本创建文本框 并加入表表中--文本框的样式自己调整
					var text = span.text();
					var txt = $(
							"<input type='text' class='dimnSGinput'>")
						.val(text);
					txt.blur(function() {
						// 失去焦点，保存值。
						var newText = $(this).val();

						// 移除文本框,显示新值
						$(this).remove();
						span.text(newText);
						_saveTempDimnsnName(span.attr('id'), newText);
					});
					span.text("");
					span.after(txt);
				});

	}

	$('.dimn-sg-name').children().first().addClass('active');

	// Load the data of the first tab
	_loadSelectedOptions(reportGlobal.dimensionstemp["members"][0]);

}

function _loadSelectedOptions(so) {
	var sf = {};
	sf.memberId = so.memberId;
	sf.name = so.name;
	sf.period = _string2Json(so.period);
	sf.store = _string2Json(so.store);
	sf.value = _string2Json(so.value);
	sf.label = _string2Json(so.label);

	buildDimensionDialog(reportGlobal.jsondata);
	$('.dimension-dialog input[name="start"]').datepicker('setDate',
		new Date(sf.period.start_time));
	$('.dimension-dialog input[name="end"]').datepicker('setDate',
		new Date(sf.period.end_time));
	if (!sf.period.end_time) {
		$('.dimension-dialog input[name="end"]').datepicker("option",
			"disabled", true);
		$('.dimension-dialog input[name="nowtime"]').prop('checked', true);
	}
	$('input[name="storeradio"][value="' + sf.store.storeType + '"]').prop(
		'checked', true);
	for (var i = 0; i < sf.store.storeId.length; i++) {
		$(
			'#' + sf.store.storeType + 'dialog input[id="' + sf.store.storeId[i] +
			'"]').prop('checked', true);
	}

	for (var v in sf.value) {
		if (typeof(sf.value[v]) == 'object') {
			for (var d in sf.value[v]) {
				$('#' + sf.value[v][d]).prop('checked', true);
			}
		}
	}
}

function _saveTempDimnsnName(id, name) {
	var sf = {};
	sf.memberId = id;
	sf.name = name;
	sf.period = {};
	sf.store = {};
	sf.value = "";
	sf.label = "";

	_initDimnTempData();

	var findmatchedflag = false;
	for (var i = 0; i < reportGlobal.dimensionstemp["members"].length; i++) {
		if (reportGlobal.dimensionstemp["members"][i].memberId == sf.memberId) {
			reportGlobal.dimensionstemp["members"][i].name = name;
			findmatchedflag = true;
		}
	}
	if (!findmatchedflag) {
		reportGlobal.dimensionstemp["members"].push(sf);
	}
}

function _saveDimnsnFilters(id, name, saveflag) {
	var dmnsnid = $('.dimension-name input').attr('id');
	// To save the time period of the filter.
	var start_time = $('.dimension-dialog input[name="start"]').datepicker(
		'getDate');
	var end_time = $('.dimension-dialog input[name="end"]').datepicker(
		'getDate');
	var period = {
		"start_time": start_time == null ? '' : start_time
			.format('yyyy/MM/dd'),
		"end_time": end_time == null ? '' : end_time.format('yyyy/MM/dd')
	};
	// To save the store option and selected stores of the filter.
	var storeId = [];
	var storeName = [];
	$(
		'#' + $('input[name="storeradio"]:checked').val() + 'dialog input:checked').each(
		function() {
			storeId.push($(this).attr('id'));
			storeName.push($(this).val());
		})
	var store = {
		"storeType": $('.dimension-dialog input[name="storeradio"]:checked')
			.val(),
		"storeId": storeId,
		"storeName": storeName
	};
	var inputs = $('.dimension-dialog .questionaire input:checked');
	var vsltd = {};
	var nsltd = {};
	var idt, namet, valuet, pidt;
	for (var i = 0; i < inputs.length; i++) {
		idt = $(inputs[i]).attr('id');
		valuet = $(inputs[i]).attr('value');
		namet = $(inputs[i]).attr('name');
		if (valuet != namet) {
			pidt = $(inputs[i]).parent().parent().attr('id');
			if (nsltd['' + namet + '']) {
				vsltd['' + pidt + ''].push(valuet);
				nsltd['' + namet + ''].push(valuet);
			} else {
				vsltd['' + pidt + ''] = [];
				vsltd['' + pidt + ''].push(valuet);
				nsltd['' + namet + ''] = [];
				nsltd['' + namet + ''].push(valuet);
			}
		} else {
			pidt = idt;
			vsltd['' + pidt + ''] = pidt;
			nsltd['' + namet + ''] = valuet;
		}
	}
	var sf = {};
	// reportGlobal.savedfiltername = name;
	sf.memberId = id;
	sf.name = name;
	sf.period = JSON.stringify(period);
	sf.store = JSON.stringify(store);
	sf.value = JSON.stringify(vsltd);
	sf.label = JSON.stringify(nsltd);

	_initDimnTempData();

	var findmatchedflag = false;
	for (var i = 0; i < reportGlobal.dimensionstemp["members"].length; i++) {
		if (reportGlobal.dimensionstemp["members"][i].memberId == sf.memberId) {
			reportGlobal.dimensionstemp["members"].splice(i, 1, sf);
			findmatchedflag = true;
		}
	}
	if (!findmatchedflag) {
		reportGlobal.dimensionstemp["members"].push(sf);
	}
	findmatchedflag = false;
	if (!saveflag) {
		// reportGlobal.tempFilters = sf;
	} else {
		var url = reportGlobal.ctx + '/page/proreport/addTemplateDimensions.json';
		var postdata = {
			id: reportGlobal.jsondata.id,
			data: JSON.stringify(reportGlobal.dimensionstemp)
		};
		if (reportGlobal.jsondata["dimensions"]) {
			for (var i = 0; i < reportGlobal.jsondata["dimensions"].length; i++) {
				if (reportGlobal.jsondata["dimensions"][i].dimensionId == dmnsnid) {
					reportGlobal.jsondata["dimensions"].splice(i, 1,
						reportGlobal.dimensionstemp);
					findmatchedflag = true;
					url = reportGlobal.ctx +
						'/page/proreport/updateTemplateDimensionsById.json';
					postdata.dimensionId = dmnsnid;
				}
			}
			if (!findmatchedflag) {
				reportGlobal.jsondata["dimensions"]
					.push(reportGlobal.dimensionstemp);
			}

		} else {
			reportGlobal.jsondata["dimensions"] = [];
			reportGlobal.jsondata["dimensions"]
				.push(reportGlobal.dimensionstemp);
		}

		$.ajax({
			type: 'post',
			url: url,
			data: postdata,
			dataType: 'json',
			cache: false,
			success: function(data) {
				if (data.success) {
					console.log("自定义维度'" + sf.name + "'保存成功！");
				}
			}
		});
	}
}

/**
 * **/

function buildLeftNav(jsondata) {
	var qGroup = jsondata['questionGroup'].sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});
	var leftNav = jsondata['questions'].sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});
	var flag = false;
	var html = "";
	var period = _string2Json(reportGlobal.samplefilter.period);
	if (period == null) {
		var cdate = new Date();
		cdate.setMonth(cdate.getMonth() - 6);
		period = {
			start_time: cdate.format('yyyy/MM/dd'),
			end_time: ''
		};
	}
	var ds2 = {
		start_time: new Date(period.start_time),
		end_time: period.end_time == '' ? null : new Date(period.end_time)
	};
	for (var i = 0; i < qGroup.length; i++) {
		if (qGroup[i]['businessType'] != 'groupCustomerVoice') {
			if (i > 0) {
				html += "</ul></div>";
			}
			html += "<h3><a " + " question_group_id='" + qGroup[i]['questionGroupId'] +
				"'" + " href='#' onclick='_leftNavQuestionGroupClick(this);'>" + qGroup[i][
					'name'
				] + "</a></h3>";
			html += "<div style='padding:5px;'>";
			html += "<ul";
			if (!flag) {
				html += " class='open' ";
			}
			html += ">";

			for (var j = 0; j < leftNav.length; j++) {
				if (leftNav[j]['questionGroupId'] == qGroup[i]['questionGroupId'] && (
						leftNav[j]['activePeriod'] == '' || (leftNav[j]['activePeriod'] != '' &&
							_compareDates(
								leftNav[j]['activePeriod'], ds2)))) {
					if (('groupDriver,groupStandard'.indexOf(qGroup[i]['businessType']) > -1) &&
						leftNav[j]['filterFlag']) {
						//不显示driver和standard下面的filterFlag为True的问题
					} else {
						html += "<li><a " + " questionId='" + leftNav[j]['questionId'] + "'" +
							" question_group_id='" + leftNav[j]['questionGroupId'] + "'" +
							" href='#' onclick='_leftNavQuestionClick(this);'>" + leftNav[j][
								'questionName'
							] + "</a></li>";
					}
				}
			}
		}
	}
	html += "";
	$(".left-nav").html(html);
	$("#reportCategory").accordion({
		heightStyle: "fill",
		active: 0
	});
}

function _leftNavQuestionGroupClick(dom) {
	$(dom).parent().parent().find("ul").removeClass("open");
	$(dom).parent().next('div').find("ul").addClass("open");
	$(dom).parent().next('div').find("ul").find("a:first").click();
}

function _leftNavQuestionClick(dom) {
	$(dom).parent().parent().parent().parent().find("ul li").removeClass(
		"active");
	$(dom).parent().addClass("active");

	$(".question_group_id").html($(dom).attr('question_group_id'));
	$(".question_id").html($(dom).attr('questionid'));
	$(".dim").html("");
	$(".dim.one_dimnsn").html("true");
	$(".dimnsn>li>div").removeClass("active");
	$('.date-dialog-toggle').html("时间");
	loadPage();
}

function _refreshFilters(jsondata) {
	if ($('.sample-dialog input[name="nowtime"]').is(':checked')) {
		_buildSampleDialog(jsondata);
	} else if ($('.sample-dialog input[name="start"]').datepicker("getDate") ==
		null && $('.sample-dialog input[name="end"]').datepicker("getDate") == null) {
		$(".sample-dialog .dialogAlertMessage").show();
	} else {
		_buildSampleDialog(jsondata);
	}
}

function _refreshDimnsnFilters(jsondata) {
	if ($('.dimension-dialog input[name="nowtime"]').is(':checked')) {
		buildDimensionDialog(jsondata);
	} else if ($('.dimension-dialog input[name="start"]').datepicker("getDate") ==
		null && $('.dimension-dialog input[name="end"]').datepicker("getDate") ==
		null) {
		$(".dimension-dialog .dialogAlertMessage").show();
	} else {
		buildDimensionDialog(jsondata);
	}
}

function buildTopNav(jsondata) {
	buildFilterTopNav(jsondata);
	buildDimensionNav(jsondata);
}

function _string2Json(jString) {
	if (jString && '' != jString) {
		return eval("(" + jString + ")");
	} else {
		return null;
	}
}

function _json2String(jString) {
	return JSON.stringify(jString);
}

function _maxD(d1, d2) {
	var dt;
	d1 > d2 ? dt = d1 : dt = d2;
	return dt;
}

function _minD(d1, d2) {
	var dt;
	d1 < d2 ? dt = d1 : dt = d2;
	return dt;
}

function _compareDates(ds1, ds2) {
	var d1s, d1e, d2s, d2e, result;
	for (var i = 0; i < ds1.length; i++) {
		d1s = new Date(ds1[i].startTime);
		d2s = new Date(ds2.start_time);
		ds1[i].endTime == null || ds1[i].endTime == '' ? d1e = new Date(
			'2100/01/01') : d1e = new Date(ds1[i].endTime);
		ds2.end_time == null ? d2e = new Date('2100/01/01') : d2e = new Date(
			ds2.end_time);
		_minD(d1e, d2e).format('yyyy/MM/dd') >= _maxD(d1s, d2s).format(
			'yyyy/MM/dd') ? result = true : result = false;
		if (result) {
			return result;
		}
	}
	return result;
}

function buildFilterTopNav(jsondata) {
	// <li><div onclick="_topFilterClick(this);"
	// title="筛选条件:男；北京/上海/广州；18-45岁"><span
	// class="filtertextspan">未保存</span></div></li>
	buildTempFilterTopNav();
	buildSavedFilterTopNav(jsondata);
}

/**
 * @param jsondata
 */
function buildTempFilterTopNav() {
	var topNav = reportGlobal.tempFilters;
	var start = 0;
	var fNames = [];
	var html = "";
	if (topNav) {
		var labels = _string2Json(topNav['label']);
		var values = _string2Json(topNav['value']);
		var fName = topNav['name'];
		var classstring = "class='active'";
		if (fName == reportGlobal.savedfiltername) {
			classstring = "class='active'";
		} else {
			classstring = ""
		}
		html += "<li><div " + classstring + " title='筛选条件: ";
		for (var label in labels) {
			if ('object' == typeof labels[label]) {
				html += label + ':'
				for (var j = 0; j < labels[label].length; j++) {
					html += labels[label][j] + ',';
				}
				html = html.substr(0, html.length - 1);
				html += '/';
			} else {
				html += labels[label] + "/";
			}
		}
		if (html.charAt(html.length - 1) == '/') {
			html = html.substr(0, html.length - 1);
		}
		html += "' ><span class='filtertextspan'  onclick='_topFilterClick(this);'>" +
			fName +
			"</span><button class='close' type='button'  onclick='_topFilterRemove(this);'>×</button></div></li>";
		// $(".tempfilter").html(html);
	}
}

/**
 * @param jsondata
 */
function buildSavedFilterTopNav(jsondata) {
	var topNav = jsondata['filters'];
	var start = 0;
	var fNames = [];

	var html = "";
	for (var i = 0; i < topNav.length; i++) {
		var labels = _string2Json(topNav[i]['label']);
		var values = _string2Json(topNav[i]['value']);
		var fName = topNav[i]['name'];
		var classstring = "class='active'";
		if (fName == reportGlobal.savedfiltername) {
			classstring = "class='active'";
		} else {
			classstring = ""
		}
		html += "<li><div " + classstring + " title='筛选条件: ";
		for (var label in labels) {
			if ('object' == typeof labels[label]) {
				html += label + ':'
				for (var j = 0; j < labels[label].length; j++) {
					html += labels[label][j] + ',';
				}
				html = html.substr(0, html.length - 1);
				html += '/';
			} else {
				html += labels[label] + "/";
			}
		}
		if (html.charAt(html.length - 1) == '/') {
			html = html.substr(0, html.length - 1);
		}
		html += "' ><span class='filtertextspan' id='" + topNav[i]['filter_id'] +
			"'  onclick='_topFilterClick(this);'>" + fName +
			"</span><button class='close' type='button'  onclick='_topFilterRemove(this);'>×</button></div></li>";
	}
	$(".savedfilter").html(html);
}

function buildDimensionNav(jsondata) {
	var topNav = jsondata['savedDimnTab'].replace(/"/g, '').replace('[', '')
		.replace(']', '').replace(/ /g, '').split(',');
	reportGlobal.savedDimns = parseDimnTabs(topNav);
	var start = 0;
	var savedDimn = {};
	// for ( var i = 0; i < topNav.length; i++) {
	// if (topNav[i]['sortNumber'] == 0) {
	// start = i;
	// }
	// }
	var html = "";
	if (reportGlobal.selectedStoreType == 'store') {
		html += "<li><div onclick='_topNavStoreClick(this)'>门店</div></li>";
	} else if (reportGlobal.selectedStoreType == 'storegroup') {
		html += "<li><div onclick='_topNavStoreClick(this)'>门店组</div></li>";
	} else {

	}

	var funcname = '_topNavOtherClick';
	for (var i = start; i < reportGlobal.savedDimns.length; i++) {
		if (reportGlobal.savedDimns[i].id != null) {
			savedDimn = reportGlobal.savedDimns[i];
			if (savedDimn.type == "stor") {
				funcname = '_topNavStoreClick';
			} else if (savedDimn.type == "dimn") {
				funcname = '_topNavCustDimnClick';
			} else {
				funcname = '_topNavOtherClick';
			}
			html += '<li><div onclick="' + funcname + '(this);" id="' + savedDimn.id +
				'">' + savedDimn.name + ' </div></li>'
		}
	}
	$(".dimnsn").append(html);
}

function __topNavClick(dom) {
	$(".dimnsn li>div").removeClass("active");
	$(dom).addClass("active");
}

function _topFilterClick(dom) {
	// TO-DO: 1. whether load filters in the sample dialog? 2. load the page
	// according to the saved filter
	reportGlobal.savedfiltername = $(dom).text();
	for (var i = 0; i < reportGlobal.jsondata["filters"].length; i++) {
		if ($(dom).attr('id') == reportGlobal.jsondata["filters"][i].filter_id) {
			reportGlobal.samplefilter = reportGlobal.jsondata["filters"][i];
		}
	}
	$('.tempfilter div,.savedfilter div').removeClass('active');
	$(dom).parent().addClass('active');
	getTotalCount();
	loadPage();
}

function _topFilterRemove(dom) {
	// TODO to add the logic for removing the item from mongodb
	$(dom).parent().parent().remove();
	var postdata = {
		id: reportGlobal.jsondata.id,
		filterId: $(dom).prev().attr('id')
	};
	var url = reportGlobal.ctx + '/page/proreport/delTemplateFilterById.json';
	$.ajax({
		type: 'post',
		url: url,
		data: postdata,
		dataType: 'json',
		cache: false,
		success: function(data) {
			if (data.success) {
				console.log("筛选条件'" + $(dom).prev().text() + "'删除成功！");
			}
		}
	});
	loadPage();
}

function _topNavStoreClick(dom) {
	__topNavClick(dom);
	$(".dim").html("");
	$(".dim.store").html("true");
	reportGlobal.dimensionType = "chartStore";
	loadPage();
}

function _topNavOtherClick(dom) {
	__topNavClick(dom);

	if ($(".dim.time").html() != "") {
		$(".dim").html("");
		$(".dim.time_dimnsn").html("true");
		reportGlobal.dimensionType = "chartTimeDimnsn";
	} else if ($(".dim.time_dimnsn").html() != "") {
		reportGlobal.dimensionType = "chartTimeDimnsn";
	} else {
		$(".dim").html("");
		$(".dim.dimnsn").html("true");
		reportGlobal.dimensionType = "chartMultiDimnsn";
	}

	var qdata = reportGlobal.jsondata['questions'].sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});
	for (var i = 0; i < qdata.length; i++) {
		if (qdata[i]['questionId'] == $(dom).attr('id')) {
			if (reportGlobal.dimensions.dateType != null && reportGlobal.dimensions.dateType !=
				'') {
				var dt = reportGlobal.dimensions.dateType;
				reportGlobal.dimensions = qdata[i];
				reportGlobal.dimensions.dateType = dt;
			} else {
				reportGlobal.dimensions = qdata[i];
			}
		}
	}

	loadPage();
}

function _topNavCustDimnClick(dom) {
	__topNavClick(dom);

	if ($(".dim.time").html() != "") {
		$(".dim").html("");
		$(".dim.time_dimnsn").html("true");
		reportGlobal.dimensionType = "chartTimeCustDimnsn";
	} else if ($(".dim.time_dimnsn").html() != "") {
		reportGlobal.dimensionType = "chartTimeCustDimnsn";
	} else {
		$(".dim").html("");
		$(".dim.dimnsn").html("true");
		reportGlobal.dimensionType = "chartCustDimnsn";
	}

	if (reportGlobal.jsondata["dimensions"]) {
		for (var i = 0; i < reportGlobal.jsondata["dimensions"].length; i++) {
			if (reportGlobal.jsondata["dimensions"][i].dimensionId == $(dom)
				.attr('id')) {
				if (reportGlobal.dimensions.dateType != null && reportGlobal.dimensions.dateType !=
					'') {
					var dt = reportGlobal.dimensions.dateType;
					reportGlobal.dimensions = reportGlobal.jsondata["dimensions"][i];
					reportGlobal.dimensions.dateType = dt;
				} else {
					reportGlobal.dimensions = reportGlobal.jsondata["dimensions"][i];
				}
			}
		}
	} else {}

	loadPage();
}

/*******************************************************************************
 * William Function example: convert 1 to '01'
 ******************************************************************************/
function zeroize(value) {
	var length = 2;
	value = String(value);
	for (var i = 0, zeros = ''; i < (length - value.length); i++) {
		zeros += '0';
	}
	return zeros + value;
}

function _checknowtime(dom) {
	$("input[name='end']").datepicker("option", "disabled", dom.checked);
}

function _buildSampleDialog(jsondata) {
	var dialog = $(".sample-dialog");
	// var times = dialog.find(".timerange");
	var html = "";
	var store = dialog.find(".store");
	html += "<div class='heading'>分店选择:</div>";
	html +=
		"<input type='radio' onclick='_filterchecked(this)' name='storeradio' value='store' id='storeradio' ><label for='storeradio'>分店</label>";
	html +=
		"<input type='radio' onclick='_filterchecked(this)' name='storeradio' value='storegroup' id='storegroupradio' ><label for='storegroupradio'>分店组</label>";
	html +=
		"<input type='radio' onclick='_filterchecked(this)' name='storeradio' value='nostore' id='nostoreradio' checked><label for='nostoreradio'>未分店</label>";
	store.html(html);
	reportGlobal.html = {};
	reportGlobal.html.store = html;

	var storedata = jsondata['store'];
	var htmltemp = "<input type='checkbox' id='all' value='all'>全选</input><br>";
	for (var i = 0; i < storedata.length; i++) {
		htmltemp +=
			"<input type='checkbox' onclick='_filterchecked(this)' name='门店' value='" +
			storedata[i]['storeName'] + "' id='" + storedata[i]['storeId'] +
			"'><label for='" + storedata[i]['storeId'] + "'>" + storedata[i][
				'storeName'
			] + "</label>";
		if (i % 2 > 0) {
			htmltemp += "<br>";
		}
	}
	$('#storedialog').html(htmltemp);
	reportGlobal.html.storedialog = htmltemp;

	var storegroupdata = jsondata['storeGroup'];
	htmltemp = "<input type='checkbox' id='all' value='all'>全选</input><br>";
	for (var i = 0; i < storegroupdata.length; i++) {
		htmltemp +=
			"<input type='checkbox' onclick='_filterchecked(this)' name='门店组' value='" +
			storegroupdata[i]['storeGroupName'] + "' id='" + storegroupdata[i][
				'storeGroupId'
			] + "'><label for='" + storegroupdata[i]['storeGroupId'] + "'>" +
			storegroupdata[i]['storeGroupName'] + "</label>";
		if (i % 2 > 0) {
			htmltemp += "<br>";
		}
	}
	$('#storegroupdialog').html(htmltemp);
	reportGlobal.html.storegroupdialog = htmltemp;

	html = "";
	var q = dialog.find(".questionaire");
	var qGroup = jsondata['questionGroup'].sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});
	var qdata = jsondata['questions'].sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});
	var v = 0;
	var ds1;
	var ds2 = {
		start_time: $('.sample-dialog input[name="start"]').datepicker(
			'getDate'),
		end_time: $('.sample-dialog input[name="end"]').datepicker('getDate')
	};

	for (var j = 0; j < qGroup.length; j++) {
		if (qGroup[j]['businessType'] != 'groupCustomerVoice' && qGroup[j][
				'filterFlag'
			]) {
			html += "<div class='splitter'></div>";
			html += "<div class='heading' id='" + qGroup[j]['questionGroupId'] + "' >" +
				qGroup[j]['name'] + "</div>";
			var judgeCount = 0;
			for (var i = 0; i < qdata.length; i++) {
				if (qdata[i]['filterFlag'] && qGroup[j]['questionGroupId'] == qdata[i][
						'questionGroupId'
					] && (qdata[i]['activePeriod'] == '' || (qdata[i]['activePeriod'] != '' &&
						_compareDates(
							qdata[i]['activePeriod'], ds2)))) {
					if (qdata[i]['questionType'] == 'com.myb.questiontype.SingleSelect') {
						html += "<div class='heading1' id='" + qdata[i]['questionId'] +
							"' ><div class='questionlabel'>" + qdata[i]['questionName'] +
							"</div><div class='optionsdiv'>";
						var option = qdata[i]['options'].sort(function(a, b) {
							return a.sortNumber - b.sortNumber;
						});
						for (var k = 0; k < option.length; k++) {
							if (option[k]['activeFlag']) {
								html += "<input type='checkbox' onclick='_filterchecked(this)' name='" +
									qdata[i]['questionName'] + "' value='" + option[k]['optionValue'] +
									"' id='" + option[k]['optionId'] + "'><label for='" + option[k][
										'optionId'
									] + "'>" + option[k]['optionValue'] + "</label>";
							}
						}
						html += "</div></div>";
					} else if (qdata[i]['questionType'] == 'com.myb.questiontype.Judge') {
						judgeCount += 1;
						if (judgeCount == 1) {
							html += "<span style='padding-left:12px'>"
						}
						html += "<input type='checkbox' onclick='_filterchecked(this)' name='" +
							qdata[i]['questionName'] + "' value='" + qdata[i]['questionName'] +
							"' id='" + qdata[i]['questionId'] + "'><label for='" + qdata[i][
								'questionId'
							] + "'>" + qdata[i]['questionName'] + "</label>";
						if (judgeCount == 1) {
							html += "</span>"
						}
					} else if (qdata[i]['questionType'] == 'com.myb.questiontype.Degree') {
						html += "<div class='heading1' id='" + qdata[i]['questionId'] + "' >" +
							qdata[i]['questionName'] + "<br/><div class='custdiv'>";
						var option = qdata[i]['options'].sort(function(a, b) {
							return a.sortNumber - b.sortNumber;
						});
						for (var k = 0; k < option.length; k++) {
							if (option[k]['activeFlag']) {
								html += "<input type='checkbox'	 onclick='_filterchecked(this)' name='" +
									qdata[i]['questionName'] + "' value='" + option[k]['optionValue'] +
									"'  id='" + option[k]['optionId'] + "'><label for='" + option[k][
										'optionId'
									] + "'>" + option[k]['optionValue'] + "</label>";
							}
						}
						html += "</div></div>";
					} else if (qdata[i]['questionType'] == 'com.myb.questiontype.Score') {
						html += "<div class='heading1' id='" + qdata[i]['questionId'] + "' >" +
							qdata[i]['questionName'] + "<br/><div class='custdiv'>";
						var option = qdata[i]['options'].sort(function(a, b) {
							return a.sortNumber - b.sortNumber;
						});
						for (var k = 0; k < option.length; k++) {
							if (option[k]['activeFlag']) {
								html += "<input type='checkbox'	 onclick='_filterchecked(this)' name='" +
									qdata[i]['questionName'] + "' value='" + option[k]['optionValue'] +
									"'  id='" + option[k]['optionId'] + "'><label for='" + option[k][
										'optionId'
									] + "'>" + option[k]['optionValue'] + "</label>";
							}
						}
						html += "</div></div>";
					}

				}
			}
		}
	}

	q.html(html);

	reportGlobal.html.questionaire = html;
}

function buildDimensionDialog(jsondata) {
	var dialog = $(".dimension-dialog");
	// var times = dialog.find(".timerange");
	var html = "";
	var store = dialog.find(".store");
	html += "<div class='heading'>分店选择:</div>";
	html +=
		"<input type='radio' onclick='_filterchecked(this)' name='storeradio' value='store' id='storeradio' ><label for='storeradio'>分店</label>";
	html +=
		"<input type='radio' onclick='_filterchecked(this)' name='storeradio' value='storegroup' id='storegroupradio' ><label for='storegroupradio'>分店组</label>";
	html +=
		"<input type='radio' onclick='_filterchecked(this)' name='storeradio' value='nostore' id='nostoreradio' checked><label for='nostoreradio'>未分店</label>";
	store.html(html);
	reportGlobal.html = {};
	reportGlobal.html.store = html;

	var storedata = jsondata['store'];
	var htmltemp = "<input type='checkbox' id='all' value='all'>全选</input><br>";
	for (var i = 0; i < storedata.length; i++) {
		htmltemp +=
			"<input type='checkbox' onclick='_filterchecked(this)' name='门店' value='" +
			storedata[i]['storeName'] + "' id='" + storedata[i]['storeId'] +
			"'><label for='" + storedata[i]['storeId'] + "'>" + storedata[i][
				'storeName'
			] + "</label>";
		if (i % 2 > 0) {
			htmltemp += "<br>";
		}
	}
	$('#storedialog').html(htmltemp);
	reportGlobal.html.storedialog = htmltemp;

	var storegroupdata = jsondata['storeGroup'];
	htmltemp = "<input type='checkbox' id='all' value='all'>全选</input><br>";
	for (var i = 0; i < storegroupdata.length; i++) {
		htmltemp +=
			"<input type='checkbox' onclick='_filterchecked(this)' name='门店组' value='" +
			storegroupdata[i]['storeGroupName'] + "' id='" + storegroupdata[i][
				'storeGroupId'
			] + "'><label for='" + storegroupdata[i]['storeGroupId'] + "'>" +
			storegroupdata[i]['storeGroupName'] + "</label>";
		if (i % 2 > 0) {
			htmltemp += "<br>";
		}
	}
	$('#storegroupdialog').html(htmltemp);
	reportGlobal.html.storegroupdialog = htmltemp;

	html = "";
	var q = dialog.find(".questionaire");

	var qGroup = jsondata['questionGroup'].sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});
	var qdata = jsondata['questions'].sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});
	var v = 0;
	var ds1;
	var ds2 = {
		start_time: $('.dimension-dialog input[name="start"]').datepicker(
			'getDate'),
		end_time: $('.dimension-dialog input[name="end"]').datepicker(
			'getDate')
	};

	for (var j = 0; j < qGroup.length; j++) {
		if (qGroup[j]['businessType'] != 'groupCustomerVoice' && qGroup[j][
				'filterFlag'
			]) {
			html += "<div class='splitter'></div>";
			html += "<div class='heading' id='" + qGroup[j]['questionGroupId'] + "' >" +
				qGroup[j]['name'] + "</div>";
			var judgeCount = 0;
			for (var i = 0; i < qdata.length; i++) {
				if (qdata[i]['filterFlag'] && qGroup[j]['questionGroupId'] == qdata[i][
						'questionGroupId'
					] && (qdata[i]['activePeriod'] == '' || (qdata[i]['activePeriod'] != '' &&
						_compareDates(
							qdata[i]['activePeriod'], ds2)))) {
					if (qdata[i]['questionType'] == 'com.myb.questiontype.SingleSelect') {
						html += "<div class='heading1' id='" + qdata[i]['questionId'] +
							"' ><div class='questionlabel'>" + qdata[i]['questionName'] +
							"</div><div class='optionsdiv'>";
						var option = qdata[i]['options'].sort(function(a, b) {
							return a.sortNumber - b.sortNumber;
						});
						for (var k = 0; k < option.length; k++) {
							if (option[k]['activeFlag']) {
								html += "<input type='checkbox' onclick='_filterchecked(this)' name='" +
									qdata[i]['questionName'] + "' value='" + option[k]['optionValue'] +
									"' id='" + option[k]['optionId'] + "'><label for='" + option[k][
										'optionId'
									] + "'>" + option[k]['optionValue'] + "</label>";
							}
						}
						html += "</div></div>";
					} else if (qdata[i]['questionType'] == 'com.myb.questiontype.Judge') {
						judgeCount += 1;
						if (judgeCount == 1) {
							html += "<span style='padding-left:12px'>"
						}
						html += "<input type='checkbox' onclick='_filterchecked(this)' name='" +
							qdata[i]['questionName'] + "' value='" + qdata[i]['questionName'] +
							"' id='" + qdata[i]['questionId'] + "'><label for='" + qdata[i][
								'questionId'
							] + "'>" + qdata[i]['questionName'] + "</label>";
						if (judgeCount == 1) {
							html += "</span>"
						}
					} else if (qdata[i]['questionType'] == 'com.myb.questiontype.Degree') {
						html += "<div class='heading1' id='" + qdata[i]['questionId'] + "' >" +
							qdata[i]['questionName'] + "<br/><div class='custdiv'>";
						var option = qdata[i]['options'].sort(function(a, b) {
							return a.sortNumber - b.sortNumber;
						});
						for (var k = 0; k < option.length; k++) {
							if (option[k]['activeFlag']) {
								html += "<input type='checkbox'	 onclick='_filterchecked(this)' name='" +
									qdata[i]['questionName'] + "' value='" + option[k]['optionValue'] +
									"'  id='" + option[k]['optionId'] + "'><label for='" + option[k][
										'optionId'
									] + "'>" + option[k]['optionValue'] + "</label>";
							}
						}
						html += "</div></div>";
					} else if (qdata[i]['questionType'] == 'com.myb.questiontype.Score') {
						html += "<div class='heading1' id='" + qdata[i]['questionId'] + "' >" +
							qdata[i]['questionName'] + "<br/><div class='custdiv'>";
						var option = qdata[i]['options'].sort(function(a, b) {
							return a.sortNumber - b.sortNumber;
						});
						for (var k = 0; k < option.length; k++) {
							if (option[k]['activeFlag']) {
								html += "<input type='checkbox'	 onclick='_filterchecked(this)' name='" +
									qdata[i]['questionName'] + "' value='" + option[k]['optionValue'] +
									"'  id='" + option[k]['optionId'] + "'><label for='" + option[k][
										'optionId'
									] + "'>" + option[k]['optionValue'] + "</label>";
							}
						}
						html += "</div></div>";
					}

				}
			}
		}
	}

	q.html(html);

	reportGlobal.html.questionaire = html;
}

function _updateTips(t) {
	var tips = $(".validateTips");
	tips.text(t).addClass("ui-state-highlight").show();
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}

function _checkLength(o, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		_updateTips("" + n + " 的长度必须在 " + min + " 和 " + max + " 之间。");
		return false;
	} else {
		return true;
	}
}

function _saveFilters(name, saveflag) {
	// To save the time period of the filter.
	var start_time = $('.sample-dialog input[name="start"]').datepicker(
		'getDate');
	var end_time = $('.sample-dialog input[name="end"]').datepicker('getDate');
	var period = {
		"start_time": start_time == null ? '' : start_time
			.format('yyyy/MM/dd'),
		"end_time": end_time == null ? '' : end_time.format('yyyy/MM/dd')
	};
	// To save the store option and selected stores of the filter.
	var storeSelected = [];
	var storegroupSelected = [];
	var storeTmp = {};
	//Todo ###01
	$(
		'#' + $('input[name="storeradio"]:checked').val() + 'dialog input:checked').each(
		function() {
			if ("storeGroup" == $('input[name="storeradio"]:checked').val()) {
				for (var i = 0; i < reportGlobal.jsondata.storeGroup.length; i++) {
					if ($(this).attr('id') == reportGlobal.jsondata.storeGroup[i].storeGroupId) {

					}
				}
			}
			storeId.push($(this).attr('id'));
			storeName.push($(this).val());
		})
	var store = {
		"storeType": $('input[name="storeradio"]:checked').val(),
		"store": storeSelected,
		"storeGroup": storegroupSelected
	};
	var inputs = $('.sample-dialog .questionaire input:checked');
	var vsltd = {};
	var nsltd = {};
	var idt, namet, valuet, pidt;
	for (var i = 0; i < inputs.length; i++) {
		idt = $(inputs[i]).attr('id');
		valuet = $(inputs[i]).attr('value');
		namet = $(inputs[i]).attr('name');
		if (valuet != namet) {
			pidt = $(inputs[i]).parent().parent().attr('id');
			if (nsltd['' + namet + '']) {
				vsltd['' + pidt + ''].push(valuet);
				nsltd['' + namet + ''].push(valuet);
			} else {
				vsltd['' + pidt + ''] = [];
				vsltd['' + pidt + ''].push(valuet);
				nsltd['' + namet + ''] = [];
				nsltd['' + namet + ''].push(valuet);
			}
		} else {
			pidt = idt;
			vsltd['' + pidt + ''] = pidt;
			nsltd['' + namet + ''] = valuet;
		}
	}
	var sf = {};
	reportGlobal.savedfiltername = name;
	reportGlobal.selectedStoreType = $('input[name="storeradio"]:checked')
		.val();
	sf.filter_id = uuid(32, 16);
	sf.name = name;
	sf.period = JSON.stringify(period);
	sf.store = JSON.stringify(store);
	sf.value = JSON.stringify(vsltd);
	sf.label = JSON.stringify(nsltd);
	reportGlobal.samplefilter = sf;
	if (!saveflag) {
		reportGlobal.tempFilters = sf;
	} else {
		if (reportGlobal.jsondata["filters"]) {
			reportGlobal.jsondata["filters"].push(sf);
		} else {
			reportGlobal.jsondata["filters"] = [];
			reportGlobal.jsondata["filters"].push(sf);
		}
		var postdata = {
			id: reportGlobal.jsondata.id,
			data: JSON.stringify(sf)
		};
		var url = reportGlobal.ctx + '/page/proreport/addTemplateFilter.json';
		$.ajax({
			type: 'post',
			url: url,
			data: postdata,
			dataType: 'json',
			cache: false,
			success: function(data) {
				if (data.success) {
					console.log("筛选条件'" + sf.name + "'保存成功！");
				}
			}
		});
	}
}

function _saveselectedstore() {
	console.log('save selected store');
}

function _filterchecked(dom) {
	if (dom.checked) {
		// 点击分店，分店组
		if (dom.value == "store" || dom.value == "storegroup") {
			dialog = $("#" + dom.value + "dialog").dialog({
				autoOpen: false,
				height: 300,
				width: 350,
				modal: true,
				buttons: {
					"确认": function() {
						dialog.dialog("close");
					},
					"取消": function() {
						dialog.dialog("close");
					}
				},
				close: function() {
					// form[ 0 ].reset();
					// allFields.removeClass( "ui-state-error" );
				}
			});

			dialog.dialog("open");
			$("#" + dom.value + "dialog").parent().css({
				left: '300px',
				top: '140px'
			});

		} else if (dom.name != dom.value) {

		} else {

		}
	} else {

	}
}

function buildSampleDialogButtons(jsondata) {
	var left = ($(window).width() - $(".sample-dialog").width()) / 2;
	$(".sample-dialog").css({
		left: '100px'
	});
	$(".sample-dialog .close-dialog").click(function() {
		$(".sample-dialog").hide();
	});

	$(".sample-dialog button.savefilterbtn").click(function() {
		$(".sample-dialog").hide();
		$("#saveFilterNameDialog").dialog("open");
	});

	$(".sample-dialog button.tempfilterbtn").click(function() {
		$(".sample-dialog").hide();
		_saveFilters("未保存", false);
		buildFilterTopNav(jsondata);
		_loadSavedDimnsnList(jsondata);
		getTotalCount();
		loadPage();
	});

	$(".sample-dialog .filterbutton").click(function() {
		_refreshFilters(jsondata);
	});

	$(".sample-dialog input[name='start']").click(function() {
		$(".sample-dialog .dialogAlertMessage").hide();
	});
	$(".sample-dialog input[name='end']").click(function() {
		$(".sample-dialog .dialogAlertMessage").hide();
	});
	$(".sample-dialog input[name='nowtime']").click(function() {
		$(".sample-dialog .dialogAlertMessage").hide();
		_checknowtime(this);
	});

	$("#saveFilterNameDialog").dialog({
		autoOpen: false,
		height: 170,
		width: 250,
		modal: true,
		buttons: {
			"保存条件": function() {
				var name = $("#filterName");
				name.removeClass("ui-state-error");
				var bValid = true;
				bValid = _checkLength(name, "名字", 1, 8);
				if (bValid) {
					$(this).dialog("close");
					_saveFilters(name.val(), true);
					buildFilterTopNav(jsondata);
					_loadSavedDimnsnList(jsondata);
					getTotalCount();
					loadPage();
				}
			},
			Cancel: function() {
				$(this).dialog("close");
				console.log('Cancel');
			}
		},
		close: function() {
			console.log('close');
		}
	});
}

function buildDimensionDialogButtons(jsondata) {
	// var left = ($(window).width() - $(".dimension-dialog").width()) / 2;
	// $(".dimension-dialog").css({
	// left : '100px'
	// });

	$(".dimension-dialog .filterbutton").click(function() {
		_refreshDimnsnFilters(jsondata);
	});

	// Cancel the saving of the customized dimension and close the dimension
	// dialog
	$('.dimension-dialog button.close-dialog').click(function() {
		$(".dimension-dialog").toggle();
		_closedmnsndialog();
		loadPage();
	})

	// Save the customized dimension and close the dimension dialog
	$(".savedimnsnbtn").click(
		function() {
			$(".dimension-dialog").toggle();
			_saveDimnsnFilters($('.dimn-sg-name')
				.find('li[class="active"]').find('span').attr('id'), $(
					'.dimn-sg-name').find('li[class="active"]')
				.find('span').text(), true);
			_loadSavedDimnsnList(reportGlobal.jsondata);
			loadPage();
		});

	$(".dimension-dialog input[name='start']").click(function() {
		$(".dimension-dialog .dialogAlertMessage").hide();
	});
	$(".dimension-dialog input[name='end']").click(function() {
		$(".dimension-dialog .dialogAlertMessage").hide();
	});
	$(".dimension-dialog input[name='nowtime']").click(function() {
		$(".dimension-dialog .dialogAlertMessage").hide();
		_checknowtime(this);
	});

}

function uuid(len, radix) {
	var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
		.split('');
	var uuid = [],
		i;
	radix = radix || chars.length;

	if (len) {
		// Compact form
		for (i = 0; i < len; i++)
			uuid[i] = chars[0 | Math.random() * radix];
	} else {
		// rfc4122, version 4 form
		var r;

		// rfc4122 requires these characters
		uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		uuid[14] = '4';

		// Fill in random data. At i==19 set the high bits of clock sequence as
		// per rfc4122, sec. 4.1.5
		for (i = 0; i < 36; i++) {
			if (!uuid[i]) {
				r = 0 | Math.random() * 16;
				uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
			}
		}
	}

	return uuid.join('');
}

function bindButtons() {
	$(".sample").click(function() {
		$(".sample-dialog").toggle();
		return false;
	});

	$(".dimensions").click(function() {
		$('.dimensions').next('ul').toggle();
		return false;
	});

	$("#commenticon").click(function() {
		$('.report-page .comments').toggle();
		return false;
	});

	$(".dimn-new").click(function() {
		$('.dimensions').next('ul').toggle();
		$(".dimension-dialog").toggle();
		_initDimnDialog();
		newsamplegroup($(".newsmplbtn"));
		return false;
	});

	$(".dimn-confirm")
		.click(
			function() {
				$('.dimensions').next('ul').toggle();
				var ckdmns = $('.dimensions-dropdown input:checked')
				var html = '';
				var funcname = '_topNavOtherClick';
				var type = "qust";
				reportGlobal.savedDimns = [];
				for (var i = 0; i < ckdmns.length; i++) {
					if ($(ckdmns[i]).parent().text() == '门店' || $(ckdmns[i]).parent().text() ==
						'门店组') {
						funcname = '_topNavStoreClick';
						type = "stor";
					} else if ($(ckdmns[i]).parent().parent().attr(
							'class') == 'dimensions-dropdown-div-right') {
						funcname = '_topNavCustDimnClick';
						type = "dimn";
					}

					html += '<li><div onclick="' + funcname + '(this);" id="' + $(ckdmns[i]).val() +
						'">' + $(ckdmns[i]).parent().text().replace(
							'编辑删除', '') + ' </div></li>'
					savedDimn = {};
					savedDimn.id = $(ckdmns[i]).val();
					savedDimn.type = type;
					savedDimn.name = $(ckdmns[i]).parent().text()
						.replace('编辑删除', '');
					reportGlobal.savedDimns.push(savedDimn);
				}
				$("ul.dimnsn").children().first().siblings().remove();
				$("ul.dimnsn").children().first().after(html);
				saveDimnTabs();
				return false;
			});

	$(".date-dialog-toggle").click(function() {
		$(".date-dialog").toggle();
		return false;
	});

	$(".date-dialog li").click(
		function() {
			$(".date-dialog-toggle").html($(this).html());
			if ($(".dim.time").html() != "" || $(".dim.time_dimnsn").html() != "") {} else if (
				$(".dim.store").html() != "" || $(".dim.one_dimnsn").html() != "") {
				$(".dim").html("");
				$(".dim.time").html("true")
			} else {
				$(".dim").html("");
				$(".dim.time_dimnsn").html("true");
			}
			$(".date-dialog").hide();
		});

	$('.dimnSGtextspan').dblclick(function() {
		var span = $(this);
		// 根据文本创建文本框 并加入表表中--文本框的样式自己调整
		var text = span.text();
		var txt = $("<input type='text' class='dimnSGinput'>").val(text);
		txt.blur(function() {
			// 失去焦点，保存值。
			var newText = $(this).val();

			// 移除文本框,显示新值
			$(this).remove();
			span.text(newText);
		});
		span.text("");
		span.after(txt);
	});

	$("#showmenu").selectmenu({
		change: function(event, ui) {
			if (ui.item.label == '年') {
				reportGlobal.dimensions.dateType = 'year';
			} else if (ui.item.label == '季') {
				reportGlobal.dimensions.dateType = 'quarter';
			} else if (ui.item.label == '月') {
				reportGlobal.dimensions.dateType = 'month';
			} else if (ui.item.label == '按时间') {
				reportGlobal.dimensions.dateType = '';
			}
			if ($(".dim.time").html() != "" || $(".dim.time_dimnsn").html() != "") {} else if (
				$(".dim.store").html() != "" || $(".dim.one_dimnsn").html() != "") {
				$(".dim").html("");
				$(".dim.time").html("true")
			} else {
				$(".dim").html("");
				$(".dim.time_dimnsn").html("true");
			}
			loadPage();
		}
	});
}

function saveDimnTabs() {
	var savedDimnTabsStr = parseSavedDimns(reportGlobal.savedDimns);
	var postdata = {
		id: reportGlobal.jsondata.id,
		data: savedDimnTabsStr
	};
	var url = reportGlobal.ctx + '/page/proreport/addSavedDimensionsList.json';
	$.ajax({
		type: 'post',
		url: url,
		data: postdata,
		dataType: 'json',
		cache: false,
		success: function(data) {
			if (data.success) {
				console.log("保存维度条件'" + savedDimnTabsStr + "'保存成功！");
			}
		}
	});
}

function parseDimnTabs(dimnTabs) {
	var savedDimns = [];
	var savedDimn;
	for (var i = 0; i < dimnTabs.length; i++) {
		savedDimn = {};
		savedDimn.id = dimnTabs[i].trim().split('_')[1];
		savedDimn.type = dimnTabs[i].trim().split('_')[0];
		savedDimn.name = dimnTabs[i].trim().split('_')[2];
		savedDimns.push(savedDimn);
	}
	return savedDimns;
}

function parseSavedDimns(savedDimns) {
	var dimnTabs = "";
	var savedDimn;
	for (var i = 0; i < savedDimns.length; i++) {
		savedDimn = savedDimns[i];
		dimnTabs += savedDimn.type + '_' + savedDimn.id + '_' + savedDimn.name + ',';
	}
	if (dimnTabs.length > 0) {
		dimnTabs = dimnTabs.substr(0, dimnTabs.length - 1);
	}
	return dimnTabs;
}

function reloadPageMapping() {
	$.ajax({
		type: 'post',
		url: reportGlobal.ctx + '/assets/report/qst_page_mapping.js',
		data: JSON.stringify(''),
		dataType: 'json',
		headers: {
			Accept: "application/json",
			"Content-Type": "application/json"
		},
		processData: false,
		cache: false,
		success: function(data) {
			reportGlobal.qst_page_mapping = data;
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.readyState);
			console.log(textStatus);
		}
	});
}
// "chart_type": ["p8", "p411", "p11", "p10", "p13"]
// "chart_type": ["dimnsn", "one_dimnsn", "store", "time", "time_dimnsn"]
/*
 * "dimnsn" : "p8", "one_dimnsn" : "p411", "store" : "p11", "time" : "p10",
 * "time_dimnsn" : "p13",
 *
 * "questionId" : "16", "questionGroupId" : "10", "chartOneDimnsn" : "p1",
 * "chartMultiDimnsn" : "p4", "chartStore" : "p8", "chartTime" : "p8",
 * "chartTimeDimnsn" : "p8",
 *
 * var v ; // start testing data setting
 *
 * if ($(".dimnsn>li>div:eq(0)").hasClass("active")) { v = "location"; }else if
 * ($(".dimnsn>li>div:eq(1)").hasClass("active")) { v = "mealtime"; }else if
 * ($(".dimnsn>li>div:eq(2)").hasClass("active")) { v = "custtype"; }else if
 * ($(".dimnsn>li>div:eq(3)").hasClass("active")) { v = "whowith"; }else if
 * ($(".dimnsn>li>div:eq(4)").hasClass("active")) { v = "age"; }else if
 * ($(".dimnsn>li>div:eq(5)").hasClass("active")) { v = "sex"; }
 */

function loadPage() {
	var data = reportGlobal.jsondata.questions.sort(function(a, b) {
		return a.sortNumber - b.sortNumber;
	});;
	var qgroupid = $(".question_group_id").text();
	var qid = $(".question_id").text();
	var qstGrup = [];
	var mapping;
	var questionName = "";
	var qlist = [];

	for (var i = 0; i < data.length; i++) {
		if (qgroupid == data[i].questionGroupId) {
			var qst = {};
			qst.questionGroupId = qgroupid;
			qst.questionId = data[i].questionId;
			qst.questionValue = data[i].questionValue;
			qst.questionName = data[i].questionName;
			qst.sortNumber = data[i].sortNumber;
			qstGrup.push(qst);
		}
		if (qgroupid == data[i].questionGroupId && data[i].questionId == qid) {
			mapping = data[i];
			questionName = data[i].questionName;
		}
		if (data[i].businessType != null && data[i].businessType != '' &&
			reportGlobal.specialQuestions != '') {
			var ql = {};
			ql.questionId = data[i].questionId;
			ql.businessType = data[i].businessType;
			qlist.push(ql)
		}
	}
	reportGlobal.specialQuestions == '' ? reportGlobal.specialQuestions = JSON
		.stringify(qlist) : 1 == 1;

	var page = "";
	if ($(".dim.one_dimnsn").html() != "") {
		page = mapping.chartOneDimnsn;
	} else if ($(".dim.dimnsn").html() != "") {
		page = mapping.chartMultiDimnsn;
	} else if ($(".dim.store").html() != "") {
		page = mapping.chartStore;
	} else if ($(".dim.time").html() != "") {
		page = mapping.chartTime;
	} else { // time_dimnsn
		page = mapping.chartTimeDimnsn;
	}
	console.log("打开page " + page);

	var postdata = {
		questionnaire_id: reportGlobal.id,
		question_id: qid,
		page: page,
		filter: JSON.stringify(reportGlobal.samplefilter),
		dimensiontype: reportGlobal.dimensionType,
		dimension: JSON.stringify(reportGlobal.dimensions),
		questionGroup: JSON.stringify(qstGrup),
		groupId: qid,
		questionName: questionName,
		specialQuestions: reportGlobal.specialQuestions
	};
	$.ajax({
		type: 'post',
		url: reportGlobal.ctx + '/page/reportEchart/getChartAllInfo.json', // '/assets/report/testdata-'+page+'.js',
		data: postdata, // JSON.stringify(''),
		dataType: 'json',
		cache: false,
		success: function(data) {
			drawCharts(data.data);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.readyState);
			console.log(textStatus);
		}
	});

	// eval('load_'+page+'()');
	/*
	 * switch(page) {
	 *  }
	 */
}

function getTotalCount() {
	var cdate = new Date();
	cdate.setMonth(cdate.getMonth() - 6);
	var start_time = _string2Json(reportGlobal.samplefilter.period).start_time ==
		'' ? cdate
		.format('yyyy/MM/dd') : _string2Json(reportGlobal.samplefilter.period).start_time;
	var end_time = _string2Json(reportGlobal.samplefilter.period).end_time == '' ?
		(new Date())
		.format('yyyy/MM/dd') : _string2Json(reportGlobal.samplefilter.period).end_time;
	$('.timeperiodlabel').html(start_time + ' - ' + end_time);
	var postdata = {
		questionnaire_id: reportGlobal.id,
		filter: JSON.stringify(reportGlobal.samplefilter)
	};
	$.ajax({
		type: 'post',
		url: reportGlobal.ctx + '/page/proreport/querySampleCountById.json', // '/assets/report/testdata-'+page+'.js',
		data: postdata,
		dataType: 'json',
		cache: false,
		success: function(data) {
			$('.filterlabel').html(
				'样本量: ' + (_string2Json(data.data)).totalCount);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.readyState);
			console.log(textStatus);
		}
	});
}

function drawCharts(jsondata) {
	//根绝参数判断是否要显示chart还是tablea
	var html = "<table class='tablesorter'><thead><tr>";
	html +=
		"<th><div><div class='summary-dropdown-toggle'>Dimension<span class='table-caret' style='float:right;margin-top:11px;'></span></div>";
	html +=
		'<ul class="summary-dropdown-menu"><li><input type="checkbox" name="offering-summary" checked>Offerings by Business Unit</li><li><input type="checkbox" name="iot-summary" checked>Geography by IOTs</li></ul>'; //<li><input type="checkbox" name="platform-summary" checked>Platform</li></ul>';
	html +=
		"</div></th><th>Visit</th><th>Visit MTM</th><th>Engagement Rate</th><th>Engagement Rate MTM</th><th>Engaged Visit</th><th>Conversion Rate</th><th>Conversion Rate MTM</th><th>Web Purchase</th>";
	html += "</tr></thead>";
	// 基于准备好的dom，初始化echarts图表
	var chart = echarts.init($(".chart-main")[0]);
	if (jsondata.option) {
		chart
			.setOption(typeof(jsondata.option) == 'string' ? _string2Json(jsondata.option) :
				jsondata.option);
	}
	$(".pcont .title").html(jsondata.title);
	if (jsondata.chartlegend) {
		$(".pcont .chartlegend").show();
		$(".pcont .chartlegend").html(jsondata.chartlegend);
	} else {
		$(".pcont .chartlegend").hide();
		$(".pcont .chartlegend").html("");
	}
	$(".pcont .commentscontent").html(jsondata.comments);
}

function finalInits() {
	_loadSavedDimnsnList(reportGlobal.jsondata);
	getTotalCount();
}
