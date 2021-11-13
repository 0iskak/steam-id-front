package steam.id.front;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import steam.id.front.utils.MarginUtil;

public class MainLayout extends VerticalLayout {
    public MainLayout() {
        setMaxWidth("50vw");
        setMinWidth("700px");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        MarginUtil.set(this, MarginUtil.Margin.X, "auto");
    }
}
