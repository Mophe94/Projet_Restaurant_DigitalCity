package com.example.projet_restaurant_digitalcity.bl.services;

import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import org.springframework.data.domain.Page;

public interface WorkerService {

 Worker findByName(String name);

 Page<Worker> getAll(int page,int countByPage);

 Worker getOneById(long idWorker);

 Worker create(Worker tocreate);

 void delete(long idWorker);

 Worker update(long idWorker, Worker toupdate);
}
