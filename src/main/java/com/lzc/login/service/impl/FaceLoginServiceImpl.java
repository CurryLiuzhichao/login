package com.lzc.login.service.impl;

import com.lzc.login.service.FaceLoginService;
import com.lzc.login.util.BaiduAiUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class FaceLoginServiceImpl implements FaceLoginService {

    @Autowired
    private BaiduAiUtils baiduAiUtils;

    /**
     * 人脸注册
     * @param userid
     * @param imagebast64
     * @return
     */
    @Override
    public String registerFace(String userid, StringBuffer imagebast64) {
        //处理图像格式
        String image = imagebast64.substring(imagebast64.indexOf(",") + 1, imagebast64.length());
        Boolean aBoolean = baiduAiUtils.faceRegister(userid, image);
        if (aBoolean){
            return "面部注册成功，可以人脸登录啦！";
        }else {
            return "面部注册失败,请重试！";
        }
    }
}
