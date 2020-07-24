package tr.com.projectx.repository.worker;


import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.repository.WorkerRepository;

import java.util.HashSet;
import java.util.Set;

public class WorkerRepositoryConsoleImpl implements WorkerRepository {

    private static final Set<Worker> workers = new HashSet<>();

    @Override
    public Set<Worker> getAllWorkers() {
        return workers;
    }

    @Override
    public Worker findWorkerByRegistrationNumber(String registrationNumber) {
        return workers.stream().filter(worker -> worker.getRegistrationNumber().equalsIgnoreCase(registrationNumber)).findFirst().orElse(null);
    }

    @Override
    public boolean saveWorker(Worker worker) {
        return workers.add(worker);
    }

    @Override
    public boolean updateWorker(Worker worker) {
        return workers.add(worker);
    }

    @Override
    public boolean deleteWorker(Worker worker) {
        return workers.remove(worker.getRegistrationNumber());
    }

}
