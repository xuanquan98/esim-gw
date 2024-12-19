package com.quanvx.esim.repository;

import com.quanvx.esim.entity.ChannelDefinitionEntity;
import com.quanvx.esim.entity.CodeMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeMappingRepository extends JpaRepository<CodeMappingEntity, Long> {

}
