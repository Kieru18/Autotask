package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import z21.autotask.entities.TaskStatus;

public class TaskStatusTest {
    @Test
    void testTaskStatusConstructor() {
        TaskStatus taskStatus = new TaskStatus(1, "in progress");
        
        assertEquals(1, taskStatus.getStatusId());
        assertEquals("in progress", taskStatus.getDescription());
    }

    @Test
    void testTaskStatusConstructorEmptyValues() {
        TaskStatus taskStatus = new TaskStatus(0, "");
        
        assertEquals(0, taskStatus.getStatusId());
        assertEquals("", taskStatus.getDescription());
    }

    @Test
    void testTaskStatusNoArgConstructor() {
        TaskStatus taskStatus = new TaskStatus();
        
        assertNull(taskStatus.getStatusId());
        assertNull(taskStatus.getDescription());
    }

    @Test
    void testTaskStatusSetters() {
        TaskStatus taskStatus = new TaskStatus();
        
        taskStatus.setStatusId(1);
        taskStatus.setDescription("in progress");
        
        assertEquals(1, taskStatus.getStatusId());
        assertEquals("in progress", taskStatus.getDescription());
    }

    @Test
    void testTaskStatusGetters() {
        TaskStatus taskStatus = new TaskStatus(1, "in progress");
        
        assertEquals(1, taskStatus.getStatusId());
        assertEquals("in progress", taskStatus.getDescription());
    }

    @Test
    void testTaskStatusToString() {
        TaskStatus taskStatus = new TaskStatus(1, "in progress");
        
        assertEquals("TaskStatus(statusId=1, description=in progress)", taskStatus.toString());
    }

    @Test
    void testTaskStatusEquals() {
        TaskStatus taskStatus1 = new TaskStatus(1, "in progress");
        TaskStatus taskStatus2 = new TaskStatus(1, "in progress");
        TaskStatus taskStatus3 = new TaskStatus(2, "in progress");
        TaskStatus taskStatus4 = new TaskStatus(1, "complete");
        
        assertTrue(taskStatus1.equals(taskStatus2));
        assertFalse(taskStatus1.equals(taskStatus3));
        assertFalse(taskStatus1.equals(taskStatus4));
    }

    @Test
    void testTaskStatusHashCode() {
        TaskStatus taskStatus1 = new TaskStatus(1, "in progress");
        TaskStatus taskStatus2 = new TaskStatus(1, "in progress");
        TaskStatus taskStatus3 = new TaskStatus(2, "in progress");
        TaskStatus taskStatus4 = new TaskStatus(1, "complete");
        
        assertEquals(taskStatus1.hashCode(), taskStatus2.hashCode());
        assertNotEquals(taskStatus1.hashCode(), taskStatus3.hashCode());
        assertNotEquals(taskStatus1.hashCode(), taskStatus4.hashCode());
    }
}
