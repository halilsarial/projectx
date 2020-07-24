package tr.com.projectx.repository;

import tr.com.projectx.domain.model.work.Worker;

import java.util.Set;

public interface WorkerRepository {

    Set<Worker> getAllWorkers();

    Worker findWorkerByRegistrationNumber(String registrationNumber);

    boolean saveWorker(Worker worker);

    boolean updateWorker(Worker worker);

    boolean deleteWorker(Worker worker);
}
