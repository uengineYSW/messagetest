package messagetest.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import messagetest.ConnectLogServiceApplication;
import messagetest.domain.ConnectLogCreated;

@Entity
@Table(name = "ConnectLog_table")
@Data
//<<< DDD / Aggregate Root
public class ConnectLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logNo;

    private Long loginDt;

    private String loginId;

    private String loginIdnm;

    private String accessIp;

    @PostPersist
    public void onPostPersist() {
        ConnectLogCreated connectLogCreated = new ConnectLogCreated(this);
        connectLogCreated.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static ConnectLogRepository repository() {
        ConnectLogRepository connectLogRepository = ConnectLogServiceApplication.applicationContext.getBean(
            ConnectLogRepository.class
        );
        return connectLogRepository;
    }
}
//>>> DDD / Aggregate Root
