package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.entity.FrameAuthorization;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameAuthorizationConsentRepository;
import com.gstdev.cloud.oauth2.data.jpa.repository.FrameAuthorizationRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>Description: FrameAuthorizationService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 *
 * @author : cc
 * @date : 2022/2/25 21:06
 */
@Service
public class FrameAuthorizationService extends BaseServiceImpl<FrameAuthorization, String> {

    private static final Logger log = LoggerFactory.getLogger(FrameAuthorizationService.class);


    private FrameAuthorizationRepository frameAuthorizationRepository;
    @Autowired
    public FrameAuthorizationService(FrameAuthorizationRepository frameAuthorizationRepository) {
        super(frameAuthorizationRepository);
        this.frameAuthorizationRepository=frameAuthorizationRepository;
    }
    public FrameAuthorizationRepository getRepository() {
        return this.frameAuthorizationRepository;
    }

    public Optional<FrameAuthorization> findByState(String state) {
        Optional<FrameAuthorization> result = getRepository().findByState(state);
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findByState.");
        return result;
    }

    public Optional<FrameAuthorization> findByAuthorizationCode(String authorizationCode) {
        Optional<FrameAuthorization> result = getRepository().findByAuthorizationCodeValue(authorizationCode);
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findByAuthorizationCode.");
        return result;
    }

    public Optional<FrameAuthorization> findByAccessToken(String accessToken) {
        Optional<FrameAuthorization> result = getRepository().findByAccessTokenValue(accessToken);
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findByAccessToken.");
        return result;
    }

    public Optional<FrameAuthorization> findByRefreshToken(String refreshToken) {
        Optional<FrameAuthorization> result = getRepository().findByRefreshTokenValue(refreshToken);
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findByRefreshToken.");
        return result;
    }

    public Optional<FrameAuthorization> findByOidcIdTokenValue(String idToken) {
        Optional<FrameAuthorization> result = getRepository().findByOidcIdTokenValue(idToken);
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findByOidcIdTokenValue.");
        return result;
    }

    public Optional<FrameAuthorization> findByUserCodeValue(String userCode) {
        Optional<FrameAuthorization> result = getRepository().findByUserCodeValue(userCode);
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findByUserCodeValue.");
        return result;
    }

    public Optional<FrameAuthorization> findByDeviceCodeValue(String deviceCode) {
        Optional<FrameAuthorization> result = getRepository().findByDeviceCodeValue(deviceCode);
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findByDeviceCodeValue.");
        return result;
    }

    public Optional<FrameAuthorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token) {

        Specification<FrameAuthorization> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("state"), token));
            predicates.add(criteriaBuilder.equal(root.get("authorizationCodeValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("accessTokenValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("refreshTokenValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("oidcIdTokenValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("userCodeValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("deviceCodeValue"), token));

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(predicateArray)));
            return criteriaQuery.getRestriction();
        };

        Optional<FrameAuthorization> result = getRepository().findOne(specification);
        log.trace("[GstDev Cloud] |- GstDevAuthorization Service findByDetection.");
        return result;
    }

    public void clearHistoryToken() {
        getRepository().deleteByRefreshTokenExpiresAtBefore(Instant.now());
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service clearExpireAccessToken.");
    }

    public List<FrameAuthorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<FrameAuthorization> authorizations = getRepository().findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(registeredClientId, principalName, Instant.now());
        log.debug("[GstDev Cloud] |- GstDevAuthorization Service findAvailableAuthorizations.");
        return authorizations;
    }

    public int findAuthorizationCount(String registeredClientId, String principalName) {
        List<FrameAuthorization> authorizations = findAvailableAuthorizations(registeredClientId, principalName);
        int count = 0;
        if (CollectionUtils.isNotEmpty(authorizations)) {
            count = authorizations.size();
        }
        log.debug("[GstDev Cloud] |- FrameAuthorization Service current authorization count is [{}].", count);
        return count;
    }
}
