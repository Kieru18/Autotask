package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import z21.autotask.entities.Species;

public class SpeciesTest {
    @Test
    void testSpeciesConstructor() {
        Species species = new Species(1, "dog", "carnivore", 123456);

        assertEquals(1, species.getSpeciesId());
        assertEquals("dog", species.getName());
        assertEquals("carnivore", species.getFoodType());
        assertEquals(123456, species.getAverageLifespan());
    }

    @Test
    void testSpeciesConstructorEmptyValues() {
        Species species = new Species(0, "", "", 0);

        assertEquals(0, species.getSpeciesId());
        assertEquals("", species.getName());
        assertEquals("", species.getFoodType());
        assertEquals(0, species.getAverageLifespan());
    }

    @Test
    void testSpeciesNoArgConstructor() {
        Species species = new Species();

        assertNull(species.getSpeciesId());
        assertNull(species.getName());
        assertNull(species.getFoodType());
        assertNull(species.getAverageLifespan());
    }

    @Test
    void testSpeciesSetters() {
        Species species = new Species();

        species.setSpeciesId(1);
        species.setName("dog");
        species.setFoodType("carnivore");
        species.setAverageLifespan(123456);

        assertEquals(1, species.getSpeciesId());
        assertEquals("dog", species.getName());
        assertEquals("carnivore", species.getFoodType());
        assertEquals(123456, species.getAverageLifespan());
    }

    @Test
    void testSpeciesGetters() {
        Species species = new Species(1, "dog", "carnivore", 123456);

        assertEquals(1, species.getSpeciesId());
        assertEquals("dog", species.getName());
        assertEquals("carnivore", species.getFoodType());
        assertEquals(123456, species.getAverageLifespan());
    }

    @Test
    void testSpeciesToString() {
        Species species = new Species(1, "dog", "carnivore", 123456);

        assertEquals("Species(speciesId=1, name=dog, foodType=carnivore, averageLifespan=123456)", species.toString());
    }

    @Test
    void testSpeciesEquals() {
        Species species1 = new Species(1, "dog", "carnivore", 123456);
        Species species2 = new Species(1, "dog", "carnivore", 123456);
        Species species3 = new Species(2, "cat", "carnivore", 12334);

        assertTrue(species1.equals(species2));
        assertFalse(species1.equals(species3));
    }

    @Test
    void testSpeciesHashCode() {
        Species species1 = new Species(1, "dog", "carnivore", 123456);
        Species species2 = new Species(1, "dog", "carnivore", 123456);
        Species species3 = new Species(2, "cat", "carnivore", 12334);

        assertEquals(species1.hashCode(), species2.hashCode());
        assertNotEquals(species1.hashCode(), species3.hashCode());
    }
    
}
