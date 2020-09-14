package cn.logcode.xhufiveface.utils;

/**
 * @package cn.logcode.demo.utils
 * @ClassName ImageTypeUtils
 * @Author caost
 * @Date 2020/4/15 21:16
 * @Emall caostgrace@163.com
 * @DESC
 */


import cn.logcode.xhufiveface.model.FileType;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取文件格式
 */
public class ImageTypeUtils {

    /**
     * 根据文件流获取文件类型
     * @param is
     * @return
     * @throws IOException
     */
    public static FileType getFileType(InputStream is) throws IOException {
        byte[] src = new byte[28];
        is.read(src, 0, 28);
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
            if (stringBuilder.toString().startsWith(fileType.getValue())) {
                return fileType;
            }
        }
        return null;
    }

    public static FileType readType(String fullFilePath) throws IOException {
        URL url = new URL(fullFilePath);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        httpUrl.connect();
        InputStream inputStream = httpUrl.getInputStream();
        FileType fileType = getFileType(inputStream);
        return fileType;
    }
}