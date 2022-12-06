package z21.autotask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query(value = "SELECT * FROM animals WHERE name = ?1", nativeQuery = true)
    List<Animal> findByName(String name);

    /* @Query(value = "SELECT name FROM animals WHERE name = ?1", nativeQuery = true)
    List<String> findName(String name); */
  }
