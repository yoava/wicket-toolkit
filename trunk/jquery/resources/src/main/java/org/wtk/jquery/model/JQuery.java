package org.wtk.jquery.model;

import org.apache.wicket.behavior.IBehavior;
import org.wtk.behavior.CssClass;
import org.wtk.jquery.resource.JQuerySkin;

/**
 * @author Yoav Aharoni
 */
public class JQuery {
	public static JQuerySkin getGlobalTheme(final Theme theme) {
		return new JQuerySkin(theme, true);
	}

	public static JQuerySkin createScopedTheme(final Theme theme) {
		return new JQuerySkin(theme, false);
	}

	/**
	 * @author Yoav Aharoni
	 */
	public enum Effect {
		BLIND("blind"),
		BOUNCE("bounce"),
		CLIP("clip"),
		DROP("drop "),
		EXPLODE("explode"),
		FOLD("fold"),
		HIGHLIGHT("highlight"),
		PUFF("puff"),
		PULSATE("pulsate "),
		SCALE("scale"),
		SHAKE("shake"),
		SIZE("size"),
		SLIDE("slide"),

		TRANSFER("transfer");

		private String effect;

		Effect(String effect) {
			this.effect = effect;
		}

		public String getEffect() {
			return effect;
		}
	}

	/**
	 * @author Yoav Aharoni
	 */
	public enum Theme {
		BLACK_TIE("black-tie"),
		BLITZER("blitzer"),
		CUPERTINO("cupertino"),
		DARK_HIVE("dark-hive"),
		DOT_LUV("dot-luv"),
		EGGPLANT("eggplant"),
		EXCITE_BIKE("excite-bike"),
		FLICK("flick"),
		HOT_SNEAKS("hot-sneaks"),
		HUMANITY("humanity"),
		LE_FROG("le-frog"),
		MINT_CHOC("mint-choc"),
		NO_THEME("no-theme"),
		OVERCAST("overcast"),
		PEPPER_GRINDER("pepper-grinder"),
		REDMOND("redmond"),
		SMOOTHNESS("smoothness"),
		SOUTH_STREET("south-street"),
		START("start"),
		SUNNY("sunny"),
		SWANKY_PURSE("swanky-purse"),
		TRONTASTIC("trontastic"),
		UI_DARKNESS("ui-darkness"),
		UI_LIGHTNESS("ui-lightness"),
		VADER("vader");

		private String theme;

		Theme(String theme) {
			this.theme = theme;
		}

		public String getTheme() {
			return theme;
		}

		public IBehavior createCssScopeBehavior() {
			return new CssClass("." + theme);
		}
	}

	public enum Position {
		CENTER("center"), LEFT("left"), RIGHT("right"), TOP("top"), BOTTOM("bottom");

		private String position;

		Position(String position) {
			this.position = position;
		}

		public String getPosition() {
			return position;
		}
	}
}
