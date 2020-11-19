package com.trade_accounting.models.util;

import com.trade_accounting.models.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "recovery_token")
public class PasswordRecoveryToken {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    @Column
    private String hash;

    @Column
    private String hashEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startTime;

    public PasswordRecoveryToken() {
    }

    public PasswordRecoveryToken(Employee employee, String hash, String hashEmailToken, Date startTime) {
        this.employee = employee;
        this.hash = hash;
        this.hashEmail = hashEmailToken;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHashEmail() {
        return hashEmail;
    }

    public void setHashEmail(String hashEmail) {
        this.hashEmail = hashEmail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordRecoveryToken that = (PasswordRecoveryToken) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(hash, that.hash) &&
                Objects.equals(hashEmail, that.hashEmail) &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, employee, hash, hashEmail, startTime);
    }
}
