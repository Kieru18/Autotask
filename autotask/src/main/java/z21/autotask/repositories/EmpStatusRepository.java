package z21.autotask.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.EmpStatus;

@Transactional @Repository
public interface EmpStatusRepository extends JpaRepository<EmpStatus, Integer> {
    @Query
    List<EmpStatus> findByStatusId(Integer statusId);

    @Query
    List<EmpStatus> findByDescription(String description);

    @Modifying @Query(value = "INSERT INTO emp_status (description) VALUES (:description)", nativeQuery = true)
    void insertEmpStatus(@Param("description") String description);

}