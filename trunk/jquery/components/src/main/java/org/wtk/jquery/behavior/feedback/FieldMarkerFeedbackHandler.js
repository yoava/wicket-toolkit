(function($) {
	wtk.feedback.FieldMarker = wtk.clazz(wtk.feedback.MessageHandler, {
		cssClass: 'feedback-marker',
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
			var tagName = this.context.message.reporter.tagName.toLowerCase();
			if (this.tagNames[tagName]) {
				return true;
			}

			if (tagName == 'input') {
				var type = this.context.message.reporter.type.toLowerCase();
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
				marker.className = this.cssClass + ' ' + this.cssClass + '-' + level;
				$(reporter).wrap(marker);
				this.rendered();
			}
		}
	});
})(jQuery);