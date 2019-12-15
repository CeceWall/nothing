package com.example.dto;

import com.example.exception.EmployeeInvalidException;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

@Entity
@Table(name = "Sys_User")
public class User implements UserDetails {
    @Id
    @Column(name = "LoginName")
    private String loginName;
    @Column(name = "Password")
    private String password;
    @Column(name = "Remark")
    private String remark;

    @Column(name = "EmployeeID")
    private String employeeId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID", insertable = false, updatable = false)
    private Employee employee;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Nullable
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @NotNull
    public String getUserDeptId() {
        if (this.employee != null) {
            return this.employee.getDeptID();
        }
        throw new EmployeeInvalidException("当前用户的没有对应的employee信息或employee中未配置department信息");
    }
}
