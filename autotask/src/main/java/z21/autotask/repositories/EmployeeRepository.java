package z21.autotask.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.orm.Employee;


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


    @Modifying @Query(value = "INSERT INTO employees (first_name,  last_name, gender, birth_date) VALUES (:firstName, :lastName, :gender, :birthDate )", nativeQuery = true)
    void insertEmployee(@Param("first_name") String firstName, @Param("last_name")  String lastName, @Param("gender") char gender,
                       @Param("birth_date") Date birthDate);

}