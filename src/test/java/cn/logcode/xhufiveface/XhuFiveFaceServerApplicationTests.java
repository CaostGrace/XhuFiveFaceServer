package cn.logcode.xhufiveface;

import cn.logcode.xhufiveface.service.core.FaceService;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@SpringBootTest(classes = XhuFiveFaceServerApplication.class)
@RunWith(SpringRunner.class)
class XhuFiveFaceServerApplicationTests {

    @Autowired
    FaceService faceService;




    @Test
    void contextLoads() {
        faceService.faceVerification(1000,5);
    }


}
