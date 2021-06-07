package dm.dev.ekartest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class SetThreadsDTO {
    @Min(value = 0, message = "Min newValue is 0")
    @Max(value = 100, message = "Max newValue is 100")
    private int newValue;

}
