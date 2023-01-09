package z21.autotask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import z21.autotask.entities.*;
import z21.autotask.repositories.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DataService {

    private final AnimalRepository animalRepository;
    private final EmployeeRepository employeeRepository;
    private final SpeciesRepository speciesRepository;
    private final UserRepository userRepository;
    private final PositionRepository positionRepository;
    private final LocationRepository locationRepository;
    private final EmpStatusRepository empStatusRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TaskRepository taskRepository;
    private final EmpAssignmentRepository empAssignmentRepository;
    private final AnimalAssignmentRepository animalAssignmentRepository;


    @Autowired
    public DataService(AnimalRepository animalRepository,
                       EmployeeRepository employeeRepository,
                       SpeciesRepository speciesRepository,
                       UserRepository userRepository,
                       PositionRepository positionRepository,
                       LocationRepository locationRepository,
                       EmpStatusRepository empStatusRepository,
                       TaskTypeRepository taskTypeRepository,
                       TaskStatusRepository taskStatusRepository,
                       TaskRepository taskRepository,
                       EmpAssignmentRepository empAssignmentRepository,
                       AnimalAssignmentRepository animalAssignmentRepository) {
            this.animalRepository = animalRepository;
            this.employeeRepository = employeeRepository;
            this.speciesRepository = speciesRepository;
            this.userRepository = userRepository;
            this.positionRepository = positionRepository;
            this.locationRepository = locationRepository;
            this.empStatusRepository = empStatusRepository;
            this.taskTypeRepository = taskTypeRepository;
            this.taskStatusRepository = taskStatusRepository;
            this.taskRepository = taskRepository;
            this.empAssignmentRepository = empAssignmentRepository;
            this.animalAssignmentRepository = animalAssignmentRepository;
        }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public List<Species> getAllSpecies() {
        return speciesRepository.findAll();
    }
    public List<EmpStatus> getAllEmpStatuses() {
        return empStatusRepository.findAll();
    }
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public List<TaskStatus> getAllTaskStatuses() {
        return taskStatusRepository.findAll();
    }
    public List<TaskType> getAllTaskTypes() {
        return taskTypeRepository.findAll();
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<TaskStatus> getAwaiting() {
        return taskStatusRepository.findByDescription("awaiting");
    }
    private Integer getLastTaskId() {
        return taskRepository.findLastTaskId();
    }

    public void addAnimal(String name, Integer locationId, Integer speciesId, Float weight, Date birthDate) {
        animalRepository.insertAnimal(name, locationId, speciesId, weight, birthDate);
    }
    public void addEmployee(String firstName, String lastName, char gender, Date birthDate, Integer positionId, Integer statusId, Integer userId) {
        employeeRepository.insertEmployee(firstName, lastName, gender, birthDate, positionId, statusId, userId);
    }
    public void addTask(String description, Date dateStart, Date dateEnd, Date deadline, Integer priority, Integer locationId, Integer statusId, Integer typeId, Set<Employee> employees, Set<Animal> animals) {
        
        taskRepository.insertTask(description, dateStart, dateEnd, deadline, priority, locationId, statusId, typeId);
        Integer lastTaskId = getLastTaskId();

        for(Employee employee : employees) {
            addEmpAssignment(employee, lastTaskId);
        }
        for(Animal animal : animals) {
            addAnimalAssignment(animal, lastTaskId);
        }
    }
    public void addTaskType(String name, String description, Integer base_priority, SimpleDateFormat frequency) {
        taskTypeRepository.insertTaskType(name,  description, base_priority, frequency);
    }
    private void addEmpAssignment(Employee employee, Integer taskId) {
        empAssignmentRepository.assignEmployeeToTask(employee.getEmployeeId(), taskId);
    }
    private void addAnimalAssignment(Animal animal, Integer taskId) {
        animalAssignmentRepository.assignAnimalToTask(animal.getAnimalId(), taskId);
    }

    public List<Employee> getUnavailableEmployees() {
        return employeeRepository.findByStatus("unavailable");
    }
    public List<Employee> getAvailableEmployees() { 
        return employeeRepository.findByStatus("available");
    }
}
