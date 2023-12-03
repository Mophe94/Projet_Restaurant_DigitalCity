package com.example.projet_restaurant_digitalcity.dal.repositories;
import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker,Long> {


   Optional<Worker> findByUsername(String username);

   Optional<Worker> findByName(String name);

   boolean existsByUsername(String username);
   boolean existsById(long idWorker);


}
