wtk.feedback.DefaultFeedbackHandler = wtk.clazz(wtk.feedback.BaseContainerHandler, {
	priority: 50,

	initialize: function(targetId) {
		this.$super.initialize.apply(this, arguments);
		this.targetId = targetId;
	},

	onClear: function() {
		this.clearPanel(wtk.byId(this.targetId));
	},

	onMessage: function() {
		this.renderMessage(wtk.byId(this.targetId));
	},

	onShow: function() {
		this.fireShow(wtk.byId(this.targetId));
	}
});