package com.quanvx.esim.repository;

import com.quanvx.esim.entity.AppEntity;
import com.quanvx.esim.entity.SapoOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Long> {}
