package com.lemzki.tools.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AsyncResult<T> {
    private long duration;
    private Status status;
    private String error;
    private T data;
}
