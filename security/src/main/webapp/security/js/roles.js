(function($) {
	var datalist = new DataList({
		    dom : ".datalist",
		    editUrl : "/role",
		    deleteUrl : "/deleteRole"
	    });
	$("#add").on("click", function(event) {
		    window.open(getCtx() + "/role");
	    });
	$("#remove").on("click", function(event) {
		    var ids = datalist.getSelected();
		    $.ajax({
			        url : getCtx() + "/deleteRole",
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