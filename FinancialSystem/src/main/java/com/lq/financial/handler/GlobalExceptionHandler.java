package com.lq.financial.handler;

import com.lq.financial.bean.CodeEnumEntity;
import com.lq.financial.bean.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-08  22:31
 * @Description: 全局异常捕获
 * @Version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 不同异常返回不同结果
     * @author xuyuxiang
     * @date 2022/7/28 16:54
     **/
   /* @ResponseBody
    @ExceptionHandler
    public ResultVo<String> handleException(Exception e) {
        return GlobalExceptionUtil.getCommonResult(e);
    }*/

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResultVo<String> handler(AccessDeniedException e) {
        log.info("security权限不足：----------------{}", e.getMessage());
        return new ResultVo(true, CodeEnumEntity.SYSTEM_ERROR.getCode(),e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo<String> handler(MethodArgumentNotValidException e) {
        log.info("实体校验异常：----------------{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return new ResultVo(true, CodeEnumEntity.SYSTEM_ERROR.getCode(),objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultVo<String> handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        return new ResultVo(true, CodeEnumEntity.SYSTEM_ERROR.getCode(),e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResultVo<String> handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e);
        return new ResultVo(true, CodeEnumEntity.SYSTEM_ERROR.getCode(),e.getMessage());
    }


}
