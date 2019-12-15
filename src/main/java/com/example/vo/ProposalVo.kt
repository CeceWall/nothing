package com.example.vo

import com.example.dto.Proposal
import org.springframework.beans.BeanUtils
import javax.validation.constraints.*

data class ProposalVo(
        var proposalId: Long? = null,
        @get: NotBlank
        @get: Size(min = 10, max = 50)
        var proposalName: String? = null,
        @get: NotNull
        var proposalTypeId: Long? = null,
        @get: NotEmpty
        @get: Size(max = 50)
        var proposalStaff: String? = null,
        @get: NotEmpty
        @get: Size(min = 30, max = 255)
        var beforeImprove: String? = null,
        var beforeImproveAttachment: List<String>? = null,
        @get: NotEmpty
        @get: Size(min = 30, max = 255)
        var afterImprove: String? = null,
        var afterImproveAttachment: List<String>? = null,
        @get: NotEmpty
        @get: Size(min = 80, max = 255)
        var counterMeasure: String? = null,
        @get: NotEmpty()
        @get: Size(min = 80, max = 255)
        var effect: String? = null,
        @get: NotNull
        var effectValue: Double? = null
) {
    constructor(proposal: Proposal) : this() {
        BeanUtils.copyProperties(proposal, this);
    }
}

