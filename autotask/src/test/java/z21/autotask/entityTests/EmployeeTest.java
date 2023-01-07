package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import z21.autotask.entities.EmpStatus;
import z21.autotask.entities.Employee;
import z21.autotask.entities.Position;
import z21.autotask.entities.User;

public class EmployeeTest {
    @Test
    void testEmployeeConstructor() {
        Employee employee = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        
        assertEquals(1, employee.getEmployeeId());
        assertEquals("Zbigniew", employee.getFirstName());
        assertEquals("100noga", employee.getLastName());
        assertEquals('M', employee.getGender());
        assertNotNull(employee.getBirthDate());
        assertNotNull(employee.getPosition());
        assertNotNull(employee.getStatus());
        assertNotNull(employee.getUser());
    }

    @Test
    void testEmployeeConstructorEmptyValues() {
        Employee employee = new Employee(0, "", "", ' ', null, null, null, null);
        
        assertEquals(0, employee.getEmployeeId());
        assertEquals("", employee.getFirstName());
        assertEquals("", employee.getLastName());
        assertEquals(' ', employee.getGender());
        assertNull(employee.getBirthDate());
        assertNull(employee.getPosition());
        assertNull(employee.getStatus());
        assertNull(employee.getUser());
    }

    @Test
    void testEmployeeNoArgConstructor() {
        Employee employee = new Employee();
        
        assertNull(employee.getEmployeeId());
        assertNull(employee.getFirstName());
        assertNull(employee.getLastName());
        assertEquals('\u0000', employee.getGender());
        assertNull(employee.getBirthDate());
        assertNull(employee.getPosition());
        assertNull(employee.getStatus());
        assertNull(employee.getUser());
    }


    @Test
    void testEmployeeSetters() {
        Employee employee = new Employee();
        
        employee.setEmployeeId(1);
        employee.setFirstName("Zbigniew");
        employee.setLastName("100noga");
        employee.setGender('M');
        employee.setBirthDate(new Date());
        employee.setPosition(new Position());
        employee.setStatus(new EmpStatus());
        employee.setUser(new User());
        
        assertEquals(1, employee.getEmployeeId());
        assertEquals("Zbigniew", employee.getFirstName());
        assertEquals("100noga", employee.getLastName());
        assertEquals('M', employee.getGender());
        assertNotNull(employee.getBirthDate());
        assertNotNull(employee.getPosition());
        assertNotNull(employee.getStatus());
        assertNotNull(employee.getUser());
    }

    @Test
    void testEmployeeToString() {
        Employee employee = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());

        assertEquals("Employee(employeeId=1, firstName=Zbigniew, lastName=100noga, gender=M, birthDate=" 
                    + employee.getBirthDate() + ", position=" + employee.getPosition() 
                    + ", status=" + employee.getStatus() + ", user=" + employee.getUser() 
                    + ")", employee.toString());
    }   

    @Test
    void testEmployeeEquals() {
        Employee employee1 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        Employee employee2 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        Employee employee3 = new Employee(2, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        Employee employee4 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee4.setFirstName("Janusz");
        Employee employee5 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee5.setLastName("Kowalski");
        Employee employee6 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee6.setGender('F');
        Employee employee7 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee7.setBirthDate(new Date(0));
        Employee employee8 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee8.setPosition(new Position());
        Employee employee9 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee9.setStatus(new EmpStatus());
        Employee employee10 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee10.setUser(new User());

        assertTrue(employee1.equals(employee2));
        assertFalse(employee1.equals(employee3));
        assertFalse(employee1.equals(employee4));
        assertFalse(employee1.equals(employee5));
        assertFalse(employee1.equals(employee6));
        assertFalse(employee1.equals(employee7));
    }

    @Test
    void testEmployeeHashCode() {
        Employee employee1 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        Employee employee2 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        Employee employee3 = new Employee(2, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        Employee employee4 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee4.setFirstName("Janusz");
        Employee employee5 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee5.setLastName("Kowalski");
        Employee employee6 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee6.setGender('F');
        Employee employee7 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee7.setBirthDate(new Date(0));
        Employee employee8 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee8.setPosition(new Position());
        Employee employee9 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee9.setStatus(new EmpStatus());
        Employee employee10 = new Employee(1, "Zbigniew", "100noga", 'M', 
                            new Date(), new Position(), new EmpStatus(), new User());
        employee10.setUser(new User());

        assertEquals(employee1.hashCode(), employee2.hashCode());
        assertNotEquals(employee1.hashCode(), employee3.hashCode());
        assertNotEquals(employee1.hashCode(), employee4.hashCode());
        assertNotEquals(employee1.hashCode(), employee5.hashCode());
        assertNotEquals(employee1.hashCode(), employee6.hashCode());
    }
}
