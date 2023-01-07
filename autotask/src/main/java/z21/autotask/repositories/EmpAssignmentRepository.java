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
public interface EmpAssignmentRepository extends JpaRepository<EmpAssignment, Integer>{
    @Query
    List<EmpAssignment> findByEmployeeId(Integer employeeId);

    @Query
    List<EmpAssignment> findByTaskId(Integer taskId);

    @Query
    List<EmpAssignment> findByEmployeeIdAndTaskId(Integer employeeId, Integer taskId);

    @Query
    List<EmpAssignment> findByEmployeeIdOrTaskId(Integer employeeId, Integer taskId);

    @Query
    List<EmpAssignment> findByEmployeeIdIn(List<Integer> employeeIds);

    @Query
    List<EmpAssignment> findByTaskIdIn(List<Integer> taskIds);

    @Modifying @Query
    void insertEmpAssignment(@Param("employee_id") Integer employeeId, @Param("task_id") Integer taskId);

}
