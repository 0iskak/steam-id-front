package steam.id.front.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import steam.id.front.utils.AudioUtil;

import java.io.IOException;


@Getter
@UIScope
@SpringComponent
public class Search extends HorizontalLayout {
    private final TextField field = new TextField();
    private final Button search = new Button(new Icon("lumo", "search"));

    private final Data data;

    public Search(Data data) {
        this.data = data;

        field.setHelperText("Steam64 ID / Custom URL / Vanity URL / Permanent Link");
        field.setPlaceholder("Fill the field");
        field.setWidthFull();

        add(field, search);

        search.addClickShortcut(Key.ENTER);
        search.addClickListener(this::clicked);
    }

    private void clicked(ClickEvent<Button> event) {
        try {
            click();
        } catch (IOException e) {
            Notification.show(e.getMessage(),
                            3000, Notification.Position.BOTTOM_START)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            AudioUtil.play(AudioUtil.Audio.ERROR);
        }
    }

    private void click() throws IOException {
        String value = field.getValue().trim();
        if (value.contains("steamcommunity.com")) {
            if (value.endsWith("/"))
                value = value.substring(0, value.length() - 1);
            value = value.substring(value.lastIndexOf('/') + 1).trim();
        }

        data.parse(value);
    }
}
