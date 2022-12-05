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
    @Column(name = "animal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animalId;
    private String name;
    private String color;
    private Integer legCount;

    protected Animal() {};

    public Animal(int animalId, String name, String color, int legCount) {
        this.animalId = animalId;
        this.name = name;
        this.color = color;
        this.legCount = legCount;
    }

    @Override
    public String toString() {
        return String.format(
            "Animal[animalId=%d, name='%s', color='%s', legCount=%d]",
            animalId, name, color, legCount);
    }

    public int getAnimalId() {
        return this.animalId;
    }
    public String getName() {
        return this.name;
    }
    public String getColor() {
        return this.color;
    }
    public int getLegCount() {
        return this.legCount;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLegCount(int legCount) {
        this.legCount = legCount;
    }
}
