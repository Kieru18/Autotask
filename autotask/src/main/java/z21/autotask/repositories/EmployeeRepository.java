package z21.autotask.repositories;


import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.Employee;


@Transactional @Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query
    List<Employee> findByEmployeeId(Integer employeeId);

    @Query
    List<Employee> findByFirstName(String firstName);

    @Query
    List<Employee> findByLastName(Integer lastName);

    @Query
    List<Employee> findByGender(char gender);

    @Query
    List<Employee> findByBirthDate(Date birthDate);

    @Query(value = "SELECT employee_id, first_name, last_name, gender, birth_date, position_id, status_id, user_id FROM employees JOIN emp_status USING(status_id) WHERE description = :status", nativeQuery = true)
    List<Employee> findByStatus(@Param("status") String status);

    @Query
    List<Employee> findByStatusStatusId(Integer statusId);

    @Query
    List<Employee> findByPositionPositionId(Integer positionId);

    @Query(value = "SELECT e.* FROM employees e JOIN users u ON (e.user_id = u.user_id) WHERE u.user_id = :user_id", nativeQuery = true)
    List<Employee> findByUserId(@Param("user_id") Integer userId);

    @Query(value = "SELECT employees_seq.CURRVAL FROM dual", nativeQuery = true)
    Integer findLastEmployeeId();

    @Query(value = "SELECT e.* FROM tasks t JOIN emp_assignments emp ON(t.task_id = emp.task_id) JOIN employees e ON (e.employee_id = emp.employee_id) WHERE t.task_id = :task_id", nativeQuery = true)
    List<Employee> findByTaskId(@Param("task_id") Integer taskId);

    @Modifying @Query(value = "INSERT INTO employees (first_name, last_name, gender, birth_date, position_id, status_id, user_id) VALUES (:first_name, :last_name, :gender, :birth_date, :position_id, :status_id, :user_id)", nativeQuery = true)
    void insertEmployee(@Param("first_name") String firstName, 
                        @Param("last_name") String lastName, 
                        @Param("gender") char gender,
                        @Param("birth_date") Date birthDate,
                        @Param("position_id") Integer positionId,
                        @Param("status_id") Integer statusId,
                        @Param("user_id") Integer userId);
    
    @Modifying @Query(value = "UPDATE employees SET user_id = :user_id WHERE employee_id = :employee_id", nativeQuery = true)
    void updateUser(@Param("user_id") Integer userId, 
                    @Param("employee_id") Integer employeeId);

}