package messagetest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagetest.domain.*;
import messagetest.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConnectLogCreated extends AbstractEvent {

    private Long logNo;
    private Long loginDt;
    private String loginId;
    private String loginIdnm;
    private String accessIp;

    public ConnectLogCreated(ConnectLog aggregate) {
        super(aggregate);
    }

    public ConnectLogCreated() {
        super();
    }
}
//>>> DDD / Domain Event
