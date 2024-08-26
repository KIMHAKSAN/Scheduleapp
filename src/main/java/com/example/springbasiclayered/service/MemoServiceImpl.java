package com.example.springbasiclayered.service;

import com.example.springbasiclayered.dto.MemoRequestDto;
import com.example.springbasiclayered.dto.MemoResponseDto;
import com.example.springbasiclayered.entity.Memo;
import com.example.springbasiclayered.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto requestDto) {

        // MemoId 식별자 계산
        Long memoId = memoRepository.getMemoId();

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());

        // Inmemory DB에 Memo 저장
        memoRepository.saveMemo(memo);

        return new MemoResponseDto(memo);
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {
        // init List
        List<MemoResponseDto> responseList = new ArrayList<>();

        // 전체 조회
        Map<Long, Memo> memoList = memoRepository.findAllMemos();

        // HashMap<Memo> -> List<MemoResponseDto>
        for (Memo memo : memoList.values()) {
            MemoResponseDto responseDto = new MemoResponseDto(memo);
            responseList.add(responseDto);
        }

        return responseList;
    }

    @Override
    public MemoResponseDto findMemoById(Long id) {
        // 식별자의 Memo가 없다면?
        Memo memo = memoRepository.findMemoById(id);

        // NPE 방지
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(memo);
    }

//    @Override
//    public MemoResponseDto updateMemo(Long id, String title, String contents) {
//        // memo 조
//        Memo memo = memoRepository.findMemoById(id);
//
//        // NPE 방지
//        if (memo == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
//        }
//
//        // 필수값 검증
//        if (title == null || contents == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
//        }
//
//        // memo 수정
//        memo.update(title, contents);
//
//        return new MemoResponseDto(memo);
//    }
//
//    @Override
//    public MemoResponseDto updateTitle(Long id, String title, String contents) {
//        // memo 조회
//        Memo memo = memoRepository.findMemoById(id);
//
//        // NPE 방지
//        if (memo == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
//        }
//        // 필수값 검증
//        if (title == null || contents != null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
//        }
//
//        memo.update(title);
//
//        return null;
//    }
//
//    @Override
//    public void deleteMemo(Long id) {
//        // memo 조회
//        Memo memo = memoRepository.findMemoById(id);
//
//        // NPE 방지
//        if (memo == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
//        }
//
//        memoRepository.deleteMemo(id);
//
//    }

}
