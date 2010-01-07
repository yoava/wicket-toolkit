wtk.feedback.ToogleCssHandler = wtk.clazz(wtk.feedback.BaseContainerHandler, {
	priority: 110,

	initialize: function() {
		this.$super.initialize.apply(this, arguments);
		this.nodes = new Array();
	},

	onClear: function() {
		var node;
		while (node = this.nodes.pop()) {
			this.unmark(node);
		}
	},

	onMessage: function() {
		var node = this.context.message.reporter;
		while (node && node != document.body) {
			if (node.className.indexOf('feedback-aware') >= 0) {
				this.nodes.push(node);
				this.mark(node);
				this.rendered();
			}
			node = node.parentNode;
		}
	},

	mark: function(node) {
		node.className = node.className.replace(/feedback-aware no-feedback/, 'feedback-aware has-feedback');
	},

	unmark: function(node) {
		node.className = node.className.replace(/feedback-aware has-feedback/, 'feedback-aware no-feedback');
	}
});