package tr.com.projectx.service.exceptions.workgroup;

import tr.com.projectx.service.exceptions.BaseException;

public class WorkGroupAlreadyExistsException extends BaseException {

    public WorkGroupAlreadyExistsException(String string) {
        super(string);
    }
}
