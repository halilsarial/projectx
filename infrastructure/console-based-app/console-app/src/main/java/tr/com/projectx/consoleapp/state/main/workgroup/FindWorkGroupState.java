package tr.com.projectx.consoleapp.state.main.workgroup;

import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.factories.workgroup.WorkGroupServiceFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.service.exceptions.worker.WorkerDoesntExistException;
import tr.com.projectx.service.services.WorkGroupService;

public class FindWorkGroupState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkGroupService workGroupService;
    private ConsoleState nextState;

    public FindWorkGroupState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workGroupService = WorkGroupServiceFactory.getWorkGroupService();
        this.nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String workGroupCode = "";
        do {
            System.out.println(String.format("Enter Work Group Code"));
            workGroupCode = consoleManager.readLine();
        } while (StringUtils.isEmpty(workGroupCode));

        try {
            WorkGroup foundedWorkGroup = workGroupService.findWorkGroupByWorkGroupCode(workGroupCode);
            System.out.println(String.format("Worker found. Details:%nLogin: %s%nName: %s%nSurname: %s%nEmail: %s%n%n",
                    foundedWorkGroup.getWorkGroupCode(),
                    foundedWorkGroup.getWorkGroupName()));
            System.out.println(String.format("Edit? (y/n)"));
            String yesNo = consoleManager.readLine();
            if ("y".equalsIgnoreCase(yesNo)) {
                nextState = new EditWorkGroupState(foundedWorkGroup);
            } else {
                nextState = new MainState();
            }
        } catch (WorkerDoesntExistException e) {
            System.out.println(String.format("User with registrationNumber '%s' cant be found! Try again? (y/n)", workGroupCode));
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
