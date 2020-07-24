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

public class FindWorkerState extends WorkerManageState {

    private final ConsoleManager consoleManager;
    private final WorkerService workerService;
    private ConsoleState nextState;

    public FindWorkerState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        this.nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String registrationNumber = "";
        do {
            System.out.println("Enter registrationNumber");
            registrationNumber = consoleManager.readLine();
        } while (StringUtils.isEmpty(registrationNumber));

        try {
            Worker foundedWorker = workerService.findWorkerByRegistrationNumber(registrationNumber);
            System.out.println(String.format("Worker found. Details:%nLogin: %s%nName: %s%nSurname: %s%nEmail: %s%n%n",
                    foundedWorker.getRegistrationNumber(),
                    foundedWorker.getName(),
                    foundedWorker.getSurname(),
                    foundedWorker.getEmail()));
            System.out.println(String.format("Edit? (y/n)"));
            String yesNo = consoleManager.readLine();
            if ("y".equalsIgnoreCase(yesNo)) {
                nextState = new EditWorkerState(foundedWorker);
            } else {
                nextState = new MainState();
            }
        } catch (WorkerDoesntExistException e) {
            System.out.println(String.format("Worker with registrationNumber '%s' cant be found! Try again? (y/n)", registrationNumber));
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
