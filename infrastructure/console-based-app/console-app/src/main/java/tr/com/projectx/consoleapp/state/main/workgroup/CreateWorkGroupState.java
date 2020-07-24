package tr.com.projectx.consoleapp.state.main.workgroup;

import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.factories.workgroup.WorkGroupServiceFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.service.exceptions.worker.WorkerAlreadyExistsException;
import tr.com.projectx.service.services.WorkGroupService;

public class CreateWorkGroupState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkGroupService workGroupService;
    private ConsoleState nextState;

    public CreateWorkGroupState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workGroupService = WorkGroupServiceFactory.getWorkGroupService();
        nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String workGroupCode;
        do {
            System.out.println(String.format("Enter WorkGroup Code:"));
            workGroupCode = consoleManager.readLine();
        } while (StringUtils.isEmpty(workGroupCode));
        String workGroupName;
        do {
            System.out.println(String.format("Enter WorkGroup Code:"));
            workGroupName = consoleManager.readLine();
        } while (StringUtils.isEmpty(workGroupName));
        try {
            workGroupService.createWorkGroup(new WorkGroup(workGroupCode, workGroupName));
            System.out.println(String.format("Worker created!"));
            consoleManager.readLine();
        } catch (WorkerAlreadyExistsException e) {
            System.out.println(String.format("Worker already exist! Try to again? (y/n)"));
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
