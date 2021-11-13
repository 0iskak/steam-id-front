package steam.id.front.utils;

import com.vaadin.flow.component.Component;

public class MarginUtil {
    public static void set(Component component, Margin key, String value) {
        var el = component.getElement().getStyle();
        switch (key) {
            case LEFT -> el.set("margin-left", value);
            case TOP -> el.set("margin-top", value);
            case RIGHT -> el.set("margin-right", value);
            case BOTTOM -> el.set("margin-bottom", value);
            case X -> {
                set(component, Margin.LEFT, value);
                set(component, Margin.RIGHT, value);
            }
            case Y -> {
                set(component, Margin.TOP, value);
                set(component, Margin.BOTTOM, value);
            }
            case ALL -> {
                set(component, Margin.X, value);
                set(component, Margin.Y, value);
            }
        }
    }

    public enum Margin {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        X,
        Y,
        ALL
    }
}
