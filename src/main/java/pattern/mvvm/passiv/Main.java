package pattern.mvvm.passiv;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            Model model = new Model();
            Presenter presenter = new Presenter(view, model);
            view.setPresenter(presenter);
        });
    }
}
