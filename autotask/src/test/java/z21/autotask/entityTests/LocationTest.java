package z21.autotask.entityTests;

import java.util.Date;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import z21.autotask.entities.Animal;
import z21.autotask.entities.Location;
import z21.autotask.entities.Species;

public class LocationTest {
    @Test
    void testLocationConstructor() {
        Location location = new Location(1, "Cool place", 4.2f, 1.33f);
        
        assertEquals(1, location.getLocationId());
        assertEquals("Cool place", location.getName());
        assertEquals(4.2f, location.getLatitude());
        assertEquals(1.33f, location.getLongitude());
    }

    @Test
    void testLocationConstructorEmptyValues() {
        Location location = new Location(0, "", 0.0f, 0.0f);
        
        assertEquals(0, location.getLocationId());
        assertEquals("", location.getName());
        assertEquals(0.0f, location.getLatitude());
        assertEquals(0.0f, location.getLongitude());
    }

    @Test
    void testLocationNoArgConstructor() {
        Location location = new Location();
        
        assertNull(location.getLocationId());
        assertNull(location.getName());
        assertNull(location.getLatitude());
        assertNull(location.getLongitude());
    }

    @Test
    void testLocationSetters() {
        Location location = new Location();
        location.setLocationId(1);
        location.setName("Cool place");
        location.setLatitude(4.2f);
        location.setLongitude(1.33f);
        
        assertEquals(1, location.getLocationId());
        assertEquals("Cool place", location.getName());
        assertEquals(4.2f, location.getLatitude());
        assertEquals(1.33f, location.getLongitude());
    }

    @Test
    void testLocationsGetters() {
        Location location = new Location(1, "Cool place", 4.2f, 1.33f);
        
        assertEquals(1, location.getLocationId());
        assertEquals("Cool place", location.getName());
        assertEquals(4.2f, location.getLatitude());
        assertEquals(1.33f, location.getLongitude());
    }

    @Test
    void testLocationToString() {
        Location location = new Location(1, "Cool place", 4.2f, 1.33f);
        
        assertEquals("Location(locationId=1, name=Cool place, latitude=4.2, longitude=1.33)", location.toString());
    }

    @Test
    void testLocationEquals() {
        Location location1 = new Location(1, "Cool place", 4.2f, 1.33f);
        Location location2 = new Location(1, "Cool place", 4.2f, 1.33f);
        Location location3 = new Location(1, "Cool place", 4.2f, 2.01f);
        
        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
    }

    @Test
    void testLocationHashCode() {
        Location location1 = new Location(1, "Cool place", 4.2f, 1.33f);
        Location location2 = new Location(1, "Cool place", 4.2f, 1.33f);
        Location location3 = new Location(1, "Cool place", 4.2f, 2.01f);
        
        assertEquals(location1.hashCode(), location2.hashCode());
        assertNotEquals(location1.hashCode(), location3.hashCode());
    }

}
