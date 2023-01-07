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
        Animal animal = new Animal(1, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        
        assertEquals(1, animal.getAnimalId());
        assertEquals("Grześ", animal.getName());
        assertNotNull(animal.getLocation());
        assertNotNull(animal.getSpecies());
        assertNotNull(animal.getBirthDate());
        assertEquals(21.37f, animal.getWeight());
    }

    @Test
    void testAnimalConstructorEmptyValues() {
        Animal animal = new Animal(0, "", null, null, null, 0.0f);
        
        assertEquals(0, animal.getAnimalId());
        assertEquals("", animal.getName());
        assertNull(animal.getLocation());
        assertNull(animal.getSpecies());
        assertNull(animal.getBirthDate());
        assertEquals(0.0f, animal.getWeight());
    }

    @Test
    void testAnimalNoArgConstructor() {
        Animal animal = new Animal();
        
        assertNull(animal.getAnimalId());
        assertNull(animal.getName());
        assertNull(animal.getLocation());
        assertNull(animal.getSpecies());
        assertNull(animal.getBirthDate());
        assertNull(animal.getWeight());
    }

    @Test
    void testAnimalSetters() {
        Animal animal = new Animal();
        
        animal.setAnimalId(1);
        animal.setName("Grześ");
        animal.setLocation(new Location());
        animal.setSpecies(new Species());
        animal.setBirthDate(new Date());
        animal.setWeight(21.37f);
        
        assertEquals(1, animal.getAnimalId());
        assertEquals("Grześ", animal.getName());
        assertNotNull(animal.getLocation());
        assertNotNull(animal.getSpecies());
        assertNotNull(animal.getBirthDate());
        assertEquals(21.37f, animal.getWeight());
    }

    @Test
    void testAnimalGetters() {
        Animal animal = new Animal(1, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        
        assertEquals(1, animal.getAnimalId());
        assertEquals("Grześ", animal.getName());
        assertNotNull(animal.getLocation());
        assertNotNull(animal.getSpecies());
        assertNotNull(animal.getBirthDate());
        assertEquals(21.37f, animal.getWeight());
    }

    @Test
    void testAnimalToString() {
        Animal animal = new Animal(1, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        
        assertEquals("Animal(animalId=1, name=Grześ, location=null, species=null, birthDate=null, weight=21.37)", animal.toString());
    }

    @Test
    void testAnimalEquals() {
        Animal animal1 = new Animal(1, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        Animal animal2 = new Animal(1, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        Animal animal3 = new Animal(2, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        
        assertTrue(animal1.equals(animal2));
        assertFalse(animal1.equals(animal3));
    }

    @Test
    void testAnimalHashCode() {
        Animal animal1 = new Animal(1, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        Animal animal2 = new Animal(1, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        Animal animal3 = new Animal(2, "Grześ", new Location(), new Species(), new Date(), 21.37f);
        
        assertEquals(animal1.hashCode(), animal2.hashCode());
        assertNotEquals(animal1.hashCode(), animal3.hashCode());
    }

    
}


