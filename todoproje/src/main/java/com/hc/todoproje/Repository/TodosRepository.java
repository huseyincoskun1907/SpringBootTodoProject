package com.hc.todoproje.Repository;

import org.springframework.data.repository.CrudRepository;

import com.hc.todoproje.entity.Todos;

import java.util.List;

public interface TodosRepository extends CrudRepository<Todos, Long> {

    Todos findByName(String name);

    List<Todos> findByCompletedAndUserId(boolean complated, Long Id);

}