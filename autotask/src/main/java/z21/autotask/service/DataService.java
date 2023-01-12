package z21.autotask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import z21.autotask.entities.*;
import z21.autotask.repositories.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Integer getLastEmployeeId() {
        return employeeRepository.findLastEmployeeId();
    }
    private Integer getLastUserId() {
        return userRepository.findLastUserId();
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
    public void addTaskType(String name, String description, Integer base_priority, Date frequency) {
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

    public List<Employee> getEmployeesByTask(Task task) {
            return employeeRepository.findByTaskId(task.getTaskId());
        }
    public List<Animal> getAnimalsByTask(Task task) {
            return animalRepository.findByTaskId(task.getTaskId());
        }
    public List<Task> getTasksByEmployee(Employee employee) {
            return taskRepository.findByEmployeeId(employee.getEmployeeId());
        }

    public List<Task> getTasksByUser(String login) {
        List<Task> tasks = taskRepository.findByUserLogin(login);
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.getDeadline().compareTo(t2.getDeadline()) == 0) {
                    return t1.getPriority() - t2.getPriority();
                } else {
                    return t1.getDeadline().compareTo(t2.getDeadline());
                }
            }
        });
        List<Task> completedTasks = tasks.stream()
            .filter(t -> t.getStatus().getDescription().equals("completed"))
            .collect(Collectors.toList());
        tasks.removeAll(completedTasks);
        tasks.addAll(completedTasks);
        return tasks;
    }
        

    public void addUser(String login, String password, String role, String mail) {
        userRepository.insertUser(login, password, role, mail);
        Integer userId = getLastUserId();
        Integer employeeId = getLastEmployeeId();
        employeeRepository.updateUser(userId, employeeId);
    }

    public void closeTask(Task task, Date date) {
        taskRepository.updateTaskEndDate(task.getTaskId(), date);
        taskRepository.updateTaskStatus(task.getTaskId(), 1);
    }

    public void startTask(Task task, Date date) {
        taskRepository.updateTaskStartDate(task.getTaskId(), date);
        taskRepository.updateTaskStatus(task.getTaskId(), 2);
    }
}
