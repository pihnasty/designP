package tests.core.switchTest;

public class Test {

    public static void main(String args[]) {
        // char grade = args[0].charAt(0);
        char grade = 'R';

        System.out.println(getFromSwitch2(grade));

        System.out.println("Your grade is " + grade);
    }

    private static void getFromSwitch(char grade) {
        switch(grade) {
            case 'A' :
                System.out.println("Excellent!");
                break;
            case 'B' :
            case 'C' :
                System.out.println("Well done");
                break;
            case 'D' :
                System.out.println("You passed");
            case 'F' :
                System.out.println("Better try again");
                break;
            default :
                System.out.println("Invalid grade");
        }
    }

    private static String getFromSwitch2(char grade) {
        switch(grade) {
            case 'A' :
                return "Excellent!";
            case 'B' :
            case 'C' :
                return "Well done";
            case 'D' :
                return "You passed";
            case 'F' :
                return "Better try again";
            default :
                System.out.println("Invalid grade");
                return "default";
        }
    }
}
