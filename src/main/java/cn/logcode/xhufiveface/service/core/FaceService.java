package cn.logcode.xhufiveface.service.core;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.FaceDao;
import cn.logcode.xhufiveface.dao.UserDao;
import cn.logcode.xhufiveface.dao.pojo.*;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.utils.Imagebase64;
import com.baidu.aip.face.AipFace;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

@Service
public class FaceService {
    public final Log logger = LogFactory.getLog(FaceService.class);

    @Autowired
    FaceDao faceDao;

    @Autowired
    AipFace client;

    @Autowired
    CoreStorageService storageService;

    @Autowired
    UserDao userDao;

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean addGroup(String groupName){
        if (faceDao.addGroup(groupName)) {
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            // 创建用户组
            JSONObject res = client.groupAdd(groupName, options);
            if(res.getInt("error_code") == 0){
                return true;
            }
        }
        throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean deleteGroup(int groupId){
        FaceGroup group = faceDao.getGroupById(groupId);
        if(group != null){
            if (faceDao.deleteGroupById(groupId) && faceDao.deleteFaceLibraryByGroupId(group.getId())) {
                // 传入可选参数调用接口
                HashMap<String, String> options = new HashMap<String, String>();
                // 删除用户组
                JSONObject res = client.groupDelete(group.getGroupName(), options);
                if(res.getInt("error_code") == 0){
                    return true;
                }
            }
        }
        throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR);

    }


