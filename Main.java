import controller.ProjetoController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ProjetoController controller = new ProjetoController();
        ConsoleView view = new ConsoleView(controller);
        view.iniciarMenu();
    }
}