package messagetest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateConnectLogCommand {

    private Long logNo;
    private Long loginDt;
    private String loginId;
    private String loginIdnm;
    private String accessIp;
}
