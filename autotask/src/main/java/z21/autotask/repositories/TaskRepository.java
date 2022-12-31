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

import z21.autotask.entities.Task;


@Transactional @Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query
    List<Task> findByTaskId(Integer taskId);

    @Query
    List<Task> findByDescription(String description);

    @Query
    List<Task> findByDateStart(Date dateStart);

    @Query
    List<Task> findByDateEnd(Date dateEnd);

    @Query
    List<Task> findByDeadline(Date deadline);

    @Query
    List<Task> findByPriority(Integer priority);


    @Modifying @Query(value = "INSERT INTO tasks (description, date_start, date_end, deadline, priority ) VALUES (:description, :dateStart, :dateEnd, :deadline, :priority )", nativeQuery = true)
    void insertTask(@Param("description") String description, @Param("date_start")  Date dateStart, @Param("date_end") Date dateEnd,
                        @Param("deadline") Date deadline, @Param("priority") Integer priority);

}