package com.example.service

import com.example.dao.ProposalRepository
import com.example.dao.ProposalTypeRepository
import com.example.dto.ProposalType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProposalTypeService {
  @Autowired
  lateinit var proposalTypeRepository: ProposalTypeRepository

  fun getProposalList(): List<ProposalType> {
    return this.proposalTypeRepository.findAll();
  }
}
