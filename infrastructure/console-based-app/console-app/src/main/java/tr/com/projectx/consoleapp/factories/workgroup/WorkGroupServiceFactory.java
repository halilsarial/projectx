package tr.com.projectx.consoleapp.factories.workgroup;

import tr.com.projectx.repository.workgroup.WorkGroupRepositoryFactory;
import tr.com.projectx.service.impl.WorkGroupServiceImpl;
import tr.com.projectx.service.services.WorkGroupService;

public class WorkGroupServiceFactory {

    private static WorkGroupService workGroupService;

    public static WorkGroupService getWorkGroupService() {
        if (workGroupService == null) {
            workGroupService = new WorkGroupServiceImpl(WorkGroupRepositoryFactory.getWorkGroupRepository());
        }
        return workGroupService;
    }
}
