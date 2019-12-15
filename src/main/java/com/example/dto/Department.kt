package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "Department")
open class Department{
  @get:Id
  @get:Column(name = "DeptID", nullable = false, insertable = false, updatable = false)
  var deptID: String? = null
  @get:Basic
  @get:Column(name = "DeptName", nullable = true)
  var deptName: String? = null
  @get:Basic
  @get:Column(name = "IfUse", nullable = true)
  var ifUse: String? = null
  @get:Basic
  @get:Column(name = "FirstApprover", nullable = true)
  var firstApprover: String? = null
  @get:Basic
  @get:Column(name = "SecondApprover", nullable = true)
  var secondApprover: String? = null

  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "deptID = $deptID " +
          "deptName = $deptName " +
          "ifUse = $ifUse " +
          "firstApprover = $firstApprover " +
          "secondApprover = $secondApprover " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as Department

    if (deptID != other.deptID) return false
    if (deptName != other.deptName) return false
    if (ifUse != other.ifUse) return false
    if (firstApprover != other.firstApprover) return false
    if (secondApprover != other.secondApprover) return false

    return true
  }

}

