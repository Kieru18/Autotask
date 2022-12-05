public class Animal extends DatabaseObject{
    private int animalId;
    private String name;
    private String color;
    private int legCount;

    public Animal() {
    }

    public Animal(String name, String color, int legCount) {
        this.name = name;
        this.color = color;
        this.legCount = legCount;
    }

    public Animal(int animalId, String name, String color, int legCount) {
        this.animalId = animalId;
        this.name = name;
        this.color = color;
        this.legCount = legCount;
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
