package messagetest.domain;

import messagetest.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "connectLogs",
    path = "connectLogs"
)
public interface ConnectLogRepository
    extends PagingAndSortingRepository<ConnectLog, Long> {}
