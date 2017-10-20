package tests.enumTest;

/**
 * Created by Pihnastyi.O on 10/20/2017.
 */
public class EnumTest2 {

    public static void main(String[] args) {


        StateCheckBox stateCheckBox = StateCheckBox.getStateCheckBox(false, true);

        System.out.println(stateCheckBox );


    }


}

enum StateCheckBox {
    Selected(true, false,"Selected"), Minus(true, true, "Minus"), Empty(false, false, "Empty"), Undefined(false, true, "Undefined");

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isIndeterminate() {
        return isIndeterminate;
    }

    boolean isSelected;
    boolean isIndeterminate;
    String label;

    StateCheckBox(boolean isSelected, boolean isIndeterminate, String label) {
        this.isSelected = isSelected;
        this.isIndeterminate = isIndeterminate;
        this.label = label;
    }

    static StateCheckBox getStateCheckBox(boolean isSelected, boolean isIndeterminate) {
        for (StateCheckBox state : StateCheckBox.values()) {
            if (state.isIndeterminate == isIndeterminate && state.isSelected == isSelected) return state;
        }
        return Undefined;
    }
    @Override
    public String toString () {
       return  label;
    }
}


