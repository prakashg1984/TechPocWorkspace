package com.pg.spring.reactive.service;

import com.pg.spring.reactive.dto.UserDepartmentDTO;
import com.pg.spring.reactive.dto.UserDepartmentDTONew;
import com.pg.spring.reactive.model.Department;
import com.pg.spring.reactive.model.User;
import com.pg.spring.reactive.repository.DepartmentRepository;
import com.pg.spring.reactive.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.function.BiFunction;

@Service
@Slf4j
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<User> findById(Integer userId){
        return userRepository.findById(userId);
    }

    public Mono<User> updateUser(Integer userId,  User user){
        log.info("Updating User : {} ", user);

        return userRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setAge(user.getAge());
                    dbUser.setSalary(user.getSalary());
                    return userRepository.save(dbUser);
                });
    }

    public Mono<User> deleteUser(Integer userId){
        return userRepository.findById(userId)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                .then(Mono.just(existingUser)));
    }

    public Flux<User> findUsersByAge(int age){
        return userRepository.findByAge(age);
    }

    public Flux<User> fetchUsers(List<Integer> userIds) {
        return Flux.fromIterable(userIds)
                .parallel()
                .runOn(Schedulers.parallel())
                .flatMap(i -> findById(i))
                .ordered((u1, u2) -> u2.getId() - u1.getId());
    }

    private Mono<Department> getDepartmentByUserId(Integer userId){
    	UserDepartmentDTONew userDepartmentDTO = UserDepartmentDTONew.newInstance();
        return departmentRepository.findByUserId(userId);
    }

    public Mono<UserDepartmentDTO> fetchUserAndDepartment(Integer userId){
        Mono<User> user = findById(userId).subscribeOn(Schedulers.parallel());
        Mono<Department> department = getDepartmentByUserId(userId).subscribeOn(Schedulers.parallel());
        return Mono.zip(user, department, userDepartmentDTOBiFunction);
    }

    private BiFunction<User, Department, UserDepartmentDTO> userDepartmentDTOBiFunction = (x1, x2) -> UserDepartmentDTO.builder()
            .age(x1.getAge())
            .departmentId(x2.getId())
            .departmentName(x2.getName())
            .userName(x1.getName())
            .userId(x1.getId())
            .loc(x2.getLoc())
            .salary(x1.getSalary()).build();

}
