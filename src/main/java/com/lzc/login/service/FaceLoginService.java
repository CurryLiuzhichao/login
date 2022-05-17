package com.lzc.login.service;

public interface FaceLoginService {
    /**
     * 人脸注册
     * @param userid
     * @param imagebast64
     * @return
     */
    String registerFace(String userid, StringBuffer imagebast64);
}
