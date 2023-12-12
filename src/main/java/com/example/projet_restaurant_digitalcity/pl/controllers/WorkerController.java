package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.WorkerService;
import com.example.projet_restaurant_digitalcity.mapper.WorkerMapper;
import com.example.projet_restaurant_digitalcity.pl.models.dto.WorkerDTO;
import com.example.projet_restaurant_digitalcity.pl.models.form.WorkerForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerMapper workerMapper;

    public WorkerController(WorkerService workerService, WorkerMapper workerMapper) {
        this.workerService = workerService;
        this.workerMapper = workerMapper;
    }

    @PostMapping()
    @PreAuthorize("permitAll()")
    public ResponseEntity<WorkerDTO> create(@RequestBody WorkerForm form,@RequestParam long role){


        workerService.create(workerMapper.toEntity(form),role);


        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

}
