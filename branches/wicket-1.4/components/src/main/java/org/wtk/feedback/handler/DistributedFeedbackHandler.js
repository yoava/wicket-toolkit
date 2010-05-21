wtk.feedback.DistributeHandler = wtk.clazz(wtk.feedback.BaseContainerHandler, {
	priority: 100,

	initialize: function() {
		this.$super.initialize.apply(this, arguments);
		this.panels = new Array();
	},

	onClear: function() {
		var panel;
		while (panel = this.panels.pop()) {
			this.clearPanel(panel);
		}
	},

	onMessage: function() {
		var panel = this.findFeedbackContainer();
		this.renderMessage(panel);
	},

	onShow: function() {
		wtk.each(this.panels, function(panel) {
			this.fireShow(panel);
		}, this);
	},

	setupPanel: function(panel) {
		this.$super.setupPanel.apply(this, arguments);
		this.panels.push(panel);
	},

	/**
	 * Find closest FeedbackPanel.
	 */
	findFeedbackContainer: function() {
		var node = this.context.message.reporter;
		while (node && node != document.body) {
			// node.getAttribute('wtk:feedback') fails for table nodes in IE
			try {
				var feedbackId = node.getAttribute('wtk:feedback');
				if (feedbackId) {
					return wtk.byId(feedbackId);
				}
			} catch (e) {
			}
			node = node.parentNode;
		}

		return null;
	}
});