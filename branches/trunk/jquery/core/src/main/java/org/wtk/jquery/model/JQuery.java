package org.wtk.jquery.model;

import org.apache.wicket.behavior.IBehavior;
import org.wtk.behavior.css.CssClass;
import org.wtk.behavior.head.HeadResource;
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
		BLACK_TIE("black-tie", "Black Tie"),
		BLITZER("blitzer", "Blitzer"),
		CUPERTINO("cupertino", "Cupertino"),
		DARK_HIVE("dark-hive", "Dark Hive"),
		DOT_LUV("dot-luv", "Dot Luv"),
		EGGPLANT("eggplant", "Eggplant"),
		EXCITE_BIKE("excite-bike", "Excite Bike"),
		FLICK("flick", "Flick"),
		HOT_SNEAKS("hot-sneaks", "Hot Sneaks"),
		HUMANITY("humanity", "Humanity"),
		LE_FROG("le-frog", "Le Frog"),
		MINT_CHOC("mint-choc", "Mint Choc"),
		NO_THEME("no-theme", "No Theme"),
		OVERCAST("overcast", "Overcast"),
		PEPPER_GRINDER("pepper-grinder", "Pepper Grinder"),
		REDMOND("redmond", "Redmond"),
		SMOOTHNESS("smoothness", "Smoothness"),
		SOUTH_STREET("south-street", "South Street"),
		START("start", "Start"),
		SUNNY("sunny", "Sunny"),
		SWANKY_PURSE("swanky-purse", "Swanky Purse"),
		TRONTASTIC("trontastic", "Trontastic"),
		UI_DARKNESS("ui-darkness", "UI Darkness"),
		UI_LIGHTNESS("ui-lightness", "UI Lightness"),
		VADER("vader", "Vader");

		private String name;
		private String displayName;
		private HeadResource globalResource;
		private HeadResource scopedResource;

		Theme(String name, String displayName) {
			this.name = name;
			this.displayName = displayName;
		}

		public IBehavior createCssScopeBehavior() {
			return new CssClass("." + name);
		}

		@Override
		public String getValue() {
			return name;
		}

		public String getThemeName() {
			return name;
		}

		public String getDisplayName() {
			return displayName;
		}

		public HeadResource getGlobalResource() {
			return globalResource;
		}

		public HeadResource getScopedResource() {
			return scopedResource;
		}
	}

	public static class Position implements HasValue<String[]> {
		public static final Position CENTER_BOTTOM = new Position("center", "bottom");
		public static final Position CENTER_CENTER = new Position("center", "center");
		public static final Position CENTER_TOP = new Position("center", "top");
		public static final Position LEFT_BOTTOM = new Position("left", "bottom");
		public static final Position LEFT_CENTER = new Position("left", "center");
		public static final Position LEFT_TOP = new Position("left", "top");
		public static final Position RIGHT_BOTTOM = new Position("right", "bottom");
		public static final Position RIGHT_CENTER = new Position("right", "center");
		public static final Position RIGHT_TOP = new Position("right", "top");

		private final String[] position;

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
