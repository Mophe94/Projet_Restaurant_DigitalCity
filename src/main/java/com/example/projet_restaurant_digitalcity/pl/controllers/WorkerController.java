package com.example.projet_restaurant_digitalcity.pl.controllers;

import com.example.projet_restaurant_digitalcity.bl.services.WorkerService;
import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import com.example.projet_restaurant_digitalcity.pl.models.dto.WorkerDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

//    @GetMapping(path = {"","/all"})
//    public ResponseEntity<Page<Worker>> getAll(@RequestParam int page, int countByPage){
//        Page<WorkerDTO>  workers = workerService.getAll(page, countByPage);
//
//        return
//    }

}
