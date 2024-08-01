package messagetest.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import messagetest.ItemCodeServiceApplication;
import messagetest.domain.ItemCodeCreated;

@Entity
@Table(name = "ItemCode_table")
@Data
//<<< DDD / Aggregate Root
public class ItemCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @PostPersist
    public void onPostPersist() {
        ItemCodeCreated itemCodeCreated = new ItemCodeCreated(this);
        itemCodeCreated.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static ItemCodeRepository repository() {
        ItemCodeRepository itemCodeRepository = ItemCodeServiceApplication.applicationContext.getBean(
            ItemCodeRepository.class
        );
        return itemCodeRepository;
    }
}
//>>> DDD / Aggregate Root
