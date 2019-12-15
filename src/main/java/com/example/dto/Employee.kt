package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "Employee")
open class Employee {
  @get:Id
  @get:Column(name = "EmployeeID", nullable = false, insertable = false, updatable = false)
  var employeeID: String? = null
  @get:Basic
  @get:Column(name = "EmployeeName", nullable = true)
  var employeeName: String? = null
  @get:Basic
  @get:Column(name = "DeptID", nullable = true, insertable = false, updatable = false)
  var deptID: String? = null
  @get:Basic
  @get:Column(name = "Mobile", nullable = true)
  var mobile: String? = null
  @get:Basic
  @get:Column(name = "Remark", nullable = true)
  var remark: String? = null
  @get:Basic
  @get:Column(name = "IfExpert", nullable = true)
  var ifExpert: String? = null

  @get:ManyToOne(fetch = FetchType.EAGER)
  @get:JoinColumn(name = "DeptID", referencedColumnName = "DeptID", updatable = false, insertable = false)
  var department: Department? = null
//  @get:OneToMany(mappedBy = "refEmployeeEntity")
//  var refProposalEntities: List<Proposal>? = null
//  @get:OneToMany(mappedBy = "refEmployeeEntity")
//  var refProposalApprovalEntities: List<ProposalApprovalEntity>? = null
//  @get:OneToMany(mappedBy = "refEmployeeEntity")
//  var refProposalscoredetail1Entities: List<Proposalscoredetail1Entity>? = null
//  @get:OneToOne(mappedBy = "refEmployeeEntity")
//  var refSysUserEntities: List<User>? = null
//  @get:OneToMany(mappedBy = "refEmployeeEntity")
//  var refUserOptionEntities: List<UserOptionEntity>? = null

  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "employeeID = $employeeID " +
          "employeeName = $employeeName " +
          "deptID = $deptID " +
          "mobile = $mobile " +
          "remark = $remark " +
          "ifExpert = $ifExpert " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as Employee

    if (employeeID != other.employeeID) return false
    if (employeeName != other.employeeName) return false
    if (deptID != other.deptID) return false
    if (mobile != other.mobile) return false
    if (remark != other.remark) return false
    if (ifExpert != other.ifExpert) return false

    return true
  }

}

