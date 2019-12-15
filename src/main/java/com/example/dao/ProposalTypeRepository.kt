package com.example.dao

import com.example.dto.ProposalType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProposalTypeRepository: JpaRepository<ProposalType, Long>
