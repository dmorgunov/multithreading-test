package dm.dev.ekartest.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "finish_threads_execution_event")
public class FinishThreadsExecutionEvent extends BaseEvent {

}
