package tr.com.projectx.domain.model.work;

import java.util.Objects;

public class Worker {
    private String registrationNumber;
    private String name;
    private String surname;
    private String email;
    private WorkGroup workGroup;

    public Worker() {
    }

    public Worker(String registrationNumber, String name, String surname, String email) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Worker(String registrationNumber, String name, String surname, String email, WorkGroup workGroup) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.workGroup = workGroup;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WorkGroup getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(WorkGroup workGroup) {
        this.workGroup = workGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return registrationNumber.equals(worker.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
