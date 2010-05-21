var wtk = {
	q: function(selector, context) {
		return new wtk.q.fn.init(selector, context);
	},

	byId: function(id) {
		return id ? document.getElementById(id) : null
	},

	nop: function() {
	},

	ready: function(fn) {
		if (window.loaded) {
			fn();
		} else {
			Wicket.Event.addDomReadyEvent(fn);
		}
	},

	each: function(array, fn, scope) {
		for (var i = 0, len = array.length; i < len; i++) {
			var result = fn.call(scope || array[i], array[i], i, array);
			if (result) {
				return result;
			}
		}
	},

	extend: function(copyTo, copyFrom) {
		for (var m in copyFrom) {
			copyTo[m] = copyFrom[m];
		}
	},

	clazz: function(optionalSuper, prototype) {
		var clazz = Wicket.Class.create();
		if (prototype) {
			clazz.prototype = new optionalSuper();
			wtk.extend(clazz.prototype, prototype);
			clazz.prototype.$super = optionalSuper.prototype;
		} else {
			clazz.prototype = optionalSuper;
		}
		return clazz;
	}
};

wtk.q.fn = {
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

wtk.q.fn.init.prototype = wtk.q.fn;