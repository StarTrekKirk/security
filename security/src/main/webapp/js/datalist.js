(function($) {
	function DataList(args) {
		this.dom = $(args.dom);
		this.deleteUrl = args.deleteUrl;
		this.editUrl = args.editUrl;
		this.init();
	}

	DataList.prototype.getSelected = function() {
		var selectors = this.dom.find(".datalist-selector");
		var ids = [];
		var self = this;
		selectors.each(function(index, selector) {
			    if ($(selector).prop('checked')) {
				    var selected = self._getSelected(selector);
				    ids.push(selected);
			    }
		    });
		return ids;
	}

	DataList.prototype.init = function() {
		var self = this;
		this.dom.find(".datalist-remove").on('click', function(event) {
			    var selected = self._getSelected(event.target);
			    $.ajax({
				        url : getCtx() + self.deleteUrl,
				        type : 'post',
				        data : {
					        id : selected
				        },
				        success : function(result) {
					        window.location.reload();
				        }
			        });
		    });
		this.dom.find(".datalist-edit").on('click', function(event) {
			    var selected = self._getSelected(event.target);
			    window.open(getCtx() + self.editUrl + "?id=" + selected);
		    });

		this.dom.find(".datalist-selector-all").on('click', function(event) {
			    var all = $(event.target);
			    var selected = all.prop("checked");
			    var selectors = self.dom.find(".datalist-selector");
			    selectors.each(function(index, selector) {
				        $(selector).prop('checked', selected);
			        });
		    });
	}

	DataList.prototype._getSelected = function(target) {
		var parent = $(target).closest("tr");
		var tds = parent.find('td');
		var id = $(tds[1]);
		return id.text();
	}

	window.DataList = DataList;
}(jQuery))