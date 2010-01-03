wtk.feedback.DistributeHandler = wtk.clazz(wtk.feedback.MessageHandler, {
	initialize: function() {
		this.$super.initialize.apply(this, arguments);
		this.panels = new Array();
	},

	onClear: function() {
		if (this.panels.length == 0) {
			return;
		}
		while (true) {
			var panel = this.panels.pop();
			if (!panel) {
				return;
			}
			panel.innerHTML = '';
		}
	},

	onMessage: function() {
		// get target feedback panel
		var panel = this._feedbackPanelFor(this.context.message.reporter);
		if (!panel) {
			return;
		}

		// setup panel if needed
		if (!panel.hasChildNodes()) {
			var ul = document.createElement('ul');
			ul.className = 'feedback';
			panel.appendChild(ul);
			this.panels.push(panel);

			// trigger panel.onClear() event handler
			var onClear = panel.getAttribute('onclear');
			if (onClear) {
				eval(onClear);
			}
		}

		// add message to panel
		var li = document.createElement('li');
		panel.firstChild.appendChild(li);
		this._addMessage(li);
		this.captureMessage();
		this.rendered();
	},

	onShow: function() {
		wtk.each(this.panels, function(panel) {
			if (panel.getElementsByTagName('li').length) {
				// trigger panel.onShow() event handler
				var onShow = panel.getAttribute('onshow');
				if (onShow) {
					eval(onShow);
				}
			}
		});
	},

	_addMessage: function(li) {
		with (this.context.message) {
			li.className = 'feedback-' + level.toLowerCase();

			if (!reporter) {
				li.innerHTML = message;
				return;
			}

			var linkStart = message.indexOf('[[');
			var linkEnd = message.indexOf(']]', linkStart);

			// check if has [[link]] element
			if (linkStart < 0 || linkEnd < 0) {
				li.innerHTML = message;
				return;
			}

			// build link
			var link = document.createElement('a');
			link.href = '#' + reporter.id;
			link.innerHTML = message.substring(linkStart + 2, linkEnd);
			link.onclick = function() {
				try {
					if (reporter.focus) {
						reporter.focus();
					} else if (reporter.scrollIntoView) {
						reporter.scrollIntoView();
					}
					return false;
				} catch(e) {
				}
			};

			// add message
			li.appendChild(document.createTextNode(message.substring(0, linkStart)));
			li.appendChild(link);
			li.appendChild(document.createTextNode(message.substring(linkEnd + 2)));
		}
	},

	/**
	 * Find closest FeedbackPanel.
	 * @param reporter
	 */
	_feedbackPanelFor: function(reporter) {
		var node = reporter;
		while (node && node != document.body) {
			var feedbackId = node.getAttribute('wtk:feedback');
			if (feedbackId) {
				return wtk.byId(feedbackId);
			}

			node = node.parentNode;
		}

		return null;
	}
});