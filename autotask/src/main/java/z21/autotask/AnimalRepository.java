package z21.autotask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query
    List<Animal> findByName(String name);

    @Query(value = "SELECT * FROM animals WHERE leg_count = ?1 AND color = ?2", nativeQuery = true)
    List<Animal> findLegsColor(Integer leg_count, String color);
  }
