package cn.logcode.xhufiveface.config;

import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.result.ResultCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    public Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BaseException.class)
    public CommonResult<Object> defaultErrorHandler(BaseException ex) throws Exception {
        logger.info(ex.getMsg());
        return CommonResult.failed(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult<Object> defaultErrorHandler(Exception ex) throws Exception {
        logger.error(ex);
        String msg = null;
        if (ex instanceof MethodArgumentNotValidException) {
            msg = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage();
        }
        if (ex instanceof BindException) {
            msg = ((BindException) ex).getBindingResult().getFieldError().getDefaultMessage();
        }

        if(ex instanceof MissingServletRequestParameterException){
            msg = ((MissingServletRequestParameterException) ex).getMessage();
        }

        if(ex instanceof HttpRequestMethodNotSupportedException){
            msg = ((HttpRequestMethodNotSupportedException) ex).getMethod();
        }

        return msg == null ? CommonResult.failed(ResultCode.SERVER_ERROR) : CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), msg);
    }
}
