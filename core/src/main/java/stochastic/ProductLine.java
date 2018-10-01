package stochastic;

import java.util.LinkedList;

public class ProductLine {

    private LinkedList<Equipment> equipmentList = new LinkedList<>();

    public ProductLine() {
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public int getSize() {
        return equipmentList.size();
    }

    public LinkedList<Equipment> getEquipmentList() {
        return equipmentList;
    }
}
