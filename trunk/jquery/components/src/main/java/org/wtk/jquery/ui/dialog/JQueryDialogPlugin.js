(function($) {
	$.wtk.dialog = {
		close: function() {
			this.dialog('option', 'close', function() {
				$(this).wtk('dialog', 'destroy');
			}).dialog('close');
		},

		destroy: function() {
			// fix double overlay destroy bug 
			this.data('dialog').overlay = null;

			this.dialog('destroy').remove();
		},

		show: function(options) {
			this.dialog(options);
		},

		cleanup: function() {
			$('.wtk-jq-dialogs > *').each(function() {
				$('.ui-dialog [id=\"' + this.id + '\"]').wtk('dialog', 'destroy');
			});
		},

		onShow: function (event, ui) {
			var b = Wicket.Browser;
			if (b.isIE() && !b.isIELessThan7()) {
				$('.ui-widget-overlay').css('width', '100%').css('height', '100%').css('position', 'fixed');
			}
		}
	};
})(jQuery);
