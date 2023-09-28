package com.tenpo.challenge.api.repository;


import com.tenpo.challenge.api.dto.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}

