package com.example.springbootapi.dto;

import lombok.Getter;

public enum RESULT {

    HIGH("非常に良く飛ぶ"), //
    MID("良く飛ぶ"), //
    LOW("あまり飛ばず快適");//

    @Getter
    private String dispWord;

    RESULT(String dispWord) {
        this.dispWord = dispWord;
    }

}