    /**
     * 人脸认证
     * @param userId 用户id
     * @param fileId 上传的照片id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean faceVerification(int userId,int fileId){
        FaceUser user = userDao.getUserById(userId);
        FaceGroup group = faceDao.getGroupById(user.getGroupId());
        FaceStorage storage = storageService.findById(fileId);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "NORMAL");
        options.put("user_id", userId+"");

        String image = Imagebase64.imageToBase64(storage.getLocalpath());
        String imageType = "BASE64";

        String groupIdList = group.getGroupName();
        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupIdList, options);
        if(res.getInt("error_code") != 0){
            throw new BaseException(ResultCode.FAILED.code,res.getString("error_msg"));
        }
        JSONArray array = res.getJSONObject("result").getJSONArray("user_list");
        if(array.length() == 0){
            throw new BaseException(ResultCode.FAILED.code,"认证失败");
        }
        if (Integer.parseInt(array.getJSONObject(0).getString("user_id")) == userId) {
            return true;
        }
        return false;

    }


    /**
     * 删除百度人脸库用户信息
     * @param userId
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean deleteFaceUser(int userId){
        if(faceDao.deleteFaceLibraryByUserId(userId)){
            FaceUser user = userDao.getUserById(userId);
            FaceGroup group = faceDao.getGroupById(user.getGroupId());
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            // 删除用户
            JSONObject res = client.deleteUser(group.getGroupName(), userId+"", options);

            if(res.getInt("error_code") != 0){
                throw new BaseException(ResultCode.FAILED.code,res.getString("error_msg"));
            }
            return true;

        }
        return false;
    }


    /**
     * 更新用户的人脸数据
     * @param userId
     * @param fileId
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean updateUserFaceData(int userId, int fileId){
        if(faceDao.deleteFaceLibraryByUserId(userId)){
            FaceStorage storage = storageService.findById(fileId);
            FaceUser user = userDao.getUserById(userId);
            faceDetect(storage.getLocalpath(),false);

            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            options.put("user_info", user.getUserNick());
            FaceGroup group = faceDao.getGroupById(user.getGroupId());

            String image = Imagebase64.imageToBase64(storage.getLocalpath());
            String imageType = "BASE64";
            String groupId = group.getGroupName();

            // 人脸注册
            JSONObject res = client.updateUser(image, imageType, groupId, userId+"", options);

            if(res.getInt("error_code") != 0){
                throw new BaseException(ResultCode.FAILED.code,res.getString("error_msg"));
            }
            FaceLibrary faceLibrary = new FaceLibrary();
            faceLibrary.setCreateTime(new Date());
            faceLibrary.setFaceToken(res.getJSONObject("result").getString("face_token"));
            faceLibrary.setFileId(storage.getId());
            faceLibrary.setGroupId(group.getId());
            faceLibrary.setUserId(user.getUserId());

            if (!faceDao.addFaceLibrary(faceLibrary)) {
                throw new BaseException(ResultCode.SERVER_ERROR);
            }
            return true;
        }
        return false;
    }

    /**
     * 删除用户的一张人脸信息
     * @param userId
     * @param groupId
     * @param faceToken
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean faceDelete(int userId,int groupId,String faceToken){
        if (faceDao.deleteFaceLibraryByFaceToken(faceToken)) {
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            FaceGroup group = faceDao.getGroupById(groupId);
            // 人脸删除
            JSONObject res = client.faceDelete(userId+"", group.getGroupName(), faceToken, options);
            if(res.getInt("error_code") != 0){
                throw new BaseException(ResultCode.FAILED.code,res.getString("error_msg"));
            }
            return true;
        }
        return false;
    }

    /**
     * 用户人脸数据注册或增加
     * @param userId
     * @param fileId
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean userFaceRegisterOrAdd(int userId, int fileId){

        FaceStorage storage = storageService.findById(fileId);
        FaceUser user = userDao.getUserById(userId);
        faceDetect(storage.getLocalpath(),false);

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("user_info", user.getUserNick());
        FaceGroup group = faceDao.getGroupById(user.getGroupId());

        String image = Imagebase64.imageToBase64(storage.getLocalpath());
        String imageType = "BASE64";
        String groupId = group.getGroupName();

        // 人脸注册
        JSONObject res = client.addUser(image, imageType, groupId, userId+"", options);

        if(res.getInt("error_code") != 0){
            throw new BaseException(ResultCode.FAILED.code,res.getString("error_msg"));
        }
        FaceLibrary faceLibrary = new FaceLibrary();
        faceLibrary.setCreateTime(new Date());
        faceLibrary.setFaceToken(res.getJSONObject("result").getString("face_token"));
        faceLibrary.setFileId(storage.getId());
        faceLibrary.setGroupId(group.getId());
        faceLibrary.setUserId(user.getUserId());

        if (!faceDao.addFaceLibrary(faceLibrary)) {
            throw new BaseException(ResultCode.SERVER_ERROR);
        }
        return true;
    }



    /**
     * 人脸质量检测
     * @param imgPath
     * @return
     */
    public boolean faceDetect(String imgPath,boolean isUrl){

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,quality,eye_status,emotion,face_type");
        options.put("liveness_control", "NORMAL");
        String image;
        String imageType;
        if(isUrl){
            image = imgPath;
            imageType = "URL";
        }else {
            image = Imagebase64.imageToBase64(imgPath);
            imageType = "BASE64";
        }
        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        logger.info(res.toString());
        return faceQualityInspection(res);
    }
    /**
     * 照片质量检测
     * @param json
     * @return
     */
    private boolean faceQualityInspection(JSONObject json){
        if(json.getInt("error_code") != 0) {
            throw new BaseException(ResultCode.FAILED.code,"人脸质量检测失败");
        }

        JSONArray array = json.getJSONObject("result").getJSONArray("face_list");

        JSONObject quality = array.getJSONObject(0).getJSONObject("quality");

        if (quality.getInt("illumination")<40) {
            throw new BaseException(ResultCode.FAILED.code,"人脸质量检测失败");
        }

        if (quality.getDouble("blur") > 0.7) {
            throw new BaseException(ResultCode.FAILED.code,"人脸质量检测失败");
        }

        JSONObject occlusion = quality.getJSONObject("occlusion");
        if (occlusion.getDouble("left_eye") > 0.6 ||
                occlusion.getDouble("right_eye") > 0.6 ||
                occlusion.getDouble("nose") > 0.7 ||
                occlusion.getDouble("mouth") > 0.7 ||
                occlusion.getDouble("left_cheek") > 0.8 ||
                occlusion.getDouble("right_cheek") > 0.8 ||
                occlusion.getDouble("chin_contour") > 0.6) {
            throw new BaseException(ResultCode.FAILED.code,"人脸质量检测失败");
        }

        JSONObject angle = array.getJSONObject(0).getJSONObject("angle");

        if (angle.getDouble("roll") > 20 ||
                angle.getDouble("pitch") > 20 ||
                angle.getDouble("yaw") > 20) {
            throw new BaseException(ResultCode.FAILED.code,"人脸质量检测失败");
        }
        return true;
    }





}
