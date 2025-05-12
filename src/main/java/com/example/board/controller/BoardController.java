package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.service.BoardService;

import jakarta.validation.Valid;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public void getCteate(@ModelAttribute("dto") BoardDTO dto) {
        log.info("글 작성 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCteate(@ModelAttribute("dto") @Valid BoardDTO dto, BindingResult result,
            PageRequestDTO pageRequestDTO,
            RedirectAttributes rttr) {
        log.info("글 작성 요청{}", dto);

        if (result.hasErrors()) {
            return "/board/create";
        }
        rttr.addAttribute("page", pageRequestDTO.getPage());
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/board/list";
    }

    @PreAuthorize("authentication.name == #dto.email")
    @PostMapping("/modify")
    public String postModify(BoardDTO dto, PageRequestDTO pageRequestDTO,
            RedirectAttributes rttr) {
        log.info("수정 {} {}", dto, pageRequestDTO);

        Long bno = boardService.update(dto);

        rttr.addAttribute("page", pageRequestDTO.getPage());
        rttr.addAttribute("size", pageRequestDTO.getSize());
        rttr.addAttribute("type", pageRequestDTO.getType());
        rttr.addAttribute("keyword", pageRequestDTO.getKeyword());
        return "redirect:/board/read";
    }

    @GetMapping("/list")
    public void getList(Model model, PageRequestDTO pageRequestDTO) {
        log.info("List 요청", pageRequestDTO);

        PageResultDTO<BoardDTO> result = boardService.getList(pageRequestDTO);
        model.addAttribute("result", result);
    }

    @GetMapping({ "/read", "/modity" })
    public void getRead(Long bno, PageRequestDTO pageRequestDTO, Model model) {
        log.info("get{}", bno);
        BoardDTO dto = boardService.getRow(bno);
        model.addAttribute("dto", dto);
    }
}
