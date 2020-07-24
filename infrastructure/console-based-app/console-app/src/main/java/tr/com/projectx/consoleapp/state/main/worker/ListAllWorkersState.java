package tr.com.projectx.consoleapp.state.main.worker;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.factories.worker.WorkerServiceFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.service.exceptions.worker.WorkerDoesntExistException;
import tr.com.projectx.service.services.WorkerService;

import java.util.Set;

public class ListAllWorkersState extends WorkerManageState {

    private final ConsoleManager consoleManager;
    private final WorkerService workerService;
    private ConsoleState nextState;

    public ListAllWorkersState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        this.nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String registrationNumber = "";
        try {
            Set<Worker> allWorkers = workerService.getAllWorkers();
            if(CollectionUtils.isEmpty(allWorkers)){
                System.out.println(String.format("Any worker was  created yet!"));
            }else {
                System.out.println(String.format("%d Workers was found.", allWorkers.size()));
            }
            allWorkers.forEach(worker -> printWorkerToScreen(worker));
            nextState = new MainState();
        } catch (WorkerDoesntExistException e) {
            System.out.println(String.format("User with registrationNumber '%s' cant be found! Try again? (y/n)", registrationNumber));
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
