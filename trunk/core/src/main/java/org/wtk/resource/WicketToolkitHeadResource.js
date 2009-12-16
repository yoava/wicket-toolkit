var wtk = function(selector, context) {
	return new wtk.fn.init(selector, context);
};
wtk.fn = {
	init: function(selector, context) {
		var id = selector.substr(1);
		this[0] = document.getElementById(id);
	},
	remove: function() {
		this[0].parentNode.removeChild(this[0]);
		return this;
	},
	append: function(html) {
		var frag = document.createElement('span');
		frag.innerHTML = html;
		this[0].appendChild(frag.firstChild);
	}
};

wtk.fn.init.prototype = wtk.fn;