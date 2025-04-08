// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.core.constants;


import com.gstdev.cloud.base.definition.feedback.InternalServerErrorFeedback;
import com.gstdev.cloud.base.definition.feedback.ServiceUnavailableFeedback;

/**
 * <p>Description: 对象存储错误代码 </p>
 *
 * @author : cc
 * @date : 2022/6/30 13:15
 */
public interface OssErrorCodes {

    InternalServerErrorFeedback OSS_CLIENT_POOL_ERROR = new InternalServerErrorFeedback("无法从Oss对象池中获取对象");
    InternalServerErrorFeedback OSS_ERROR_RESPONSE = new InternalServerErrorFeedback("对象存储服务器返回错误响应");
    InternalServerErrorFeedback OSS_INSUFFICIENT_DATA = new InternalServerErrorFeedback("对象存储服务器返回数据不足");
    InternalServerErrorFeedback OSS_INTERNAL = new InternalServerErrorFeedback("对象存储服务器内部错误");
    InternalServerErrorFeedback OSS_INVALID_KEY = new InternalServerErrorFeedback("对象存储使用无效的秘钥");
    InternalServerErrorFeedback OSS_INVALID_RESPONSE = new InternalServerErrorFeedback("对象存储返回无效的响应");
    InternalServerErrorFeedback OSS_IO = new InternalServerErrorFeedback("对象存储出现IO错误");
    InternalServerErrorFeedback OSS_NO_SUCH_ALGORITHM = new InternalServerErrorFeedback("使用对象存储不支持算法错误");
    InternalServerErrorFeedback OSS_SERVER = new InternalServerErrorFeedback("对象存储服务器出现错误");
    InternalServerErrorFeedback OSS_XML_PARSER = new InternalServerErrorFeedback("对象存储 XML 解析出现错误");
    InternalServerErrorFeedback OSS_EXECUTION = new InternalServerErrorFeedback("对象存储服务器异步执行错误");
    InternalServerErrorFeedback OSS_INTERRUPTED = new InternalServerErrorFeedback("对象存储服务器异步执行中断错误");
    InternalServerErrorFeedback OSS_BUCKET_POLICY_TOO_LARGE = new InternalServerErrorFeedback("存储桶访问策略过大");
    InternalServerErrorFeedback OSS_INVALID_CIPHER_TEXT = new InternalServerErrorFeedback("无效密码文本错误");

    ServiceUnavailableFeedback OSS_CONNECTION = new ServiceUnavailableFeedback("Minio 服务器无法访问或未启动");
}
