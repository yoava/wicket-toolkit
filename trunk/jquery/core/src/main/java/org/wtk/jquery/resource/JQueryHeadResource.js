(function($) {
	window.wtk = $;

	if (window.loaded) {
		$.isReady = true;
	}

	$.wtk = {
		extend: $.extend
	};

	$.fn.wtk = function(util, method) {
		var f = $.wtk[util];
		var m = f[method];
		var args;
		if (typeof m == 'function') {
			args = Array.prototype.slice.call(arguments, 2);
			m.apply(this, args);
		} else {
			args = Array.prototype.slice.call(arguments, 1);
			f.apply(this, args);
		}
		return this;
	};
})(jQuery);
