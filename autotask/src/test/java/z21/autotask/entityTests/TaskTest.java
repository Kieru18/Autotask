package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import z21.autotask.entities.Location;
import z21.autotask.entities.Task;
import z21.autotask.entities.TaskStatus;
import z21.autotask.entities.TaskType;

public class TaskTest {
    @Test
    void testTaskConstructor() {
        Task task = new Task(1, "cleaning", new Date(), new Date(), new Date(), 2,
                             new Location(), new TaskStatus(), new TaskType());
        
        assertEquals(1, task.getTaskId());
        assertEquals("cleaning", task.getDescription());
        assertNotNull(task.getDateStart());
        assertNotNull(task.getDateEnd());
        assertNotNull(task.getDeadline());
        assertEquals(2, task.getPriority());
        assertNotNull(task.getLocation());
        assertNotNull(task.getStatus());
        assertNotNull(task.getType());
    }

    @Test
    void testTaskConstructorEmptyValues() {
        Task task = new Task(0, "", null, null, null, 0, null, null, null);
        
        assertEquals(0, task.getTaskId());
        assertEquals("", task.getDescription());
        assertNull(task.getDateStart());
        assertNull(task.getDateEnd());
        assertNull(task.getDeadline());
        assertEquals(0, task.getPriority());
        assertNull(task.getLocation());
        assertNull(task.getStatus());
        assertNull(task.getType());
    }

    @Test
    void testTaskNoArgConstructor() {
        Task task = new Task();
        
        assertNull(task.getTaskId());
        assertNull(task.getDescription());
        assertNull(task.getDateStart());
        assertNull(task.getDateEnd());
        assertNull(task.getDeadline());
        assertNull(task.getPriority());
        assertNull(task.getLocation());
        assertNull(task.getStatus());
        assertNull(task.getType());
    }

    @Test
    void testTaskSetters() {
        Task task = new Task();
        
        task.setTaskId(1);
        task.setDescription("cleaning");
        task.setDateStart(new Date());
        task.setDateEnd(new Date());
        task.setDeadline(new Date());
        task.setPriority(2);
        task.setLocation(new Location());
        task.setStatus(new TaskStatus());
        task.setType(new TaskType());
        
        assertEquals(1, task.getTaskId());
        assertEquals("cleaning", task.getDescription());
        assertNotNull(task.getDateStart());
        assertNotNull(task.getDateEnd());
        assertNotNull(task.getDeadline());
        assertEquals(2, task.getPriority());
        assertNotNull(task.getLocation());
        assertNotNull(task.getStatus());
        assertNotNull(task.getType());
    }

    @Test
    void testTaskGetters() {
        Task task = new Task(1, "cleaning", new Date(), new Date(), new Date(), 2,
                             new Location(), new TaskStatus(), new TaskType());
        
        assertEquals(1, task.getTaskId());
        assertEquals("cleaning", task.getDescription());
        assertNotNull(task.getDateStart());
        assertNotNull(task.getDateEnd());
        assertNotNull(task.getDeadline());
        assertEquals(2, task.getPriority());
        assertNotNull(task.getLocation());
        assertNotNull(task.getStatus());
        assertNotNull(task.getType());
    }

    @Test
    void testTaskEqual() {
        Task task1 = new Task(1, "cleaning", new Date(), new Date(), new Date(), 2,
                              new Location(), new TaskStatus(), new TaskType());
        Task task2 = new Task(1, "cleaning", new Date(), new Date(), new Date(), 2,
                              new Location(), new TaskStatus(), new TaskType());
        Task task3 = new Task(2, "feeding", new Date(), new Date(), new Date(), 1,
                              new Location(), new TaskStatus(), new TaskType());
        
        assertEquals(task1, task2);
        assertNotEquals(task1, task3);
    }

    @Test
    void testTaskHashCode() {
        Task task1 = new Task(1, "cleaning", new Date(), new Date(), new Date(), 2,
                              new Location(), new TaskStatus(), new TaskType());
        Task task2 = new Task(1, "cleaning", new Date(), new Date(), new Date(), 2,
                              new Location(), new TaskStatus(), new TaskType());
        Task task3 = new Task(2, "feeding", new Date(), new Date(), new Date(), 1,
                              new Location(), new TaskStatus(), new TaskType());
        
        assertEquals(task1.hashCode(), task2.hashCode());
        assertNotEquals(task1.hashCode(), task3.hashCode());
    }


}
