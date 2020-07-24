package tr.com.projectx.domain.model.work;

import java.util.HashSet;
import java.util.Set;

public class WorkGroup {

    private String workGroupCode;

    private String workGroupName;

    private Set<Worker> workers = new HashSet<>();

    public WorkGroup() {
    }

    public WorkGroup(String workGroupCode, String workGroupName) {
        this.workGroupCode = workGroupCode;
        this.workGroupName = workGroupName;
    }

    public WorkGroup(String workGroupCode, String workGroupName, Set<Worker> workers) {
        this.workGroupCode = workGroupCode;
        this.workGroupName = workGroupName;
        this.workers = workers;
    }

    public String getWorkGroupCode() {
        return workGroupCode;
    }

    public void setWorkGroupCode(String workGroupCode) {
        this.workGroupCode = workGroupCode;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }
}
