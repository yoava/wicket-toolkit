window.${objectName} = {
	injectHead: function() {
		try {
			document.write(${head});
		} catch (e) {
		}
	},

	inject: function() {
		try {
			document.write(${body});
		} catch (e) {
		}
	}
};