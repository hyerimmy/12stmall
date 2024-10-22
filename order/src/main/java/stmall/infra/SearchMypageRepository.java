package stmall.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stmall.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "searchMypages",
    path = "searchMypages"
)
public interface SearchMypageRepository
    extends PagingAndSortingRepository<SearchMypage, Long> {}
