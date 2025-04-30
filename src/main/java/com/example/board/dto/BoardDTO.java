package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // Member
    private String email;
    private String name;
    private Long replyCount;
}
