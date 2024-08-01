package messagetest.infra;

import messagetest.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ItemCodeHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ItemCode>> {

    @Override
    public EntityModel<ItemCode> process(EntityModel<ItemCode> model) {
        return model;
    }
}
