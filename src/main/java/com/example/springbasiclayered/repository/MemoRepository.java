package com.example.springbasiclayered.repository;

import com.example.springbasiclayered.entity.Memo;

import java.util.Map;

public interface MemoRepository {

    void saveMemo(Memo memo);

    Map<Long, Memo> findAllMemos();

    Memo findMemoById(Long id);

    void deleteMemo(Long id);

}
