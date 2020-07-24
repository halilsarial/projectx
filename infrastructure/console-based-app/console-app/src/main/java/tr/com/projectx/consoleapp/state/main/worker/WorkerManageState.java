package tr.com.projectx.consoleapp.state.main.worker;


import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.domain.model.work.Worker;

public class WorkerManageState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;

    public WorkerManageState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
    }

    @Override
    public void process() throws Exception {
        System.out.println(String.format("What would you like to do regarding workers?:%n" +
                " '1'  - Create new worker%n" +
                " '2'  - List all workers%n" +
                " '3'  - Find by registration number%n" +
                " '4'  - Delete existing worker%n" +
                " '5'  - Edit existing worker%n" +
                " '0'  - Back To Main Menu"));
        String input = consoleManager.readLine();
        if (null == input) {
            System.out.println("Invalid input. Use one from above");
        } else {
            switch (input) {
                case "1":
                    nextState = new CreateWorkerState();
                    consoleManager.clear();
                    break;
                case "2":
                    nextState = new ListAllWorkersState();
                    consoleManager.clear();
                    break;
                case "3":
                    nextState = new FindWorkerState();
                    consoleManager.clear();
                    break;
                case "4":
                    nextState = new DeleteWorkerState();
                    consoleManager.clear();
                    break;
                case "5":
                    nextState = new EditWorkerState();
                    consoleManager.clear();
                    break;
                case "0":
                    nextState = new MainState();
                    consoleManager.clear();
                    break;
                default:
                    System.out.println("Invalid input. Use one from above");
                    nextState = this;
                    consoleManager.clear();
                    break;
            }
        }
    }

    protected void printWorkerToScreen(Worker worker) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Registration Number\t:\t" + worker.getRegistrationNumber());
        System.out.println("Name\t\t\t\t:\t" + worker.getName());
        System.out.println("Surname\t\t\t\t:\t" + worker.getSurname());
        System.out.println("Email\t\t\t\t:\t" + worker.getEmail());
        System.out.println("Work Group\t\t\t:\t" + (worker.getWorkGroup() != null ? worker.getWorkGroup().getWorkGroupName() : StringUtils.EMPTY));
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

}
