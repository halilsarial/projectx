package tr.com.projectx.service.exceptions.workgroup;

import tr.com.projectx.service.exceptions.BaseException;

public class WorkerDoesntExistInWorkGroupException extends BaseException {

    public WorkerDoesntExistInWorkGroupException(String string) {
        super(string);
    }

}
