package cn.logcode.xhufiveface.utils;


import cn.logcode.xhufiveface.AppConstant;
import cn.logcode.xhufiveface.config.GsonMessageConfig;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.dto.AccessTokenBean;
import cn.logcode.xhufiveface.model.vo.LoginAuthBean;
import com.baidu.aip.face.AipFace;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@WebListener
public class ApplicationTool implements ApplicationContextAware, ServletContextListener {

    public static final Log logger = LogFactory.getLog(ApplicationTool.class);

    private static AipFace aipFace = null;

    /**
     * 上下文对象实例
     */
    public ApplicationContext applicationContext;

    public ServletContext servletContext;

    public static String httpPath = "";
    public static String contextPath = "";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取servletContext
     *
     * @return
     */
    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name, Class<T> clazz) {
        Assert.hasText(name, "name为空");
        return getApplicationContext().getBean(name, clazz);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        contextPath = servletContext.getContextPath();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public static AipFace getBaiduAiFaceInstance(){
        if(aipFace == null){
            synchronized (ApplicationTool.class){
                if(aipFace == null){
                    // 初始化一个AipFace
                    aipFace = new AipFace(AppConstant.APP_ID, AppConstant.API_KEY, AppConstant.SECRET_KEY);
                    // 可选：设置网络连接参数
                    aipFace.setConnectionTimeoutInMillis(2000);
                    aipFace.setSocketTimeoutInMillis(60000);
                }
            }
        }
        return aipFace;
    }


    public static Map<String, Object> mapOf(String k, Object v) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(k, v);
        return map;
    }

    /**
     * 将map 转化为xml
     */
    public static byte[] map2XML(Map map, String root) {
        StringBuffer sb = new StringBuffer();
        sb.append("<" + root + ">");
        map2XML(map, sb);
        sb.append("</" + root + ">");
        try {

            // logger.info("mapToXml===>"+sb.toString());

            return sb.toString().getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void map2XML(Map map, StringBuffer sb) {

        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value instanceof List) {
                List list = (List) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    Map hm = (Map) list.get(i);
                    map2XML(hm, sb);
                }
                sb.append("</" + key + ">");

            } else if (value instanceof Set) {
                Set list = (Set) map.get(key);
                sb.append("<" + key + ">");
                for (Iterator it1 = list.iterator(); it1.hasNext(); ) {
                    Map hm = (Map) it1.next();
                    map2XML(hm, sb);
                }
                sb.append("</" + key + ">");
            } else {
                if (value instanceof Map) {
                    sb.append("<" + key + ">");
                    map2XML((Map) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + ">" + value.toString() + "</" + key + ">");
                }

            }

        }
    }




    /**
     * 把用户头像下载在本地，用于微信用户第一次登陆的时候下载头像
     *
     * @param url
     * @return
     */
    public static String weChatHeadToLocal(String url) {


        return url;
    }

    /**
     * 创建动态id
     *
     * @return
     */
    public static String createDynamicId(int userId) {
        return randNumStr(6) + userId + System.currentTimeMillis();
    }

    public static boolean regex(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 随机生成数字字符串
     *
     * @param num
     * @return
     */
    public static String randNumStr(int num) {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            int tmp = rand.nextInt(9);
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static String getCurrentDateYYYYMMDD() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 创建登陆授权令牌
     *
     * @param user
     * @return
     */
    public static LoginAuthBean createLoginToken(FaceUser user) {
        int userId = user.getUserId();
        //当前时间戳
        long currentTime = System.currentTimeMillis();
        //时效两小时
        long timeDuration = 1 * 1000 * 60 * 60 * 2;
        //结束时间戳
        long expiresTime = currentTime + timeDuration;

        AccessTokenBean accessTokenBean = new AccessTokenBean();
        accessTokenBean.setUserId(user.getUserId());
        accessTokenBean.setCreateTime(user.getCreateTime());
        accessTokenBean.setUserNick(user.getUserNick());
        accessTokenBean.setUserPhone(user.getUserPhone());
        accessTokenBean.setUserSex(user.getUserSex());
        accessTokenBean.setUserEmail(user.getUserEmail());
        accessTokenBean.setUserPwd(user.getUserPwd());
        accessTokenBean.setUserReal(user.getUserReal());
        accessTokenBean.setTimeDuration(timeDuration);
        accessTokenBean.setCurrentTime(currentTime);
        accessTokenBean.setExpiresTime(expiresTime);

        String token = AESUtils.encrypt(GsonMessageConfig.DEFAULT_GSON.toJson(accessTokenBean), AESUtils.key, AESUtils.iv);

        LoginAuthBean authBean = new LoginAuthBean();
        authBean.setAccess_token(token);
        authBean.setExpires_in(expiresTime + "");
        return authBean;
    }

    /**
     * 获取客户端的请求ip
     *
     * @return
     */
    public static String getClientRequestIp() {

        HttpServletRequest request = getRequest();

        String ip = request.getHeader("x-forwarded-for");
        // System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            // System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            // System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            // System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            // System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            // System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            // System.out.println("getRemoteAddr ip: " + ip);
        }
        // System.out.println("获取客户端ip: " + ip);
        return ip;
    }

    /**
     * 获取当前请求
     *
     * @return
     */
    public synchronized static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    public static String stampDate(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

        return simpleDateFormat.format(date);
    }

}