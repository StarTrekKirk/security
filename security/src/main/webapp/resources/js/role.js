(function($) {
	var form = new Form({
		    dom : ".form",
		    saveUrl : "/saveRole",
		    datahandler : function(data) {
			    var permission = data.permission;
			    var ps = permission.split(";");
			    var p = 0;
			    ps.forEach(function(_p) {
				        p += parseInt(_p);
			        });
			    data.permission = p;
			    return data;
		    }
	    });
}(jQuery))