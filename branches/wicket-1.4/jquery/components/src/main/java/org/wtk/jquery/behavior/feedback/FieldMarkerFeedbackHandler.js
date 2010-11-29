(function($) {
	wtk.feedback.FieldMarker = wtk.clazz(wtk.feedback.MessageHandler, {
		priority: 110,

		cssClass: '',
		tagNames: {
			'select': true,
			'textarea': true
		},

		inputTypes: {
			'text': true,
			'password': true,
			'radio': true,
			'checkbox': true,
			'file': true
		},

		initialize: function(cssClass) {
			this.cssClass = cssClass;
		},

		onClear: function() {
			$('.' + this.cssClass).each(function() {
				this.parentNode.replaceChild(this.firstChild, this);
			});
		},

		onMessage: function() {
			if (!this.context.message.reporter) {
				return;
			}

			if (!this.isMarked() && this.shouldMark()) {
				this.mark();
			}
		},

		shouldMark: function() {
			var reporter = this.context.message.reporter;
			var tagName = reporter.tagName.toLowerCase();
			if (this.tagNames[tagName]) {
				return true;
			}

			if (tagName == 'input') {
				var type = reporter.type.toLowerCase();
				return this.inputTypes[type];
			}
			return false;
		},

		isMarked: function() {
			return this.context.message.reporter.parentNode.className.indexOf(this.cssClass) >= 0
		},

		mark: function() {
			with (this.context.message) {
				var marker = document.createElement('span');
				marker.className = this.cssClass + ' ' + this.cssClass + '-' + level.toLowerCase();
				$(reporter).wrap(marker);
				this.rendered();
			}
		}
	});
})(jQuery);