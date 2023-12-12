package com.example.projet_restaurant_digitalcity.bl.services.impl;

import com.example.projet_restaurant_digitalcity.bl.services.WorkerService;
import com.example.projet_restaurant_digitalcity.bl.services.exception.NameAlreadyUseException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.ResourceNotFoundException;
import com.example.projet_restaurant_digitalcity.bl.services.exception.UsernameNotFoundException;
import com.example.projet_restaurant_digitalcity.dal.repositories.RoleRepository;
import com.example.projet_restaurant_digitalcity.dal.repositories.WorkerRepository;
import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService,UserDetailsService {

    private final WorkerRepository workerRepository;
    private final RoleRepository roleRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository, RoleRepository roleRepository) {
        this.workerRepository = workerRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return workerRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(Worker.class,username));
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
    public Worker create(Worker tocreate, long idRoles) {
        if (workerRepository.existsByUsername(tocreate.getUsername()))
            throw new NameAlreadyUseException(Worker.class,tocreate.getName());
        tocreate.setRoles(roleRepository.findById(idRoles).stream().toList());
        return workerRepository.save(tocreate);
    }

    @Override
    public void delete(long idWorker) {
        workerRepository.deleteById(idWorker);
    }

    @Override
    public Worker update(long idWorker, Worker toUpdate) {
        if (!workerRepository.existsById(idWorker))
            throw new ResourceNotFoundException(Worker.class,idWorker);

        toUpdate.setId(idWorker);
        return workerRepository.save(toUpdate);
    }
}
