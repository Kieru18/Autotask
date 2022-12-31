package z21.autotask.repositories;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.orm.Animal;
import z21.autotask.orm.Location;
import z21.autotask.orm.Species;

@Transactional @Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query
    List<Animal> findByLocationId(Integer locationId);

    @Query
    List<Animal> findByName(String name);

    @Query
    List<Animal> findByLatitude(Float latitude);

    @Query
    List<Animal> findByLongitude (Float longitude);


    @Modifying @Query(value = "INSERT INTO locations (name, latitude, longitude) VALUES (:name, :latitude, :longitude)", nativeQuery = true)
    void insertLocation(@Param("name") String name, 
                        @Param("latitude") Float latitude, 
                        @Param("longitude") Float longitude);

}