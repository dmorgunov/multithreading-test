package dm.dev.ekartest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class ChangeThreadsDTO {

    @Min(value = 0, message = "Min producersCount is 0")
    @Max(value = 100, message = "Max producersCount is 100")
    private int producersCount;

    @Min(value = 0, message = "Min consumersCount is 0")
    @Max(value = 100, message = "Max consumersCount is 100")
    private int consumersCount;

}
