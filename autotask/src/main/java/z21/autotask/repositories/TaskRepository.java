package z21.autotask.repositories;


import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.Animal;
import z21.autotask.entities.Employee;
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

    @Query
    List<Task> findByLocationLocationId(Integer locationId);

    // @Query
    // List<Task> findByTaskTypeTypeId(Integer typeId);

    // @Query
    // List<Task> findByStatusId(Integer statusId);
    //employee_id, e.first_name, e.last_name, e.gender, e.birth_date, e.position_id, e.status_id, e.user_id

    // @Query(value = "SELECT e.* FROM tasks t LEFT JOIN emp_assignments emp ON(t.task_id = emp.task_id) LEFT JOIN employees e ON (e.employee_id = emp.employee_id) WHERE t.task_id = :task_id", nativeQuery = true)
    // List<Employee> findEmployees(@Param("task_id") Integer taskId);

    // @Query(value = "SELECT a.* FROM tasks t LEFT JOIN animal_assignments ani ON(t.task_id = ani.task_id) LEFT JOIN animals a ON (a.animal_id = ani.animal_id) WHERE t.task_id = :task_id", nativeQuery = true)
    // List<Animal> findAnimals(@Param("task_id") Integer taskId);

    @Query(value = "SELECT t.* FROM employees e LEFT JOIN emp_assignments emp ON(e.employee_id = emp.employee_id) LEFT JOIN tasks t ON(emp.task_id = t.task_id) WHERE e.employee_id = :employee_id", nativeQuery = true)
    List<Task> findByEmployeeId(@Param("employee_id") Integer employeeId);

    @Query(value = "SELECT tasks_seq.CURRVAL FROM dual", nativeQuery = true)
    Integer findLastTaskId();

    @Modifying @Query(value = "INSERT INTO tasks (description, date_start, date_end, deadline, priority, location_id, status_id, type_id) VALUES (:description, :date_start, :date_end, :deadline, :priority, :location_id, :status_id, :type_id)", nativeQuery = true)
    void insertTask(@Param("description") String description, 
                    @Param("date_start") Date dateStart, 
                    @Param("date_end") Date dateEnd,
                    @Param("deadline") Date deadline, 
                    @Param("priority") Integer priority,
                    @Param("location_id") Integer locationId,
                    @Param("status_id") Integer statusId,
                    @Param("type_id") Integer typeId);

}