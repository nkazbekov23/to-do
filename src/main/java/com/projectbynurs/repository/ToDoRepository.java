package com.projectbynurs.repository;

import com.projectbynurs.entity.ToDo;
import com.projectbynurs.reprmodel.ToDoRe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Integer> {

    @Query("select new com.projectbynurs.reprmodel.ToDoRe(t) from ToDo t " +
            "where t.user.id = :userId")
    List<ToDoRe> findToDosByUserId(@Param("userId") Integer userId);
}
