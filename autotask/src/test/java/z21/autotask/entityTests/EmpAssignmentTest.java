package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import z21.autotask.entities.Employee;
import z21.autotask.entities.EmpAssignment;
import z21.autotask.entities.EmpStatus;
import z21.autotask.entities.Location;
import z21.autotask.entities.Position;
import z21.autotask.entities.Task;
import z21.autotask.entities.TaskStatus;
import z21.autotask.entities.TaskType;
import z21.autotask.entities.User;

public class EmpAssignmentTest {
    @Test
    void testEmpAssignmentConstructor() {
        EmpAssignment empAssignment = new EmpAssignment(new Employee(), new Task());

        assertNotNull(empAssignment.getEmployee());
        assertNotNull(empAssignment.getTask());
    }

    @Test
    void testEmpAssignmentNoArgConstructor() {
        EmpAssignment empAssignment = new EmpAssignment();

        assertNull(empAssignment.getEmployee());
        assertNull(empAssignment.getTask());
    }

    @Test
    void testEmpAssignmentSetters() {
        EmpAssignment empAssignment = new EmpAssignment();

        empAssignment.setEmployee(new Employee());
        empAssignment.setTask(new Task());

        assertNotNull(empAssignment.getEmployee());
        assertNotNull(empAssignment.getTask());
    }

    @Test
    void testEmpAssignmentGetters() {
        EmpAssignment empAssignment = new EmpAssignment(new Employee(), new Task());

        assertNotNull(empAssignment.getEmployee());
        assertNotNull(empAssignment.getTask());
    }

    @Test
    void testEmpAssignmentEquals() {
        Employee employee = new Employee();
        Task task = new Task();
        Employee notemployee = new Employee(1, "Krzys", "S", 'x', new Date(), new Position(), new EmpStatus(), new User());
        Task difftask = new Task(1, "OOP", new Date(), new Date(), new Date(), 99999, new Location(), new TaskStatus(), new TaskType());
        EmpAssignment empAssignment1 = new EmpAssignment(employee, task);
        EmpAssignment empAssignment2 = new EmpAssignment(notemployee, difftask);
        EmpAssignment empAssignment3 = new EmpAssignment(employee, task);

        assertEquals(empAssignment1, empAssignment3);
        assertNotEquals(empAssignment1, empAssignment2);
    }

    @Test
    void testEmpAssignmentHashCode() {
        Employee employee = new Employee();
        Task task = new Task();
        Employee notemployee = new Employee(1, "Krzys", "S", 'x', new Date(), new Position(), new EmpStatus(), new User());
        Task difftask = new Task(1, "OOP", new Date(), new Date(), new Date(), 99999, new Location(), new TaskStatus(), new TaskType());
        EmpAssignment empAssignment1 = new EmpAssignment(employee, task);
        EmpAssignment empAssignment2 = new EmpAssignment(notemployee, difftask);
        EmpAssignment empAssignment3 = new EmpAssignment(employee, task);

        assertEquals(empAssignment1.hashCode(), empAssignment3.hashCode());
        assertNotEquals(empAssignment1.hashCode(), empAssignment2.hashCode());
    }
}
