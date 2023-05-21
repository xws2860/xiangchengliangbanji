package xyz.ruankun.xiangchengliangbanji.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {
    private int code;
    private String message;
    private T data;

    public SuccessResponse(T data) {
        this.code = 0;
        this.message = "success";
        this.data = data;
    }
}

