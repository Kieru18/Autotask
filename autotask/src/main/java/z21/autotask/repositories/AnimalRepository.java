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

@Transactional @Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query
    List<Animal> findByAnimalId(Integer animalId);

    @Query
    List<Animal> findByName(String name);

    // @Query
    // List<Animal> findByLocationId(Integer locationId);

    // @Query
    // List<Animal> findBySpeciesId(Integer speciesId);

    @Query
    List<Animal> findByWeight(Float weight);

    @Query
    List<Animal> findByBirthDate(Date birthDate);


    @Query(value = "SELECT * FROM animals WHERE weight = ?1 AND name = ?2", nativeQuery = true)
    List<Animal> findWeightName(Float weight, String name);

    @Modifying @Query(value = "INSERT INTO animals (name, location_id, species_id, weight, birth_date) VALUES (:name, :location_id, :species_id, :weight, :birth_date)", nativeQuery = true)
    void insertAnimal(@Param("name") String name, 
                      @Param("location_id") Integer locationId, 
                      @Param("species_id") Integer speciesId,
                      @Param("weight") Float weight, 
                      @Param("birth_date") Date birthDate);

}