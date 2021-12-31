package steam.id.front.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import steam.id.front.utils.CursorUtil;
import steam.id.front.utils.MarginUtil;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@UIScope
@SpringComponent
public class Data extends VerticalLayout {
    private final String API = "http://34.118.68.215/profile/";
    @Getter
    private JsonNode json;

    public void parse(String value) throws IOException {
        String json = new String(new URL(API+value).openStream().readAllBytes());
        json = json.substring(json.indexOf('{'), json.lastIndexOf('}') + 1);
        this.json = new ObjectMapper().readValue(json, JsonNode.class);
        removeAll();
        add(top(), main());
        UI.getCurrent().navigate(get("steamId64"));
    }

    private String get(String key) {
        var value = json.get(key);
        if (value == null)
            return " ";
        return value.asText().replace("\"", "");
    }

    private Component top() {
        var layout = new HorizontalLayout();
        var right = new VerticalLayout();
        var bottom = new HorizontalLayout();
        var img = new Image(get("image"), "profile picture");
        img.addClickListener(e -> UI.getCurrent().getPage().open(get("permanentUrl"),
                "_blank"));
        CursorUtil.set(img, CursorUtil.Cursor.POINTER);
        img.setHeight("100%");
        layout.add(img, right);
        right.add(
                label("Real Name", get("name")),
                label("Account Created", toDate(get("created"))),
                label("Status", get("status")),
                label("Visibility", get("visibility")),
                label("Games banned", get("gamesBan")),
                bottom);
        bottom.add(divIcon(get("vacBan"), "VAC"),
                divIcon(get("tradeBan"), "Trade"),
                divIcon(get("communityBan"), "Community"));
        layout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        MarginUtil.set(layout, MarginUtil.Margin.X, "auto");
        return layout;
    }

    private Component divIcon(String key, String s) {
        var div = new HorizontalLayout();
        var icon = new Icon("lumo", key.equals("true") ? "cross" : "checkmark");
        div.add(icon);
        var label = new Label(s);
        div.add(label);

        MarginUtil.set(icon, MarginUtil.Margin.Y, "0");
        MarginUtil.set(icon, MarginUtil.Margin.RIGHT, "0");
        MarginUtil.set(label, MarginUtil.Margin.ALL, "0");
        MarginUtil.set(div, MarginUtil.Margin.Y, "0");


        return div;
    }

    private Component label(String label1, String label2) {
        var div = new Div();
        var key = new Label(label1 + ": ");
        key.getStyle().set("font-weight", "500");
        var value = new Label(label2);
        div.add(key, value);
        MarginUtil.set(div, MarginUtil.Margin.Y, "0");
        return div;
    }

    private String toDate(String time) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(new Date(Long.parseLong(time) * 1000L));
    }

    private Component main() {
        var layout = new VerticalLayout();
        layout.add(data("Steam ID", get("steamId")),
                data("Steam ID3", get("steamId3")),
                data("Steam32 ID", get("steamId32")),
                data("Steam64 ID", get("steamId64")),
                data("Nick name", get("nick")),
                data("Profile URL", get("profileUrl")),
                data("Profile permanent link", get("permanentUrl")));
        return layout;
    }

    private Component data(String id, String key) {
        var layout = new VerticalLayout();
        var field = new TextField();
        field.setValue(key);
        field.setReadOnly(true);
        field.setWidthFull();
        var label = new Label(id);
        MarginUtil.set(layout, MarginUtil.Margin.Y, "0");
        layout.getStyle().set("padding-bottom", "0");
        MarginUtil.set(label, MarginUtil.Margin.Y, "0");
        MarginUtil.set(field, MarginUtil.Margin.Y, "0");
        layout.add(label, field);
        return layout;
    }
}
