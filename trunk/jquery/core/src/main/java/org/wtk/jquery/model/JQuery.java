package org.wtk.jquery.model;

import org.apache.wicket.behavior.IBehavior;
import org.wtk.behavior.css.CssClass;
import org.wtk.behavior.head.HeadResource;
import org.wtk.jquery.resource.css.JQueryUILightnessScopedTheme;
import org.wtk.jquery.resource.css.JQueryUILightnessTheme;
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
		BLACK_TIE("black-tie", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		BLITZER("blitzer", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		CUPERTINO("cupertino", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		DARK_HIVE("dark-hive", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		DOT_LUV("dot-luv", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		EGGPLANT("eggplant", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		EXCITE_BIKE("excite-bike", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		FLICK("flick", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		HOT_SNEAKS("hot-sneaks", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		HUMANITY("humanity", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		LE_FROG("le-frog", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		MINT_CHOC("mint-choc", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		NO_THEME("no-theme", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		OVERCAST("overcast", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		PEPPER_GRINDER("pepper-grinder", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		REDMOND("redmond", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		SMOOTHNESS("smoothness", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		SOUTH_STREET("south-street", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		START("start", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		SUNNY("sunny", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		SWANKY_PURSE("swanky-purse", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		TRONTASTIC("trontastic", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		UI_DARKNESS("ui-darkness", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		UI_LIGHTNESS("ui-lightness", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get()),
		VADER("vader", JQueryUILightnessTheme.get(), JQueryUILightnessScopedTheme.get());
		private HeadResource globalResource;
		private HeadResource scopedResource;

		private String theme;

		Theme(String theme, HeadResource globalResource, HeadResource scopedResource) {
			this.theme = theme;
			this.globalResource = globalResource;
			this.scopedResource = scopedResource;
		}

		public IBehavior createCssScopeBehavior() {
			return new CssClass("." + theme);
		}

		@Override
		public String getValue() {
			return theme;
		}

		public HeadResource getGlobalResource() {
			return globalResource;
		}

		public HeadResource getScopedResource() {
			return scopedResource;
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
