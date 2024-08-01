package messagetest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import messagetest.domain.*;
import messagetest.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ItemCodeCreated extends AbstractEvent {

    private Long id;
    private String itemCode;
    private String codeNo;
    private String code;
    private String codeName;
    private String isSys;
    private String isUse;
    private String etc;
    private String etc1;
    private String etc2;
    private String etc3;
    private String etc4;
    private String etc5;

    public ItemCodeCreated(ItemCode aggregate) {
        super(aggregate);
    }

    public ItemCodeCreated() {
        super();
    }
}
//>>> DDD / Domain Event
