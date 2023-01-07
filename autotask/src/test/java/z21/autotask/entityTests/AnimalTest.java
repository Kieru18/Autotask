package z21.autotask.entityTests;

import java.util.Date;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import z21.autotask.entities.Animal;
import z21.autotask.entities.Location;
import z21.autotask.entities.Species;

class AnimalTest {
    @Test
    void testAnimalConstructor() {
        Animal animal = new Animal(1, "Fluffy", new Location(), new Species(), new Date(), 10.5f);
        
        assertEquals(1, animal.getAnimalId());
        assertEquals("Fluffy", animal.getName());
        assertNotNull(animal.getLocation());
        assertNotNull(animal.getSpecies());
        assertNotNull(animal.getBirthDate());
        assertEquals(10.5f, animal.getWeight());
    }

    @Test
    void testAnimalSetters() {
        Animal animal = new Animal();
        
        animal.setAnimalId(1);
        animal.setName("Fluffy");
        animal.setLocation(new Location());
        animal.setSpecies(new Species());
        animal.setBirthDate(new Date());
        animal.setWeight(10.5f);
        
        assertEquals(1, animal.getAnimalId());
        assertEquals("Fluffy", animal.getName());
        assertNotNull(animal.getLocation());
        assertNotNull(animal.getSpecies());
        assertNotNull(animal.getBirthDate());
        assertEquals(10.5f, animal.getWeight());
    }

    @Test
    void testAnimalGetters() {
        Animal animal = new Animal(1, "Fluffy", new Location(), new Species(), new Date(), 10.5f);
        
        assertEquals(1, animal.getAnimalId());
        assertEquals("Fluffy", animal.getName());
        assertNotNull(animal.getLocation());
        assertNotNull(animal.getSpecies());
        assertNotNull(animal.getBirthDate());
        assertEquals(10.5f, animal.getWeight());
    }

    @Test
    void testAnimalToString() {
        Animal animal = new Animal(1, "Fluffy", new Location(), new Species(), new Date(), 10.5f);
        
        assertEquals("Animal [animalId=1, name=Fluffy, location=null, species=null, birthDate=null, weight=10.5]", animal.toString());
    }
    
}


