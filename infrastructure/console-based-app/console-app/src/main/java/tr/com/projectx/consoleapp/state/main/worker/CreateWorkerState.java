package tr.com.projectx.consoleapp.state.main.worker;

import org.apache.commons.lang3.StringUtils;
import tr.com.projectx.consoleapp.factories.console.ConsoleManager;
import tr.com.projectx.consoleapp.factories.console.ConsoleManagerFactory;
import tr.com.projectx.consoleapp.factories.worker.WorkerServiceFactory;
import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;
import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.service.exceptions.worker.WorkerAlreadyExistsException;
import tr.com.projectx.service.services.WorkerService;

public class CreateWorkerState extends WorkerManageState {

    private final ConsoleManager consoleManager;
    private final WorkerService workerService;
    private ConsoleState nextState;

    public CreateWorkerState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String registrationNumber;
        do {
            System.out.println(String.format("Enter Registration Number:"));
            registrationNumber = consoleManager.readLine();
        } while (StringUtils.isEmpty(registrationNumber));
        String name;
        do {
            System.out.println(String.format("Enter Name:"));
            name = consoleManager.readLine();
        } while (StringUtils.isEmpty(name));
        String surName;
        do {
            System.out.println(String.format("Enter Surname:"));
            surName = consoleManager.readLine();
        } while (StringUtils.isEmpty(surName));
        String email;
        do {
            System.out.println(String.format("Enter Email:"));
            email = consoleManager.readLine();
        } while (StringUtils.isEmpty(email));

        try {
            workerService.createWorker(new Worker(registrationNumber, name, surName, email));
            System.out.println(String.format("Worker created!"));
            consoleManager.readLine();
        } catch (WorkerAlreadyExistsException e) {
            System.out.println(String.format("Worker already exist! Try to again? (y/n)"));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            } else {
                nextState = this;
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
