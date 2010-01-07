wtk.feedback.AlertHandler = wtk.clazz(wtk.feedback.Handler, {
	priority: 0,

	initialize: function(format) {
		this.$super.initialize.apply(this, arguments);
		this.format = format;
	},

	onFeedback: function(messages) {
		var text = '';

		wtk.each(messages, function(msg) {
			text += this.format(msg.message, msg.level, msg.reporter) + '\n';
		}, this);

		if (text) {
			alert(text);
		}
	}
});