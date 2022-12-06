package z21.autotask;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animal_id;
    private String name;
    private String color;
    private Integer leg_count;

    protected Animal() {};

    public Animal(int animalId, String name, String color, int legCount) {
        this.animal_id = animalId;
        this.name = name;
        this.color = color;
        this.leg_count = legCount;
    }

    @Override
    public String toString() {
        return String.format(
            "Animal[animalId=%d, name='%s', color='%s', legCount=%d]",
            animal_id, name, color, leg_count);
    }

    public int getAnimalId() {
        return this.animal_id;
    }
    public String getName() {
        return this.name;
    }
    public String getColor() {
        return this.color;
    }
    public int getLegCount() {
        return this.leg_count;
    }

    public void setAnimalId(int animalId) {
        this.animal_id = animalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLegCount(int legCount) {
        this.leg_count = legCount;
    }
}
