package z21.autotask;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    // List<Animal> findByName(String name);
  
    // Animal findById(int id);

    // @Query("SELECT color FROM animals WHERE color = :color")
    // Stream<Animal> findByColor(@Param("color") String color);
  }
