package com.gstdev.cloud.service.identity.service;

import com.gstdev.cloud.base.definition.domain.oauth2.SecretKey;
import com.gstdev.cloud.oauth2.core.utils.SecurityUtils;
import com.gstdev.cloud.rest.protect.crypto.processor.HttpCryptoProcessor;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description: 请求加密服务 </p>
 *
 * @author : cc
 * @date : 2021/9/30 18:08
 */
//@Service
public class InterfaceSecurityService {

    @Resource
    private HttpCryptoProcessor httpCryptoProcessor;
    @Resource
    private RegisteredClientRepository registeredClientRepository;

//    public InterfaceSecurityService(HttpCryptoProcessor httpCryptoProcessor, RegisteredClientRepository registeredClientRepository) {
//        this.httpCryptoProcessor = httpCryptoProcessor;
//        this.registeredClientRepository = registeredClientRepository;
//    }

    /**
     * 检查终端是否是合法终端
     *
     * @param clientId     OAuth2 终端ID
     * @param clientSecret OAuth2 终端密码
     */
    private RegisteredClient validateClient(String clientId, String clientSecret) {
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);

        boolean isMatch = false;
        if (ObjectUtils.isNotEmpty(registeredClient)) {
            isMatch = SecurityUtils.matches(clientSecret, registeredClient.getClientSecret());
        }

        if (!isMatch) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
        }

        return registeredClient;
    }

    public SecretKey createSecretKey(String clientId, String clientSecret, String sessionId) {
        // 检测终端是否是有效终端
        RegisteredClient registeredClient = this.validateClient(clientId, clientSecret);
        return httpCryptoProcessor.createSecretKey(sessionId, registeredClient.getTokenSettings().getAccessTokenTimeToLive());
    }

    /**
     * 前端用后端PublicKey加密前端PublicKey后，将该值传递给后端，用于加密 AES KEY
     *
     * @param sessionId          Session 标识
     * @param confidentialBase64 前端用后端PublicKey加密前端PublicKey。前端使用node-rsa加密后的数据是base64编码
     * @return 前端RSA PublicKey 加密后的 AES Key
     */
    public String exchange(String sessionId, String confidentialBase64) {
        return httpCryptoProcessor.exchange(sessionId, confidentialBase64);
    }
}
