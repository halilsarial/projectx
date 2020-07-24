package tr.com.projectx.consoleapp.state.main.worker;


import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.factories.worker.WorkerServiceFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.service.exceptions.worker.WorkerDoesntExistException;
import tr.com.projectx.service.services.WorkerService;

public class EditWorkerState extends WorkerManageState {

    private final ConsoleManager consoleManager;
    private final WorkerService workerService;
    private ConsoleState nextState;
    private final String oldRegistrationNumber;
    private final Worker workerToEdit;

    public EditWorkerState(Worker workerToEdit) {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        nextState = new MainState();
        this.workerToEdit = workerToEdit;
        this.oldRegistrationNumber = workerToEdit.getRegistrationNumber();
    }

    @Override
    public void process() throws Exception {
        System.out.println("Enter registration number for finding to edit worker"));

        String name;
        do {
            System.out.println(String.format("Change '%s' name to:", workerToEdit.getName()));
            name = consoleManager.readLine();
        } while (StringUtils.isEmpty(name));
        String surname;
        do {
            System.out.println(String.format("Change '%s' surname to:", workerToEdit.getSurname()));
            surname = consoleManager.readLine();
        } while (StringUtils.isEmpty(surname));
        String email;
        do {
            System.out.println(String.format("Change '%s' email to:", workerToEdit.getEmail()));
            email = consoleManager.readLine();
        } while (StringUtils.isEmpty(email));
        try {
            workerService.editWorker(new Worker(oldRegistrationNumber, name, surname, email));
            System.out.println(String.format("Worker changed!"));
            consoleManager.readLine();
            nextState = new MainState();
        } catch (WorkerDoesntExistException e) {
            System.out.println(String.format("Worker dont exist! Try to search again? (y/n)"));
            String yesNo = consoleManager.readLine();
            if ("y".equalsIgnoreCase(yesNo)) {
                nextState = new FindWorkerState();
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
