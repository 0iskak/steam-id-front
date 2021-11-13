package steam.id.front.views;

import com.vaadin.flow.router.Route;
import steam.id.front.MainLayout;
import steam.id.front.components.Components;

@Route("")
public class Index extends MainLayout {

    public Index(Components components) {
        add(components.getTitle(), components.getSearch(), components.getInfo());
    }
}
