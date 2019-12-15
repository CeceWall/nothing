package com.example.constants

import com.example.exception.BaseException

enum class ApprovalStage(val value: String) {
    NOT_APPROVE("未审批"),
    APPROVING_1ST("一级审批中"),
    APPROVING_2ND("二级审批中"),
    EXPERT_SCORING("专家打分中"),
    APPROVE_FINISHED("审批完毕");

    companion object {
        private val values = ApprovalStage.values().associateBy(ApprovalStage::value)
        fun fromString(value: String): ApprovalStage {
            return this.values[value] ?: throw BaseException("${value}不是有效的审批状态")
        }
    }

}
