package com.example.springbasiclayered.repository;

import com.example.springbasiclayered.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoRepositoryImpl implements MemoRepository {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @Override
    public Long getMemoId() {

        return memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
    }

    @Override
    public void saveMemo(Memo memo) {

        memoList.put(memo.getId(), memo);
    }

    @Override
    public Map<Long, Memo> findAllMemos() {

        return memoList;
    }

//    @Override
//    public Memo findMemoById(Long id) {
//
//        return memoList.get(id);
//    }
//
//    @Override
//    public void deleteMemo(Long id) {
//
//        memoList.remove(id);
//    }

}
