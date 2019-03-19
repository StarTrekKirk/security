(function($) {
	function getCtx() {
		return $('meta[ctx]').attr('ctx');
	}

	function getParam(key) {
		var search = location.search;
		if (search.indexOf('?') === -1) {
			return;
		}
		search = search.substring(1);
		var sp = search.split('&');
		for (var i = 0, len = sp.length; i < len; i++) {
			var kv = sp[i].split('=');
			if (key == kv[0]) {
				return kv[1]
			}
		}
		return '';
	}
	window.getCtx = getCtx;
	window.getParam = getParam;
}(jQuery))