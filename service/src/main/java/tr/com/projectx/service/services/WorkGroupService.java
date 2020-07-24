package tr.com.projectx.service.services;

import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.service.exceptions.BaseException;

public interface WorkGroupService {

    WorkGroup createWorkGroup(WorkGroup workGroup) throws BaseException;

    WorkGroup editWorkGroup(WorkGroup workGroup) throws BaseException;

    WorkGroup deleteWorkGroup(WorkGroup workGroup) throws BaseException;

    WorkGroup findWorkGroupByWorkGroupCode(String workGroupCode) throws BaseException;
}
