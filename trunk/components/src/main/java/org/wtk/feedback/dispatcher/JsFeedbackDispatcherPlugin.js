wtk.feedback = {
	handlers: {},

	addHandler: function(handler) {
		this.handlers[handler.id] = handler;
	},

	removeHandler: function(handler) {
		if (typeof handler == 'string') {
			delete this.handlers[handler];
		} else {
			delete this.handlers[handler.id];
		}
	},

	msg: function(reporter, message, level) {
		return {
			reporter: wtk.byId(reporter),
			message: message,
			level: level
		};
	},

	dispatch: function(messages) {
		var handlers = wtk.feedback.handlers;
		for (var id in handlers) {
			handlers[id].onFeedback(messages);
		}
		this.cleanup(messages);
	},

	cleanup: function(messages) {
		wtk.each(messages, function() {
			this.reporter = null;
		});
	}
};

wtk.feedback.Handler = wtk.clazz({
	UNDEFINED: 'UNDEFINED',
	DEBUG: 'DEBUG',
	INFO: 'INFO',
	WARNING: 'WARNING',
	ERROR: 'ERROR',
	FATAL: 'FATAL',

	initialize: function(id) {
		this.id = id;
	},

	onFeedback: wtk.nop
});

wtk.feedback.MessageHandler = wtk.clazz(wtk.feedback.Handler, {
	onFeedback: function(messages) {
		this.context = {
			messages: messages,
			rendered: 0,
			i: 0,
			message: null
		};

		this.onClear();

		with (this.context) {
			for (; i < messages.length; i++) {
				message = messages[i];
				this.onMessage(message);
			}

			if (rendered) {
				this.onShow();
			}
		}

		delete this.context;
	},

	rendered: function() {
		this.context.rendered++;
	},

	captureMessage: function() {
		this.context.messages.splice(this.context.i, 1);
		this.context.i--;
	},

	onClear: wtk.nop,
	onMessage: wtk.nop,
	onShow: wtk.nop
});
