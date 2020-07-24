package tr.com.projectx.consoleapp.state.main.workgroup;


import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.factories.workgroup.WorkGroupServiceFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.service.exceptions.worker.WorkerDoesntExistException;
import tr.com.projectx.service.services.WorkGroupService;

public class DeleteWorkGroupState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkGroupService workGroupService;
    private ConsoleState nextState;

    public DeleteWorkGroupState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workGroupService = WorkGroupServiceFactory.getWorkGroupService();
        this.nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String workGroupCode = "";
        do {
            System.out.println(String.format("Enter name of work group to delete"));
            workGroupCode = consoleManager.readLine();
        } while (StringUtils.isEmpty(workGroupCode));
        try {
            workGroupService.deleteWorkGroup(workGroupService.findWorkGroupByWorkGroupCode(workGroupCode));
            System.out.println(String.format("Work Group deleted"));
            System.out.println(String.format("Delete another one? (y/n)"));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } catch (WorkerDoesntExistException e) {
            System.out.println(String.format("User with login '%s' cant be found! Try again? (y/n)", workGroupCode));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } finally {
            consoleManager.clear();
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

}
