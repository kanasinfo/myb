(function($){
	$.validator.addMethod("chcharacter", function(value, element){
	var tel = /^[u4e00-u9fa5]+$/;
	return this.optional(element) || (tel.test(value));
	}, "请输入汉字");
})