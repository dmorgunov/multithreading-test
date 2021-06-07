package dm.dev.ekartest.repository;

import dm.dev.ekartest.model.BaseEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadEventRepository extends CrudRepository<BaseEvent, Long> {

}
