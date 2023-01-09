package z21.autotask.repositories;

import z21.autotask.entities.AnimalAssignment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional @Repository
public interface AnimalAssignmentRepository extends JpaRepository<AnimalAssignment, Integer>{
    
    @Query(value = "SELECT * FROM animal_assignments WHERE task_id = :task_id", nativeQuery = true)
    List<AnimalAssignment> findByTask(@Param("task_id") Integer taskId);

    @Query(value = "SELECT * FROM animal_assignments WHERE animal_id = :animal_id", nativeQuery = true)
    List<AnimalAssignment> findByAnimal(@Param("animal_id") Integer animalId);
    
    @Modifying @Query(value = "INSERT INTO animal_assignments (animal_id, task_id) VALUES (:animal_id, :task_id)", nativeQuery = true)
    void assignAnimalToTask(@Param("animal_id") int animal_id, @Param("task_id") int task_id);
}
