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

public class EditWorkGroupState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkGroupService workGroupService;
    private ConsoleState nextState;
    private final String oldWorkGroupName;
    private final WorkGroup workGroupToEdit;

    public EditWorkGroupState(WorkGroup workGroupToEdit) {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workGroupService = WorkGroupServiceFactory.getWorkGroupService();
        nextState = new MainState();
        this.workGroupToEdit = workGroupToEdit;
        this.oldWorkGroupName = workGroupToEdit.getWorkGroupName();
    }

    @Override
    public void process() throws Exception {
        String workGroupCode;
        do {
            System.out.println(String.format("Change '%s' workGroupCode to:", workGroupToEdit.getWorkGroupCode()));
            workGroupCode = consoleManager.readLine();
        } while (StringUtils.isEmpty(workGroupCode));
        String workGroupName;
        do {
            System.out.println(String.format("Change '%s' workGroupName to:", workGroupToEdit.getWorkGroupName()));
            workGroupName = consoleManager.readLine();
        } while (StringUtils.isEmpty(workGroupCode));
        try {
            workGroupService.editWorkGroup(new WorkGroup(oldWorkGroupName, workGroupName));
            System.out.println(String.format("Worker changed!"));
            consoleManager.readLine();
            nextState = new MainState();
        } catch (WorkerDoesntExistException e) {
            System.out.println(String.format("Worker dont exist! Try to search again? (y/n)"));
            String yesNo = consoleManager.readLine();
            if ("y".equalsIgnoreCase(yesNo)) {
                nextState = new FindWorkGroupState();
            } else {
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
