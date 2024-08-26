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

    private final MemoService memoService;

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

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
//
//        try {
//            memoService.deleteMemo(id);
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getStatusCode());
//        }
//
//        // 일어날 수 없는 경우
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

}
