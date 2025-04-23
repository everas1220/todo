package com.example.todo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.ToDo;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert1() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ToDo todo = new ToDo();
            todo.setContent("강아지 산책" + i);

            todoRepository.save(todo);
        });
    }

    @Test
    public void testRead() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    @Test
    public void testRead2() {
        // 완료 목록 추출
        todoRepository.findByCompleted(true)
                .forEach(todo -> System.out.println(todo));
    }

    @Test
    public void testRead3() {
        // 완료 목록 추출
        todoRepository.findByImportanted(false)
                .forEach(todo -> System.out.println(todo));
    }

    // todo 삭제
    @Test
    public void Testdelete() {
        todoRepository.deleteById(10L);
    }

    // todo 수정 - 완료
    @Test
    public void Testupdate() {
        ToDo toDo = todoRepository.findById(1L).get();
        toDo.setCompleted(true);
        todoRepository.save(toDo);
    }
}
