package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.WorkerService;
import com.example.projet_restaurant_digitalcity.dal.repositories.WorkerRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService,UserDetailsService {

    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return workerRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }

    @Override
    public Worker findByName(String name) {
        return workerRepository.findByName(name)
                .orElseThrow();
    }

    @Override
    public Page<Worker> getAll(int page, int countByPage) {
        return workerRepository.findAll(PageRequest.of(page, countByPage));
    }

    @Override
    public Worker getOneById(long idWorker) {
        return workerRepository.findById(idWorker).orElseThrow();
    }

    @Override
    public Worker create(Worker tocreate) {
        if (workerRepository.existsByUsername(tocreate.getUsername()))
            throw new RuntimeException("this username is already take");
        return workerRepository.save(tocreate);
    }

    @Override
    public void delete(long idWorker) {
        workerRepository.deleteById(idWorker);
    }

    @Override
    public Worker update(long idWorker, Worker toUpdate) {
        if (!workerRepository.existsById(idWorker))
            throw new RuntimeException("no Worker found with this id");

        toUpdate.setId(idWorker);
        return workerRepository.save(toUpdate);
    }
}
