wtk.feedback = {
	dirty: false,
	handlers: new Array(),

	addHandler: function(handler) {
		this.dirty = true;
		this.handlers.push(handler);
	},

	msg: function(reporter, message, level) {
		return {
			reporter: wtk.byId(reporter),
			message: message,
			level: level
		};
	},

	dispatch: function(messages) {
		if (this.dirty) {
			this.dirty = false;
			this.handlers.sort(this._compare);
		}
		wtk.each(this.handlers, function() {
			this.onFeedback(messages);
		});
		this.cleanup(messages);
	},

	cleanup: function(messages) {
		wtk.each(messages, function() {
			this.reporter = null;
		});
	},

	_compare: function(a, b) {
		if (a.priority == b.priority) {
			return 0;
		}
		return a.priority < b.priority ? 1 : -1;
	}
};

wtk.feedback.Handler = wtk.clazz({
	UNDEFINED: 'UNDEFINED',
	DEBUG: 'DEBUG',
	INFO: 'INFO',
	WARNING: 'WARNING',
	ERROR: 'ERROR',
	FATAL: 'FATAL',

	/**
	 * Higher priority comes first (0 invoked last).
	 * Usually value between 0 to 100 or value above 100 for non-capturing handlers.
	 */
	priority: 0,

	initialize: function() {
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
