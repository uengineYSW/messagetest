package messagetest.domain;

import messagetest.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "itemCodes", path = "itemCodes")
public interface ItemCodeRepository
    extends PagingAndSortingRepository<ItemCode, Long> {}
