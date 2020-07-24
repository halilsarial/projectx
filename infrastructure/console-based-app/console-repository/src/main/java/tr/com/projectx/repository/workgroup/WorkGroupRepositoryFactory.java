package tr.com.projectx.repository.workgroup;

import tr.com.projectx.repository.WorkGroupRepository;

public class WorkGroupRepositoryFactory {

    private static WorkGroupRepository workGroupRepository;

    public static WorkGroupRepository getWorkGroupRepository() {
        if (workGroupRepository == null) {
            workGroupRepository = new WorkGroupRepositoryConsoleImpl();
        }
        return workGroupRepository;
    }
}
