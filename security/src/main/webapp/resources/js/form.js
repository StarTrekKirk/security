(function($) {
	function Form(args) {
		this.dom = $(args.dom);
		this.saveUrl = args.saveUrl;
		this.init();
		this.datahandler = args.datahandler;
	}

	Form.prototype.init = function() {
		var self = this;
		this.dom.find("#form-submit").on("click", function() {
			    self.submit();
		    })
	}

	Form.prototype.getFormData = function() {
		var inputs = this.dom.find('input');
		var data = {};
		inputs.each(function(index, input) {
			    input = $(input);
			    var name = input.attr('name');
			    var value = input.val();
			    var type = input.attr("type");
			    if (type === "checkbox") {
				    if (input.prop("checked")) {
					    var temp = data[name];
					    if (!temp) {
						    data[name] = "";
					    }
					    data[name] += (!temp) ? value : (";" + value);
				    }
			    }
			    else if (type === "radio") {
				    if (input.prop("checked")) {
					    data[name] = value;
				    }
			    }
			    else {
				    data[name] = value;
			    }
		    });
		if (this.datahandler) {
			return this.datahandler(data);
		}
		return data;
	}

	Form.prototype.submit = function() {
		$.ajax({
			    url : getCtx() + this.saveUrl,
			    type : "post",
			    data : this.getFormData(),
			    success : function(result) {
				    if (result) {
					    alert("保存成功")
				    }
				    else {
					    alert("保存失败")
				    }
			    }
		    })
	}

	window.Form = Form;

}(jQuery))