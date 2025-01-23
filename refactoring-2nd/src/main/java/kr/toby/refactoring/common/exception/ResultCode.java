package kr.toby.refactoring.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultCode {

    SUCCESS("Success"),
    ERROR("Error"),

    // Play
    INVALID_PLAY_TYPE("Invalid play type"),
    ;

    private final String message;
}
