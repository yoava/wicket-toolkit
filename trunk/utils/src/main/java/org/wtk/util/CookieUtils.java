package org.wtk.util;

import javax.servlet.http.Cookie;

/**
 * @author Yoav Aharoni
 */
public class CookieUtils {
    public static String getCookie(String name) {
        Cookie cookie = RequestUtils.getWebRequest().getCookie(name);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    public static void setCookie(String name, Object value) {
        if (value == null) {
            clearCookie(name);
        } else {
            RequestUtils.getWebResponse().addCookie(new Cookie(name, value.toString()));
        }
    }

    public static void clearCookie(String name) {
        RequestUtils.getWebResponse().clearCookie(new Cookie(name, null));
    }
}
