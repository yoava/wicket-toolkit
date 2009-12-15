package org.wtk.jquery.model;

import org.apache.wicket.behavior.IBehavior;
import org.wtk.behavior.css.CssClass;
import org.wtk.model.HasValue;

import java.util.Arrays;

/**
 * @author Yoav Aharoni
 */
public class JQuery {
	/**
	 * @author Yoav Aharoni
	 */
	public enum Effect implements HasValue<String> {
		BLIND("blind"),
		BOUNCE("bounce"),
		CLIP("clip"),
		DROP("drop"),
		EXPLODE("explode"),
		FOLD("fold"),
		HIGHLIGHT("highlight"),
		PUFF("puff"),
		PULSATE("pulsate"),
		SCALE("scale"),
		SHAKE("shake"),
		SIZE("size"),
		SLIDE("slide"),
		TRANSFER("transfer");

		private String effect;

		Effect(String effect) {
			this.effect = effect;
		}

		@Override
		public String getValue() {
			return effect;
		}
	}

	/**
	 * @author Yoav Aharoni
	 */
	public enum Theme implements HasValue<String> {
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

		public IBehavior createCssScopeBehavior() {
			return new CssClass("." + theme);
		}

		@Override
		public String getValue() {
			return theme;
		}
	}

	public static class Position implements HasValue<String[]> {

		private final String[] position;
		public static final Position CENTER_BOTTOM = new Position("center", "bottom");
		public static final Position CENTER_CENTER = new Position("center", "center");
		public static final Position CENTER_TOP = new Position("center", "top");
		public static final Position LEFT_BOTTOM = new Position("left", "bottom");
		public static final Position LEFT_CENTER = new Position("left", "center");
		public static final Position LEFT_TOP = new Position("left", "top");
		public static final Position RIGHT_BOTTOM = new Position("right", "bottom");
		public static final Position RIGHT_CENTER = new Position("right", "center");
		public static final Position RIGHT_TOP = new Position("right", "top");

		public Position(int x, int y) {
			this(String.valueOf(x), String.valueOf(y));
		}

		private Position(String x, String y) {
			position = new String[]{x, y};
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Position that = (Position) o;
			return Arrays.equals(position, that.position);

		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(position);
		}

		@Override
		public String[] getValue() {
			return position;
		}
	}
}
