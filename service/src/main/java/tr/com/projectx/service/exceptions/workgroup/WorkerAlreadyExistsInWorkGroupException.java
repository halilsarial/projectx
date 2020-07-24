package tr.com.projectx.service.exceptions.workgroup;

import tr.com.projectx.service.exceptions.BaseException;

public class WorkerAlreadyExistsInWorkGroupException extends BaseException {

    public WorkerAlreadyExistsInWorkGroupException(String string) {
        super(string);
    }

}
