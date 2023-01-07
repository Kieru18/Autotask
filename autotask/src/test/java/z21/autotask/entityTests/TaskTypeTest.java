package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import z21.autotask.entities.TaskType;

public class TaskTypeTest {
    @Test
    void testTaskTypeConstructor() {
        TaskType taskType = new TaskType(1, "cleaning", "clean clean clean", 1, new Date());

        assertEquals(1, taskType.getTypeId());
        assertEquals("cleaning", taskType.getName());
        assertEquals("clean clean clean", taskType.getDescription());
        assertEquals(1, taskType.getBasePriority());
        assertNotNull(taskType.getFrequency());
    }

    @Test
    void testTaskTypeConstructorEmptyValues() {
        TaskType taskType = new TaskType(0, "", "", 0, null);

        assertEquals(0, taskType.getTypeId());
        assertEquals("", taskType.getName());
        assertEquals("", taskType.getDescription());
        assertEquals(0, taskType.getBasePriority());
        assertNull(taskType.getFrequency());
    }

    @Test
    void testTaskTypeNoArgConstructor() {
        TaskType taskType = new TaskType();

        assertNull(taskType.getTypeId());
        assertNull(taskType.getName());
        assertNull(taskType.getDescription());
        assertNull(taskType.getBasePriority());
        assertNull(taskType.getFrequency());
    }

    @Test
    void testTaskTypeSetters() {
        TaskType taskType = new TaskType();

        taskType.setTypeId(1);
        taskType.setName("cleaning");
        taskType.setDescription("clean clean clean");
        taskType.setBasePriority(1);
        taskType.setFrequency(new Date());

        assertEquals(1, taskType.getTypeId());
        assertEquals("cleaning", taskType.getName());
        assertEquals("clean clean clean", taskType.getDescription());
        assertEquals(1, taskType.getBasePriority());
        assertNotNull(taskType.getFrequency());
    }

    @Test
    void testTaskTypeGetters() {
        TaskType taskType = new TaskType(1, "cleaning", "clean clean clean", 1, new Date());

        assertEquals(1, taskType.getTypeId());
        assertEquals("cleaning", taskType.getName());
        assertEquals("clean clean clean", taskType.getDescription());
        assertEquals(1, taskType.getBasePriority());
        assertNotNull(taskType.getFrequency());
    }

    @Test
    void testTaskTypeEquals() {
        TaskType taskType1 = new TaskType(1, "cleaning", "clean clean clean", 1, new Date());
        TaskType taskType2 = new TaskType(1, "cleaning", "clean clean clean", 1, new Date());
        TaskType taskType3 = new TaskType(2, "dirtying", "not clean clean clean", 1, new Date());

        assertEquals(taskType1, taskType2);
        assertNotEquals(taskType1, taskType3);
    }

    @Test
    void testTaskTypeHashCode() {
        TaskType taskType1 = new TaskType(1, "cleaning", "clean clean clean", 1, new Date());
        TaskType taskType2 = new TaskType(1, "cleaning", "clean clean clean", 1, new Date());
        TaskType taskType3 = new TaskType(2, "dirtying", "not clean clean clean", 1, new Date());

        assertEquals(taskType1.hashCode(), taskType2.hashCode());
        assertNotEquals(taskType1.hashCode(), taskType3.hashCode());
    }
}
