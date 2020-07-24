package tr.com.projectx.service.services;

import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.service.exceptions.BaseException;

import java.util.Set;

public interface WorkerService {

    boolean createWorker(Worker worker) throws BaseException;

    boolean editWorker(Worker worker) throws BaseException;

    boolean deleteWorker(Worker worker) throws BaseException;

    Worker findWorkerByRegistrationNumber(String registrationNumber) throws BaseException;

    Set<Worker> getAllWorkers() throws BaseException;

    void updateWorkerWorkGroup(Worker worker, WorkGroup newWorkGroup);
}
