window.PageInjector = {
	injectHead: function() {
		try {
			document.write(${head});
		} catch (e) {
		}
	},

	injectBodyHeader: function() {
		try {
			document.write(${header});
		} catch (e) {
		}
	},

	injectBodyFooter: function() {
		try {
			document.write(${footer});
		} catch (e) {
		}
	}
};