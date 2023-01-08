package z21.autotask.entityTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import z21.autotask.entities.Animal;
import z21.autotask.entities.AnimalAssignment;
import z21.autotask.entities.Location;
import z21.autotask.entities.Species;
import z21.autotask.entities.Task;
import z21.autotask.entities.TaskStatus;
import z21.autotask.entities.TaskType;

public class AnimalAssignmentTest {
    @Test
    void testAnimalAssignmentConstructor() {
        AnimalAssignment animalAssignment = new AnimalAssignment(new Animal(), new Task());

        assertNotNull(animalAssignment.getAnimal());
        assertNotNull(animalAssignment.getTask());
    }

    @Test
    void testAnimalAssignmentNoArgConstructor() {
        AnimalAssignment animalAssignment = new AnimalAssignment();

        assertNull(animalAssignment.getAnimal());
        assertNull(animalAssignment.getTask());
    }

    @Test
    void testAnimalAssignmentSetters() {
        AnimalAssignment animalAssignment = new AnimalAssignment();

        animalAssignment.setAnimal(new Animal());
        animalAssignment.setTask(new Task());

        assertNotNull(animalAssignment.getAnimal());
        assertNotNull(animalAssignment.getTask());
    }

    @Test 
    void testAnimalAssignmentGetters() {
        AnimalAssignment animalAssignment = new AnimalAssignment(new Animal(), new Task());

        assertNotNull(animalAssignment.getAnimal());
        assertNotNull(animalAssignment.getTask());
    }

    @Test
    void testAnimalAssignmentEquals() {
        Animal animal = new Animal();
        Task task = new Task();
        Animal notanimal = new Animal(1, "Grześ", 21.37f, new Location(), new Species(), new Date());
        Task difftask = new Task(1, "OOP", new Date(), new Date(), new Date(), 99999, new Location(), new TaskStatus(), new TaskType());
        AnimalAssignment animalAssignment1 = new AnimalAssignment(animal, task);
        AnimalAssignment animalAssignment2 = new AnimalAssignment(notanimal, difftask);
        AnimalAssignment animalAssignment3 = new AnimalAssignment(animal, task);

        assertNotEquals(animalAssignment1, animalAssignment2);
        assertEquals(animalAssignment1, animalAssignment3);
    }

    @Test
    void testAnimalAssignmentHashCode() {
        Animal animal = new Animal();
        Task task = new Task();
        Animal notanimal = new Animal(1, "Grześ", 21.37f, new Location(), new Species(), new Date());
        Task difftask = new Task(1, "OOP", new Date(), new Date(), new Date(), 99999, new Location(), new TaskStatus(), new TaskType());
        AnimalAssignment animalAssignment1 = new AnimalAssignment(animal, task);
        AnimalAssignment animalAssignment2 = new AnimalAssignment(notanimal, difftask);
        AnimalAssignment animalAssignment3 = new AnimalAssignment(animal, task);

        assertNotEquals(animalAssignment1.hashCode(), animalAssignment2.hashCode());
        assertEquals(animalAssignment1.hashCode(), animalAssignment3.hashCode());
    }
}
