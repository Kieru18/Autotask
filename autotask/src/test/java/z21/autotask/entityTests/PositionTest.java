package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import z21.autotask.entities.Position;

public class PositionTest {
    @Test
    void testPositionConstructor() {
        Position position = new Position(1, "cleaner", "clean.jpeg");
        
        assertEquals(1, position.getPositionId());
        assertEquals("cleaner", position.getName());
        assertEquals("clean.jpeg", position.getPhoto());
    }

    @Test
    void testPositionConstructorEmptyValues() {
        Position position = new Position(0, "", "");
        
        assertEquals(0, position.getPositionId());
        assertEquals("", position.getName());
        assertEquals("", position.getPhoto());
    }

    @Test
    void testPositionNoArgConstructor() {
        Position position = new Position();
        
        assertNull(position.getPositionId());
        assertNull(position.getName());
        assertNull(position.getPhoto());
    }

    @Test
    void testPositionSetters() {
        Position position = new Position();
        
        position.setPositionId(1);
        position.setName("cleaner");
        position.setPhoto("clean.jpeg");
        
        assertEquals(1, position.getPositionId());
        assertEquals("cleaner", position.getName());
        assertEquals("clean.jpeg", position.getPhoto());
    }

    @Test
    void testPositionGetters() {
        Position position = new Position(1, "cleaner", "clean.jpeg");
        
        assertEquals(1, position.getPositionId());
        assertEquals("cleaner", position.getName());
        assertEquals("clean.jpeg", position.getPhoto());
    }

    @Test
    void testPositionToString() {
        Position position = new Position(1, "cleaner", "clean.jpeg");
        
        assertEquals("Position(positionId=1, name=cleaner, photo=clean.jpeg)", position.toString());
    }

    @Test
    void testPositionEquals() {
        Position position1 = new Position(1, "cleaner", "clean.jpeg");
        Position position2 = new Position(1, "cleaner", "clean.jpeg");
        Position position3 = new Position(2, "seller", "sell.jpeg");
        
        assertTrue(position1.equals(position2));
        assertFalse(position1.equals(position3));
    }

    @Test
    void testPositionHashCode() {
        Position position1 = new Position(1, "cleaner", "clean.jpeg");
        Position position2 = new Position(1, "cleaner", "clean.jpeg");
        Position position3 = new Position(2, "seller", "sell.jpeg");
        
        assertEquals(position1.hashCode(), position2.hashCode());
        assertNotEquals(position1.hashCode(), position3.hashCode());
    }
    
}
