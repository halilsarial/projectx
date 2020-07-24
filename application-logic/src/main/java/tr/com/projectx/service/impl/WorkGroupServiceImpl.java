package tr.com.projectx.service.impl;

import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.repository.WorkGroupRepository;
import tr.com.projectx.service.exceptions.BaseException;
import tr.com.projectx.service.exceptions.workgroup.WorkGroupAlreadyExistsException;
import tr.com.projectx.service.exceptions.workgroup.WorkGroupDoesntExistException;
import tr.com.projectx.service.services.WorkGroupService;

public class WorkGroupServiceImpl implements WorkGroupService {

    private WorkGroupRepository workGroupRepository;

    public WorkGroupServiceImpl() {
    }

    public WorkGroupServiceImpl(WorkGroupRepository workGroupRepository) {
        this.workGroupRepository = workGroupRepository;
    }

    public WorkGroup createWorkGroup(WorkGroup workGroup) throws BaseException {
        if (workGroupRepository.findWorkGroupByWorkGroupCode(workGroup.getWorkGroupName()) != null) {
            throw new WorkGroupAlreadyExistsException("Work Group already exists!");
        }
        return workGroupRepository.saveWorkGroup(workGroup);
    }

    public WorkGroup editWorkGroup(WorkGroup workGroup) throws BaseException {
        if (workGroupRepository.findWorkGroupByWorkGroupCode(workGroup.getWorkGroupName()) == null) {
            throw new WorkGroupDoesntExistException("Work Group does not exist!");
        }
        return workGroupRepository.updateWorkGroup(workGroup);
    }

    public WorkGroup deleteWorkGroup(WorkGroup workGroup) throws BaseException {
        if (workGroupRepository.findWorkGroupByWorkGroupCode(workGroup.getWorkGroupName()) == null) {
            throw new WorkGroupDoesntExistException("Work Group does not exist!");
        }
        return workGroupRepository.deleteWorkGroup(workGroup);
    }

    public WorkGroup findWorkGroupByWorkGroupCode(String workGroupCode) throws BaseException {
        return workGroupRepository.findWorkGroupByWorkGroupCode(workGroupCode);
    }
}
