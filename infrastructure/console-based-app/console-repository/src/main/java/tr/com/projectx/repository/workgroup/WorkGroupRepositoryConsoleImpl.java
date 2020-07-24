package tr.com.projectx.repository.workgroup;


import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.domain.model.work.Worker;
import tr.com.projectx.repository.WorkGroupRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WorkGroupRepositoryConsoleImpl implements WorkGroupRepository {

    private static final Map<String, WorkGroup> workGroups = new HashMap<>();

    @Override
    public WorkGroup saveWorkGroup(WorkGroup workGroup) {
        return workGroups.put(workGroup.getWorkGroupName(), workGroup);
    }

    @Override
    public WorkGroup updateWorkGroup(WorkGroup workGroup) {
        return workGroups.put(workGroup.getWorkGroupName(), workGroup);
    }

    @Override
    public WorkGroup deleteWorkGroup(WorkGroup workGroup) {
        return workGroups.remove(workGroup.getWorkGroupName());
    }

    @Override
    public WorkGroup findWorkGroupByWorkGroupCode(String workGroupCode) {
        return workGroups.get(workGroupCode);
    }

    @Override
    public Set<Worker> findWorkersOfWorkGroup(WorkGroup workGroup) {
        return workGroups.get(workGroup.getWorkGroupName()).getWorkers();
    }
}
