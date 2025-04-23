package com.example.todo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ToDoDTO {
    private Long id;

    private String content;

    private boolean completed;

    private boolean importanted;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;
}
