package steam.id.front.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import steam.id.front.utils.MarginUtil;

@UIScope
@SpringComponent
public class Info extends VerticalLayout {
    public Info() {
        add(
                new H4("Examples:"),
                label("Steam64 ID:", "76561197960287930"),
                label("Custom URL:", "gabelogannewell"),
                label("Vanity URL:",
                        "https://steamcommunity.com/id/gabelogannewell"),
                label("Permanent Link:",
                        "https://steamcommunity.com/profiles/76561197960287930"));
    }

    private Component label(String title, String text) {
        var bold = new H5(title);
        MarginUtil.set(bold, MarginUtil.Margin.TOP, "0");

        return new Div(bold, new Label(text));
    }
}
