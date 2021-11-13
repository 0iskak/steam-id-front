package steam.id.front.components;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@UIScope
@SpringComponent
@RequiredArgsConstructor
public class Components {
    private final Title title;
    private final Search search;
    private final Info info;
    private final Data data;
}
