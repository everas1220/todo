package com.example.board.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.dto.PageResultDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardService {

        private final BoardRepository boardRepository;
        private final ReplyRepository replyRepository;

        // 새글 작성
        public Long create(BoardDTO dto) {
                // dto => entity 변경
                Board board = dtoToEntity(dto);
                // 저장
                Board newBoard = boardRepository.save(board);
                return newBoard.getBno();
        }

        @Transactional // Reply, BoardTBL 두개의 테이블 접근 => 한번에 처리
        public void delete(Long bno) {

                replyRepository.deldeleteByBoardBno(bno);

                boardRepository.deleteById(bno);
        }

        public BoardDTO getRow(Long bno) {
                Object[] entity = boardRepository.getBoardByBno(bno);

                return entityToDto((Board) entity[0], (Member) entity[1],
                                (Long) entity[2]);
        }

        public PageResultDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

                Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                                Sort.by("bno").descending());

                Page<Object[]> result = boardRepository.list(pageRequestDTO.getType(), pageRequestDTO.getKeyword(),
                                pageable);
                // Function<T,R> : T => R로 변환
                Function<Object[], BoardDTO> fn = (entity -> entityToDto((Board) entity[0], (Member) entity[1],
                                (Long) entity[2]));
                List<BoardDTO> dtoList = result.stream().map(fn).collect(Collectors.toList());
                Long totalCount = result.getTotalElements();

                PageResultDTO<BoardDTO> pageResultDTO = PageResultDTO.<BoardDTO>withAll()
                                .dtoList(dtoList)
                                .totalCount(totalCount)
                                .pageRequestDTO(pageRequestDTO)
                                .build();

                return pageResultDTO;
        }

        private BoardDTO entityToDto(Board board, Member member, Long replyCount) {
                BoardDTO dto = BoardDTO.builder()
                                .bno(board.getBno())
                                .title(board.getTitle())
                                .content(board.getContent())
                                .createdTime(board.getCreatedDate())
                                .email(member.getEmail())
                                .name(member.getName())
                                .replyCount(replyCount)
                                .build();
                return dto;

        }

        private Board dtoToEntity(BoardDTO dto) {
                Board board = BoardDTO.builder()
                                .bno(dto.getBno())
                                .title(dto.getTitle())
                                .content(dto.getContent())
                                .member(Member.builder().email(dto.getEmail()).build())
                                .build();
                return board;

        }
}
