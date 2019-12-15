package com.example.dto

import javax.persistence.*


@Entity
@Table(name = "Sys_Parameter")
open class ProposalType {
  @get:Id
  @get:Column(name = "ParameterID", nullable = false, insertable = false, updatable = false)
  var proposalTypeId: Long? = null
  @get:Basic
  @get:Column(name = "ParameterValue", nullable = true)
  var proposalTypeCode: String? = null
  @get:Basic
  @get:Column(name = "ParameterType", nullable = true)
  var proposalTypeName: String? = null
  @get:Basic
  @get:Column(name = "IfUse", nullable = true)
  var ifUse: String? = null
  @get:Basic
  @get:Column(name = "Remark", nullable = true)
  var remark: String? = null


  override fun toString(): String =
      "Entity of type: ${javaClass.name} ( " +
          "parameterID = $proposalTypeId " +
          "parameterValue = $proposalTypeCode " +
          "parameterType = $proposalTypeName " +
          "ifUse = $ifUse " +
          "remark = $remark " +
          ")"

  // constant value returned to avoid entity inequality to itself before and after it's update/merge
  override fun hashCode(): Int = 42

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as ProposalType

    if (proposalTypeId != other.proposalTypeId) return false
    if (proposalTypeCode != other.proposalTypeCode) return false
    if (proposalTypeName != other.proposalTypeName) return false
    if (ifUse != other.ifUse) return false
    if (remark != other.remark) return false

    return true
  }

}

