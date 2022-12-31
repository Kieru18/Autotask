package z21.autotask.repositories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.TaskStatus;


@Transactional @Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
    @Query
    List<TaskStatus> findByStatusId(Integer statusId);

    @Query
    List<TaskStatus> findByDescription(String description);

    @Modifying @Query(value = "INSERT INTO task_status (description) VALUES (:description)", nativeQuery = true)
    void insertTaskStatus(@Param("description") String description);

}