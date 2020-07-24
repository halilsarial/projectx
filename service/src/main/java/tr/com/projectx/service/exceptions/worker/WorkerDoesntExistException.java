package tr.com.projectx.service.exceptions.worker;

import tr.com.projectx.service.exceptions.BaseException;

public class WorkerDoesntExistException extends BaseException {

    public WorkerDoesntExistException(String string) {
        super(string);
    }

}
