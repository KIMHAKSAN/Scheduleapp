package com.example.springbasiclayered.controller;

import com.example.springbasiclayered.dto.MemoRequestDto;
import com.example.springbasiclayered.dto.MemoResponseDto;
import com.example.springbasiclayered.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController // @Controller + @ResponseBody
@RequestMapping("/memos") // Prefix
public class MemoController {

    // 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지할 수 있다.
    private final MemoService memoService;

    /**
     * 생성자 주입
     * 클래스가 필요로 하는 의존성을 생성자를 통해 전달하는 방식
     * @param memoService @Service로 등록된 MemoService 구현체인 Impl
     */
    private MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto) {

        return new ResponseEntity<>(memoService.saveMemo(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MemoResponseDto> findAllMemos() {

        return memoService.findAllMemos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        MemoResponseDto responseDto;

        try {
            responseDto = memoService.findMemoById(id);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {
        MemoResponseDto responseDto;

        try {
            responseDto = memoService.updateMemo(id, requestDto.getTitle(), requestDto.getContents());
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {
        MemoResponseDto responseDto;

        try {
            responseDto = memoService.updateTitle(id, requestDto.getTitle(), requestDto.getContents());
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {

        try {
            memoService.deleteMemo(id);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }

        // 성공한 경우
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
