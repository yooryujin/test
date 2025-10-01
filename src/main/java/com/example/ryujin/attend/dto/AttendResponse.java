package com.example.ryujin.attend.dto;

import com.example.ryujin.attend.entity.Attend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendResponse {

    private Long id;
    private String username;
    private String content;
    private LocalDateTime timestamp;

    public String getFormattedTimestamp() {
        if (this.timestamp == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.timestamp.format(formatter);
    }

    public static AttendResponse fromEntity(Attend attend) {
        return AttendResponse.builder()
                .id(attend.getId())
                .username(attend.getUsername())
                .content(attend.getContent())
                .timestamp(attend.getTimestamp())
                .build();
    }
}
