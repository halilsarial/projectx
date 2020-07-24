package tr.com.projectx.consoleapp.state.main.worker;


import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.factories.worker.WorkerServiceFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.service.exceptions.worker.WorkerDoesntExistException;
import tr.com.projectx.service.services.WorkerService;

public class DeleteWorkerState extends WorkerManageState {

    private final ConsoleManager consoleManager;
    private final WorkerService workerService;
    private ConsoleState nextState;

    public DeleteWorkerState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        this.nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String registrationNumber = "";
        do {
            System.out.println("Enter registration number of worker to delete");
            registrationNumber = consoleManager.readLine();
        } while (StringUtils.isEmpty(registrationNumber));
        try {
            workerService.deleteWorker(workerService.findWorkerByRegistrationNumber(registrationNumber));
            System.out.println(String.format("Worker deleted"));
            System.out.println(String.format("Delete another one? (y/n)"));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } catch (WorkerDoesntExistException e) {
            System.out.println(String.format("User with login '%s' cant be found! Try again? (y/n)", registrationNumber));
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
