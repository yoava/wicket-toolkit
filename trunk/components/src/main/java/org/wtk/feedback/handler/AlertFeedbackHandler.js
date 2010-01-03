wtk.feedback.AlertHandler = wtk.clazz(wtk.feedback.Handler, {
	format: function(reporter, message, level) {
		return ${format};
	},

	onFeedback: function(messages) {
		var message = '';

		wtk.each(messages, function(msg) {
			message += this.format(msg.reporter, msg.message, msg.level) + '\n';
		}, this);

		if (message) {
			alert(message);
		}
	}
});