package tr.com.projectx.service.exceptions.workgroup;

import tr.com.projectx.service.exceptions.BaseException;

public class WorkGroupDoesntExistException extends BaseException {

    public WorkGroupDoesntExistException(String string) {
        super(string);
    }

}
