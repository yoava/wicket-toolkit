(function($) {
	wtk.feedback.FieldMessage = wtk.clazz(wtk.feedback.BaseContainerHandler, {
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

		panels: {},

		initialize: function(priority) {
			this.priority = priority;
		},

		onClear: function() {
			for (var id in this.panels) {
				$(this.panels[id]).remove();
			}
			this.panels = {};
		},

		onMessage: function() {
			if (this.isField()) {
				var panel = this.getContainer();
				this.renderMessage(panel);
			}
		},

		getContainer: function() {
			var reporter = this.context.message.reporter;
			var panel = this.panels[reporter.id];
			if (panel) {
				return panel;
			}

			panel = document.createElement('div');
			panel.className = 'field-feedback';
			this.panels[reporter.id] = panel;
			$(reporter).after(panel);
			return panel;
		},

		isField: function() {
			var reporter = this.context.message.reporter;
			if (!reporter.id) {
				return false;
			}
			var tagName = reporter.tagName.toLowerCase();
			if (this.tagNames[tagName]) {
				return true;
			}

			if (tagName == 'input') {
				var type = reporter.type.toLowerCase();
				return this.inputTypes[type];
			}
			return false;
		}
	});
})(jQuery);