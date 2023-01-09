package z21.autotask.repositories;


import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.TaskType;

@Transactional @Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
    @Query
    List<TaskType> findByTypeId(Integer typeId);

    @Query
    List<TaskType> findByName(String name);

    @Query
    List<TaskType> findByDescription(String description);

    @Query
    List<TaskType> findByBasePriority(Integer basePriority);

    @Query
    List<TaskType> findByFrequency(SimpleDateFormat frequency);


    @Modifying @Query(value = "INSERT INTO task_types (name,  description, base_priority, frequency) VALUES (:name, :description, :base_priority, :frequency)", nativeQuery = true)
    void insertTaskType(@Param("name") String name, 
                        @Param("description") String description,
                        @Param("base_priority") Integer basePriority,
                        @Param("frequency") SimpleDateFormat frequency);

}