package tr.com.projectx.consoleapp.state.main;

import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.worker.WorkerManageState;
import tr.com.projectx.consoleapp.state.main.workgroup.WorkGroupManageState;

public class MainState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;

    public MainState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
    }

    @Override
    public void process() throws Exception {
        System.out.println(String.format("What would you like to do?:%n " +
                "'workers'\t\t---> Manage Workers%n " +
                "'workGroups'\t---> Manage WorkGroups%n " +
                "'exit'\t\t\t---> Exit Program"));
        String input;
        input = consoleManager.readLine();
        if (null == input) {
            System.out.println("Invalid input. Use one from above");
        } else switch (input) {
            case "workers":
                System.out.println("You chose to manage Workers");
                nextState = new WorkerManageState();
                consoleManager.clear();
                break;
            case "workGroups":
                System.out.println("You chose to manage Work Groups");
                nextState = new WorkGroupManageState();
                consoleManager.clear();
                break;
            case "exit":
                System.out.println("You chose to exit! Good Bye!");
                nextState = null;
                break;
            default:
                System.out.println("Invalid input. Use one from above");
                nextState = this;
                break;
        }

    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

}
