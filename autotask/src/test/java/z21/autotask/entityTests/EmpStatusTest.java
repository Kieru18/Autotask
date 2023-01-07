package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import z21.autotask.entities.EmpStatus;
import z21.autotask.entities.Employee;

public class EmpStatusTest {
    @Test
    void testEmpStatusConstructor() {
        EmpStatus empStatus = new EmpStatus(1, "Cool status");
        
        assertEquals(1, empStatus.getStatusId());
        assertEquals("Cool status", empStatus.getDescription());
    }

    @Test
    void testEmpStatusConstructorEmptyValues() {
        EmpStatus empStatus = new EmpStatus(0, "");
        
        assertEquals(0, empStatus.getStatusId());
        assertEquals("", empStatus.getDescription());
    }

    @Test
    void testEmpStatusNoArgConstructor() {
        EmpStatus empStatus = new EmpStatus();
        
        assertNull(empStatus.getStatusId());
        assertNull(empStatus.getDescription());
    }

    @Test
    void testEmpStatusSetters() {
        EmpStatus empStatus = new EmpStatus();
        empStatus.setStatusId(1);
        empStatus.setDescription("Cool status");
        
        assertEquals(1, empStatus.getStatusId());
        assertEquals("Cool status", empStatus.getDescription());
    }

    @Test
    void testEmpStatusGetters() {
        EmpStatus empStatus = new EmpStatus(1, "Cool status");
        
        assertEquals(1, empStatus.getStatusId());
        assertEquals("Cool status", empStatus.getDescription());
    }

    @Test
    void testEmpStatusToString() {
        EmpStatus empStatus = new EmpStatus(1, "Cool status");
        
        assertEquals("EmpStatus(statusId=1, description=Cool status)", empStatus.toString());
    }

    @Test
    void testEmpStatusEquals() {
        EmpStatus empStatus1 = new EmpStatus(1, "Cool status");
        EmpStatus empStatus2 = new EmpStatus(1, "Cool status");
        EmpStatus empStatus3 = new EmpStatus(2, "Cool status");
        EmpStatus empStatus4 = new EmpStatus(1, "Cool status 2");
        EmpStatus empStatus5 = new EmpStatus(2, "Cool status 2");
        
        assertTrue(empStatus1.equals(empStatus2));
        assertFalse(empStatus1.equals(empStatus3));
        assertFalse(empStatus1.equals(empStatus4));
        assertFalse(empStatus1.equals(empStatus5));
    }

    @Test
    void testEmpStatusHashCode() {
        EmpStatus empStatus1 = new EmpStatus(1, "Cool status");
        EmpStatus empStatus2 = new EmpStatus(1, "Cool status");
        EmpStatus empStatus3 = new EmpStatus(2, "Cool status");
        EmpStatus empStatus4 = new EmpStatus(1, "Cool status 2");
        EmpStatus empStatus5 = new EmpStatus(2, "Cool status 2");
        
        assertEquals(empStatus1.hashCode(), empStatus2.hashCode());
        assertNotEquals(empStatus1.hashCode(), empStatus3.hashCode());
        assertNotEquals(empStatus1.hashCode(), empStatus4.hashCode());
        assertNotEquals(empStatus1.hashCode(), empStatus5.hashCode());
    }
}
