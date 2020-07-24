package tr.com.projectx.repository;

import tr.com.projectx.domain.model.work.WorkGroup;
import tr.com.projectx.domain.model.work.Worker;

import java.util.Set;

public interface WorkGroupRepository {

    WorkGroup saveWorkGroup(WorkGroup workGroup);

    WorkGroup updateWorkGroup(WorkGroup workGroup);

    WorkGroup deleteWorkGroup(WorkGroup workGroup);

    WorkGroup findWorkGroupByWorkGroupCode(String workGroupCode);

    Set<Worker> findWorkersOfWorkGroup(WorkGroup workGroup);
}
