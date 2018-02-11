(function($) {
	var datalist = new DataList({
		    dom : ".datalist",
		    editUrl : "/account",
		    deleteUrl : "/deleteAccount"
	    });
	$("#add").on("click", function(event) {
		    window.open(getCtx() + "/account");
	    });
	$("#remove").on("click", function(event) {
		    var ids = datalist.getSelected();
		    $.ajax({
			        url : getCtx() + "/deleteAccount",
			        type : 'post',
			        data : {
				        id : ids.join(";")
			        },
			        success : function(result) {
				        window.location.reload();
			        }
		        });
	    });
}(jQuery))