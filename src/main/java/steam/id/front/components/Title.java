package steam.id.front.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import steam.id.front.utils.CursorUtil;
import steam.id.front.views.Index;

@UIScope
@SpringComponent
public class Title extends H3 {
    public Title(Search search) {
        setText("Get Steam ID");
        getStyle()
                .set("text-align", "center");
        CursorUtil.set(this, CursorUtil.Cursor.POINTER);

        addClickListener(e -> {
            search.getField().clear();
            UI.getCurrent().navigate(Index.class);
        });
    }
}
