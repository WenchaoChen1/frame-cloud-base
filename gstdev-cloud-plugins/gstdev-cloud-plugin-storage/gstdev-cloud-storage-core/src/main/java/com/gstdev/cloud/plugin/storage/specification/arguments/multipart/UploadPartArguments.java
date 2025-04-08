// ====================================================
//
// This file is part of the GstDev Cloud Platform.
//
// Create by GstDev Tech <support@gstdev.com>
// Copyright (c) 2020-2025 gstdev.com
//
// ====================================================

package com.gstdev.cloud.plugin.storage.specification.arguments.multipart;

import com.google.common.base.MoreObjects;
import com.gstdev.cloud.plugin.storage.specification.arguments.base.BasePartArguments;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.io.InputStream;

/**
 * <p>Description: 上传分片请求参数实体 </p>
 *
 * @author : cc
 * @date : 2023/8/13 15:44
 */
@Schema(name = "上传分片请求参数实体", title = "上传分片请求参数实体")
public class UploadPartArguments extends BasePartArguments {

    /**
     * 描述该分片相对于分片上传中其他分片的位置的分片号。分片号必须介于1和10000之间（包括1和10000）。
     */
    @Schema(name = "分片编号", description = "当前分片在所有分片中的编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "分片变化不能小于1")
    @Max(value = 10000, message = "分片变化不能大于10000")
    private int partNumber;

    /**
     * 此分片的大小，以字节为单位
     */
    @Schema(name = "分片数据大小", description = "单位字节", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive(message = "分片大小不能为 O")
    private Long partSize;

    /**
     * 包含要为分片上载的数据的流。必须仅指定一个 File 或 InputStream 作为此操作的输入。
     */
    @Schema(name = "文件输入流", requiredMode = Schema.RequiredMode.REQUIRED)
    private InputStream inputStream;

    /**
     * 本部分内容的可选但推荐的MD5哈希。如果指定，当数据到达AmazonS3时，该值将被发送到AmazonS3以验证数据的完整性。
     */
    @Schema(name = "文件输入流")
    private String md5Digest;

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public Long getPartSize() {
        return partSize;
    }

    public void setPartSize(Long partSize) {
        this.partSize = partSize;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMd5Digest() {
        return md5Digest;
    }

    public void setMd5Digest(String md5Digest) {
        this.md5Digest = md5Digest;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("partNumber", partNumber)
                .add("partSize", partSize)
                .add("md5Digest", md5Digest)
                .toString();
    }
}
