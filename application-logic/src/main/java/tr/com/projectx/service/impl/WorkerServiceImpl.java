package tr.com.projectx.service.impl;

import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.repository.WorkerRepository;
import tr.com.projectx.service.exceptions.BaseException;
import tr.com.projectx.service.exceptions.worker.WorkerAlreadyExistsException;
import tr.com.projectx.service.exceptions.worker.WorkerDoesntExistException;
import tr.com.projectx.service.services.WorkerService;

import java.util.Set;

public class WorkerServiceImpl implements WorkerService {

    private WorkerRepository workerRepository;

    public WorkerServiceImpl() {
    }

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public boolean createWorker(Worker worker) throws BaseException {
        if (!workerRepository.saveWorker(worker)) {
            throw new WorkerAlreadyExistsException("Worker already exists!");
        }
        return true;
    }

    @Override
    public boolean editWorker(Worker worker) throws BaseException {
        if (!workerRepository.updateWorker(worker)) {
            throw new WorkerDoesntExistException("Worker does not exist!");
        }
        return true;
    }

    @Override
    public boolean deleteWorker(Worker worker) throws BaseException {
        if (!workerRepository.deleteWorker(worker)) {
            throw new WorkerDoesntExistException("Worker does not exist!");
        }
        return true;
    }

    @Override
    public Worker findWorkerByRegistrationNumber(String registrationNumber) {
        return workerRepository.findWorkerByRegistrationNumber(registrationNumber);
    }

    @Override
    public Set<Worker> getAllWorkers() throws BaseException {
        Set<Worker> allWorkers = workerRepository.getAllWorkers();
        if (allWorkers.isEmpty()) {
            throw new WorkerDoesntExistException("Any Worker does not exist!");
        }
        return allWorkers;
    }

    @Override
    public void updateWorkerWorkGroup(Worker worker, WorkGroup newWorkGroup) {
        WorkGroup workGroup = worker.getWorkGroup();
        workGroup.getWorkers().remove(worker);
        worker.setWorkGroup(newWorkGroup);
        newWorkGroup.getWorkers().add(worker);
    }
}
