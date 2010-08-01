var PageInjector = {
	head: ${head},
	header: ${header},
	footer: ${footer},

	injectHead: function() {
		try {
			document.write(PageInjector.head);
		} catch (e) {
		}
	},

	injectBodyHeader: function() {
		try {
			document.write(PageInjector.header);
		} catch (e) {
		}
	},

	injectBodyFooter: function() {
		try {
			document.write(PageInjector.footer);
		} catch (e) {
		}
	}
};