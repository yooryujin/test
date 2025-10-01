package com.example.ryujin.attend.service;

import com.example.ryujin.attend.dto.AttendRequest;
import com.example.ryujin.attend.dto.AttendResponse;
import com.example.ryujin.attend.entity.Attend;
import com.example.ryujin.attend.repository.AttendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttendService {

    private final AttendRepository attendRepository;

    @Transactional
    public AttendResponse createAttend(AttendRequest attendRequest) {
        Attend attend = Attend.builder()
                .username(attendRequest.getUsername())
                .content(attendRequest.getContent())
                .timestamp(LocalDateTime.now())
                .build();

        Attend savedAttend = attendRepository.save(attend);

        return AttendResponse.fromEntity(savedAttend);
    }

    public List<AttendResponse> findAllAttends() {
        return attendRepository.findAll().stream()
                .map(AttendResponse::fromEntity)
                .collect(Collectors.toList());
    }

}
