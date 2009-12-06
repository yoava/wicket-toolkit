(function($) {
	$.wtk.dialog = {
		close: function() {
			this.dialog('option', 'close', function() {
				$(this).wtk('dialog', 'destroy');
			}).dialog('close');
		},
		destroy: function() {
			this.dialog('destroy').remove();
		},
		show: function(options) {
			this.dialog(options);
		},

		cleanup: function() {
			$('.wtk-jq-dialogs > *').each(function() {
				$('.ui-dialog [id=\"' + this.id + '\"]').wtk('dialog', 'destroy');
			});
		}
	};
})(jQuery);
