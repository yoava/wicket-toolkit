wtk.feedback.BaseContainerHandler = wtk.clazz(wtk.feedback.MessageHandler, {
	clearPanel: function(panel) {
		if (panel) {
			panel.innerHTML = '';
			this.fireClear(panel);
		}
	},

	renderMessage: function(panel) {
		if (panel) {
			if (!panel.hasChildNodes()) {
				this.setupPanel(panel);
			}

			var li = document.createElement('li');
			panel.firstChild.appendChild(li);
			this._addMessageBody(li);

			this.captureMessage();
			this.rendered();
		}
	},

	setupPanel: function(panel) {
		this.fireClear(panel);

		var ul = document.createElement('ul');
		ul.className = 'feedback';
		panel.appendChild(ul);
	},

	fireClear: function(panel) {
		var onClear = panel.getAttribute('onclear');
		if (onClear) {
			new Function(onClear).apply(panel);
		}
	},

	fireShow: function(panel) {
		if (panel && panel.getElementsByTagName('li').length) {
			panel.scrollIntoView();

			var onShow = panel.getAttribute('onshow');
			if (onShow) {
				new Function(onShow).apply(panel);
			}
		}
	},

	_addMessageBody: function(li) {
		with (this.context.message) {
			li.className = 'feedback-' + level.toLowerCase();

			var linkStart = message.indexOf('[[');
			var linkEnd = message.indexOf(']]', linkStart);

			// check if has [[link]] element
			if (linkStart < 0 || linkEnd < 0) {
				li.innerHTML = message;
				return;
			}

			// build link
			var link = document.createElement('a');

			if (reporter) {
				link.className = 'reporter';
				link.href = '#' + reporter.id;
				link.onclick = function() {
					wtk.feedback.focus(reporter);
					return false;
				};
			} else {
				link.className = 'no-reportor';
			}

			link.innerHTML = message.substring(linkStart + 2, linkEnd);

			// add message
			li.appendChild(document.createTextNode(message.substring(0, linkStart)));
			li.appendChild(link);
			li.appendChild(document.createTextNode(message.substring(linkEnd + 2)));
		}
	}
});

wtk.feedback.focus = function(node) {
	try {
		if (node.className.indexOf('input-container') >= 0) {
			var child = node.getElementsByTagName('input')[0];
			if (child) {
				wtk.feedback.focus(child);
				return;
			}
		}

		if (node.scrollIntoView) {
			node.scrollIntoView();
		}
		if (node.focus) {
			node.focus();
		}
	} catch(e) {
	}
};