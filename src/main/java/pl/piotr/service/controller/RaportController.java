package pl.piotr.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piotr.service.dto.raport.GetTasksRaportResponse;
import pl.piotr.service.service.TaskToRaportService;

import java.util.logging.Logger;

@RestController
@RequestMapping("raport")
public class RaportController {

    private Logger LOG = Logger.getLogger(getClass().getName());
    private TaskToRaportService service;

    @Autowired
    public RaportController(TaskToRaportService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<GetTasksRaportResponse> getUsers() {
        LOG.info("Preparing raport");
        return ResponseEntity.ok(
                GetTasksRaportResponse.entityToDtoMapper().apply(service.findAll()));
    }

}
