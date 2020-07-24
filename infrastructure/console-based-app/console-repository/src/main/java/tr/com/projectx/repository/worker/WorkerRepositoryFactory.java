package tr.com.projectx.repository.worker;

import tr.com.projectx.repository.WorkerRepository;

public class WorkerRepositoryFactory {

    private static WorkerRepository workerRepository;

    public static WorkerRepository getWorkerRepository() {
        if (workerRepository == null) {
            workerRepository = new WorkerRepositoryConsoleImpl();
        }
        return workerRepository;
    }
}
