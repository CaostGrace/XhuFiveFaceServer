package cn.logcode.xhufiveface.annotation.support;


import cn.logcode.xhufiveface.AppConstant;
import cn.logcode.xhufiveface.annotation.CurrentUser;
import cn.logcode.xhufiveface.dao.UserDao;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Autowired
    UserDao userDao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(FaceUser.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        Integer userId = (Integer) request.getAttribute(AppConstant.LOGIN_USER_ID, NativeWebRequest.SCOPE_REQUEST);
        return userDao.getUserById(userId);
    }
}
