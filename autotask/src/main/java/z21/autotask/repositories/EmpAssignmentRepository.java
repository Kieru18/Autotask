package z21.autotask.repositories;


import z21.autotask.entities.EmpAssignment;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional @Repository
public interface EmpAssignmentRepository extends JpaRepository<EmpAssignment, Integer> {
    @Query
    List<EmpAssignment> findByTaskTaskId(Integer taskId);

    @Query
    List<EmpAssignment> findByEmployeeEmployeeId(Integer employeeId);
    
    @Modifying @Query(value = "INSERT INTO emp_assignments (employee_id, task_id) VALUES (:employee_id, :task_id)", nativeQuery = true)
    void assignEmployeeToTask(@Param("employee_id") int employee_id, @Param("task_id") int task_id);
}