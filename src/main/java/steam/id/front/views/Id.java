package steam.id.front.views;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import steam.id.front.MainLayout;
import steam.id.front.components.Components;

@Route(":id")
public class Id extends MainLayout implements AfterNavigationObserver {

    private final Components components;
    public Id(Components components) {
        this.components = components;
        add(components.getTitle(), components.getSearch(), components.getData());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        if (components.getData().getComponentCount() == 0) {
            String path = event.getLocation().getPath();
            components.getSearch().getField().setValue(path);
            components.getSearch().getSearch().click();
        }
    }
}
