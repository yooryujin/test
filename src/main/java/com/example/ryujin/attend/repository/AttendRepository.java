package com.example.ryujin.attend.repository;

import com.example.ryujin.attend.entity.Attend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {
    Page<Attend> findAll(Pageable pageable);

}