package dm.dev.ekartest.controller;

import dm.dev.ekartest.dto.ChangeThreadsDTO;
import dm.dev.ekartest.dto.SetThreadsDTO;
import dm.dev.ekartest.service.ThreadManagerService;
import dm.dev.ekartest.thread.SystemCounter;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ThreadsOperationController {
    private final ThreadManagerService threadManagerService;
    private final SystemCounter systemCounter;

    @PostMapping("addThreads")
    public ResponseEntity<String> addThreads(@Valid @RequestBody ChangeThreadsDTO changeThreadsDto) {
        threadManagerService.addThreads(changeThreadsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

    @PostMapping("changeCounter")
    public ResponseEntity changeCounter(@Valid @RequestBody SetThreadsDTO setThreadsDto) {
        systemCounter.setNewValue(setThreadsDto.getNewValue());
        return ResponseEntity.ok().build();
    }
}
