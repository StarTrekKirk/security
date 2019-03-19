(function() {

	function getFormData() {
		var form = $('form');
		var inputs = form.find('input');
		var data = {};
		inputs.each(function(index, input) {
			    input = $(input);
			    var name = input.attr('name');
			    var value = input.val();
			    data[name] = value;
		    });
		return data;
	}

	function getReturnUrl() {
		var search = location.search;
		if (search.indexOf('?') === -1) {
			return;
		}
		search = search.substring(1);
		return search.substring("returnUrl=".length);
	}

	$('button').on('click', function(event) {
		    $.ajax({
			        url : getCtx() + '/login',
			        type : 'get',
			        data : getFormData(),
			        success : function(result) {
				        if (result == 0) {
					        var returnUrl = getReturnUrl("returnUrl");
					        if (!returnUrl) {
						        location.href = getCtx() + '/home';
					        }
					        else {

						        location.href = decodeURI(returnUrl);
					        }
				        }
				        else {
					        var rs;
					        switch (result) {
						        case 1 :
							        rs = "account not enable";
							        break;
						        case 2 :
							        rs = "account does not exist";
							        break;
						        case 3 :
							        rs = "incorrect password";
							        break;
						        default :
							        rs = "login fail";
					        }
					        $('#callback').text(rs);
				        }
			        }
		        });
	    });
}())