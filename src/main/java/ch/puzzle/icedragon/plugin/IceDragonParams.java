package ch.puzzle.icedragon.plugin;

import java.util.Optional;

public class IceDragonParams {

    public static String getParameter(String paramName) {
        return getParameter(paramName, null);
    }

    public static String getParameter(String paramName, String defaultValue) {
        return Optional.ofNullable(System.getenv(paramName))
                .or(() -> Optional.ofNullable(System.getProperty(paramName)))
                .or(() -> Optional.ofNullable(defaultValue))
                .orElseThrow();
    }
}
