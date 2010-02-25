wtk.feedback.FeedbackAwareCssHandler = wtk.clazz(wtk.feedback.MessageHandler, {
	priority: 110,

	initialize: function(cssClass, markAllParents) {
		this.$super.initialize.apply(this, arguments);
		this.nodes = new Array();
		this.cssClass = cssClass;
		this.regExp = new RegExp('^' + cssClass + '$| ' + cssClass + '$|^' + cssClass + ' | ' + cssClass + ' ');
		this.markAll = markAllParents;
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
			if (node.className.search(this.regExp) >= 0) {
				this.nodes.push(node);
				this.mark(node);
				this.rendered();
				if (!this.markAll) {
					return;
				}
			}
			node = node.parentNode;
		}
	},

	mark: function(node) {
		var levelClass = this.cssClass + '-' + this.context.message.level.toLowerCase();
		if (!node.feedback) {
			node.feedback = {};
		}
		if (!node.feedback[levelClass]) {
			node.feedback[levelClass] = true;
			node.className += ' ' + levelClass;
		}
	},

	unmark: function(node) {
		if (node.feedback) {
			for (var levelClass in node.feedback) {
				node.className = node.className.replace(' ' + levelClass, '');
			}
		}
		delete node.feedback;
	}
});