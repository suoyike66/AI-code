package com.suoyike.aicodespringboot.exception;

import cn.hutool.json.JSONUtil;
import com.suoyike.aicodespringboot.common.BaseResponse;
import com.suoyike.aicodespringboot.common.ResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;

@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    /**
     * 处理 SSE 流式响应中的异常
     * 当发生 JSON 解析错误或其他流式处理错误时，发送错误事件到客户端
     */
    @ExceptionHandler({com.fasterxml.jackson.core.JsonParseException.class})
    public void handleJsonParseErrorInSSE(com.fasterxml.jackson.core.JsonParseException e,
                                          org.springframework.web.context.request.WebRequest request) throws IOException {
        log.error("JSON 解析错误（可能是 AI 返回的工具调用参数格式错误）: {}", e.getMessage(), e);
        
        // 尝试获取 SseEmitter 并发送错误事件
        if (request instanceof org.springframework.web.context.request.NativeWebRequest) {
            org.springframework.web.context.request.NativeWebRequest nativeWebRequest = 
                (org.springframework.web.context.request.NativeWebRequest) request;
            jakarta.servlet.http.HttpServletResponse response = 
                nativeWebRequest.getNativeResponse(jakarta.servlet.http.HttpServletResponse.class);
            
            if (response != null && !response.isCommitted()) {
                response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
                response.setCharacterEncoding("UTF-8");
                
                // 发送错误事件
                Map<String, String> errorData = Map.of(
                    "error", "AI 响应格式错误",
                    "message", "AI 返回的数据格式不正确，请重试"
                );
                String errorJson = JSONUtil.toJsonStr(errorData);
                response.getWriter().write("event: error\ndata: " + errorJson + "\n\n");
                response.getWriter().flush();
            }
        }
    }
}
