package z21.autotask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional @Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query
    List<Animal> findByAnimalId(Integer animalId);

    @Query
    List<Animal> findByName(String name);

    @Query
    List<Animal> findByLegCount(Integer legCount);

    @Query
    List<Animal> findByColor(String color);

    @Query(value = "SELECT * FROM animals WHERE leg_count = ?1 AND color = ?2", nativeQuery = true)
    List<Animal> findLegsColor(Integer legCount, String color);

    @Modifying @Query(value = "INSERT INTO animals (name,  color, leg_count) VALUES (:name, :color, :leg_count )", nativeQuery = true)
    void insertAnimal(@Param("name") String name, @Param("color")  String color, @Param("leg_count") Integer legCount);
}