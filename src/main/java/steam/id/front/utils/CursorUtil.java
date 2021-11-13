package steam.id.front.utils;

import com.vaadin.flow.component.Component;

public class CursorUtil {

    public static void set(Component component, Cursor type) {
        var el = component.getElement().getStyle();
        el.set("cursor", type.name().toLowerCase());
    }

    public enum Cursor {
        POINTER
    }
}
