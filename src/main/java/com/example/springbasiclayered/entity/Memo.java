package com.example.springbasiclayered.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Memo {

    private Long id;
    private String title;
    private String contents;

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void update(String title) {
        this.title = title;
    }

}