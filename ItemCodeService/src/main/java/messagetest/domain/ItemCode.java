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

    // 비즈니스 로직 추가
    public static void createItemCode(CreateItemCodeCommand command) {
        ItemCode itemCode = new ItemCode();
        itemCode.setId(null); // 생성 시 ID는 자동 생성되므로 null로 설정합니다.
        itemCode.setItemCode(command.getItemCode());
        itemCode.setCodeNo(command.getCodeNo());
        itemCode.setCode(command.getCode());
        itemCode.setCodeName(command.getCodeName());
        itemCode.setIsSys(command.getIsSys());
        itemCode.setIsUse(command.getIsUse());
        itemCode.setEtc(command.getEtc());
        itemCode.setEtc1(command.getEtc1());
        itemCode.setEtc2(command.getEtc2());
        itemCode.setEtc3(command.getEtc3());
        itemCode.setEtc4(command.getEtc4());
        itemCode.setEtc5(command.getEtc5());

        repository().save(itemCode);
    }
}
