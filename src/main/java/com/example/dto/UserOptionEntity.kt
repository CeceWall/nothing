package com.example.dto

import javax.persistence.*

@Entity
@Table(name = "UserOption", schema = "dbo", catalog = "TNMYTAGSDB")
open class UserOptionEntity {
  @get:Id
  @get:Column(name = "ID", nullable = false)
  var ID: Int? = null
  @get:Basic
  @get:Column(name = "employeeid", nullable = true, insertable = false, updatable = false)
  var employeeid: String? = null
  @get:Basic
  @get:Column(name = "Valuetype", nullable = true)
  var valuetype: String? = null
  @get:Basic
  @get:Column(name = "ValueID", nullable = true)
  var valueID: String? = null
  @get:Basic
  @get:Column(name = "Valueclass", nullable = true)
  var valueclass: String? = null
  @get:Basic
  @get:Column(name = "Loginname", nullable = true)
  var loginname: String? = null

  @get:ManyToOne(fetch = FetchType.LAZY)
  @get:JoinColumn(name = "employeeid", referencedColumnName = "EmployeeID")
  var refEmployee: Employee? = null

  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "ID = $ID " +
          "employeeid = $employeeid " +
          "valuetype = $valuetype " +
          "valueID = $valueID " +
          "valueclass = $valueclass " +
          "loginname = $loginname " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as UserOptionEntity

    if (ID != other.ID) return false
    if (employeeid != other.employeeid) return false
    if (valuetype != other.valuetype) return false
    if (valueID != other.valueID) return false
    if (valueclass != other.valueclass) return false
    if (loginname != other.loginname) return false

    return true
  }

}

