package com.example.board.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요")
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // Member
    @Email(message = "이메일 형식을 확인해주세요")
    @NotBlank(message = "작성자를 입력해 주세요")
    private String email;
    private String name;

    private Long replyCount;
}
