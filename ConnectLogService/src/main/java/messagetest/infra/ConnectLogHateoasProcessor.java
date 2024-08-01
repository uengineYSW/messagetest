package messagetest.infra;

import messagetest.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ConnectLogHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ConnectLog>> {

    @Override
    public EntityModel<ConnectLog> process(EntityModel<ConnectLog> model) {
        return model;
    }
}
