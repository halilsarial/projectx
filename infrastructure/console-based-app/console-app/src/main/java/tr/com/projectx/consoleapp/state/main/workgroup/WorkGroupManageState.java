package tr.com.projectx.consoleapp.state.main.workgroup;


import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;

public class WorkGroupManageState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;

    public WorkGroupManageState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
    }

    @Override
    public void process() throws Exception {
        System.out.println(String.format("What would you like to do regarding workers?:%n 'create' - create new worker%n 'delete' - delete existing one by login%n 'find' - find by login"));
        String input = consoleManager.readLine();
        if (null == input) {
            System.out.println("Invalid input. Use one from above");
        } else {
            switch (input) {
                case "create":
                    nextState = new CreateWorkGroupState();
                    consoleManager.clear();
                    break;
                case "delete":
                    nextState = new DeleteWorkGroupState();
                    consoleManager.clear();
                    break;
                case "find":
                    nextState = new FindWorkGroupState();
                    consoleManager.clear();
                    break;
                default:
                    System.out.println("Invalid input. Use one from above");
                    break;
            }
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

}
