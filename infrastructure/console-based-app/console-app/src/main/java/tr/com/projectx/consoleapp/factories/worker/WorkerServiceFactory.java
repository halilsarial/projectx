package tr.com.projectx.consoleapp.factories.worker;

import tr.com.projectx.repository.worker.WorkerRepositoryFactory;
import tr.com.projectx.service.impl.WorkerServiceImpl;
import tr.com.projectx.service.services.WorkerService;

public class WorkerServiceFactory {

    private static WorkerService workerService;

    public static WorkerService getWorkerService() {
        if (workerService == null) {
            workerService = new WorkerServiceImpl(WorkerRepositoryFactory.getWorkerRepository());
        }
        return workerService;
    }
}
