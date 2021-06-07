package dm.dev.ekartest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "change_thread_event")
public class ChangeBaseEvent extends BaseEvent {
    @Column
    private Integer consumers;

    @Column
    private Integer producers;

}
