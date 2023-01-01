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
import z21.autotask.entities.Location;
import z21.autotask.entities.Species;

public interface SpeciesRepository extends JpaRepository<Species, Integer>  {
    @Query
    List<Animal> findBySpeciesId(Integer speciesId);

    @Query
    List<Animal> findByFoodType(String foodType);

    @Query
    List<Animal> findByAverageLifespan(Integer averageLifespan);


    @Modifying @Query(value = "INSERT INTO species (name, food_type, average_lifespan) VALUES (:name, :food_type, :average_lifespan)", nativeQuery = true)
    void insertSpecies(@Param("name") String name, 
                       @Param("food_type") String foodType, 
                       @Param("average_lifespan") Integer averageLifespan);

}