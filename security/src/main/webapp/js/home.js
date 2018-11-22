(function() {
	function appendInfoItem(key, value) {
		var p = $('<p/>').appendTo('#content');
		var k = $('<span/>').appendTo(p);
		k.text(key);
		var v = $('<span/>').appendTo(p);
		v.text(value);
	}
	function showInfos(infos) {
		$('#user').text(infos.user);
		appendInfoItem("session数：", infos.sessionCount);
		var userCounts = infos.userCounts;
		appendInfoItem("user数：", infos.userCount);
		for (var name in userCounts) {
			appendInfoItem("用户" + name + "：", userCounts[name]);
		}
	}
	$.ajax({
		    url : getCtx() + "/serverinfo",
		    type : 'get',
		    dataType : 'json',
		    success : function(result) {
			    showInfos(result);
		    }
	    });
}())