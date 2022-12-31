package z21.autotask.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.orm.Position;

@Transactional @Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query
    List<Position> findByPositionId(Integer positionId);

    @Query
    List<Position> findByName(String name);

    @Query
    List<Position> findByPhoto(byte[] photo);

    @Modifying @Query(value = "INSERT INTO positions (name,  photo) VALUES (:name, :photo )", nativeQuery = true)
    void insertPosition(@Param("name") String name, @Param("photo") byte[] photo);

}