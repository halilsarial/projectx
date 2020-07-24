package tr.com.projectx.service.exceptions.worker;

import tr.com.projectx.service.exceptions.BaseException;

public class WorkerAlreadyExistsException extends BaseException {

    public WorkerAlreadyExistsException(String string) {
        super(string);
    }
}
