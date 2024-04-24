package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.data.jpa.entity.DefaultOauth2Authorization;
import com.gstdev.cloud.oauth2.data.jpa.repository.HerodotusAuthorizationRepository;
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
 * <p>Description: HerodotusAuthorizationService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 *
 * @author : cc
 * @date : 2022/2/25 21:06
 */
@Service
public class DefaultOauth2AuthorizationService extends BaseServiceImpl<DefaultOauth2Authorization, String, HerodotusAuthorizationRepository> {

    private static final Logger log = LoggerFactory.getLogger(DefaultOauth2AuthorizationService.class);


    @Autowired
    public DefaultOauth2AuthorizationService(HerodotusAuthorizationRepository herodotusAuthorizationRepository) {
        super(herodotusAuthorizationRepository);
    }


    public Optional<DefaultOauth2Authorization> findByState(String state) {
        Optional<DefaultOauth2Authorization> result = getRepository().findByState(state);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByState.");
        return result;
    }

    public Optional<DefaultOauth2Authorization> findByAuthorizationCode(String authorizationCode) {
        Optional<DefaultOauth2Authorization> result = getRepository().findByAuthorizationCodeValue(authorizationCode);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByAuthorizationCode.");
        return result;
    }

    public Optional<DefaultOauth2Authorization> findByAccessToken(String accessToken) {
        Optional<DefaultOauth2Authorization> result = getRepository().findByAccessTokenValue(accessToken);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByAccessToken.");
        return result;
    }

    public Optional<DefaultOauth2Authorization> findByRefreshToken(String refreshToken) {
        Optional<DefaultOauth2Authorization> result = getRepository().findByRefreshTokenValue(refreshToken);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByRefreshToken.");
        return result;
    }

    public Optional<DefaultOauth2Authorization> findByOidcIdTokenValue(String idToken) {
        Optional<DefaultOauth2Authorization> result = getRepository().findByOidcIdTokenValue(idToken);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByOidcIdTokenValue.");
        return result;
    }

    public Optional<DefaultOauth2Authorization> findByUserCodeValue(String userCode) {
        Optional<DefaultOauth2Authorization> result = getRepository().findByUserCodeValue(userCode);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByUserCodeValue.");
        return result;
    }

    public Optional<DefaultOauth2Authorization> findByDeviceCodeValue(String deviceCode) {
        Optional<DefaultOauth2Authorization> result = getRepository().findByDeviceCodeValue(deviceCode);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByDeviceCodeValue.");
        return result;
    }

    public Optional<DefaultOauth2Authorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token) {

        Specification<DefaultOauth2Authorization> specification = (root, criteriaQuery, criteriaBuilder) -> {
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

        Optional<DefaultOauth2Authorization> result = getRepository().findOne(specification);
        log.trace("[GstDev Cloud] |- HerodotusAuthorization Service findByDetection.");
        return result;
    }

    public void clearHistoryToken() {
        getRepository().deleteByRefreshTokenExpiresAtBefore(Instant.now());
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service clearExpireAccessToken.");
    }

    public List<DefaultOauth2Authorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<DefaultOauth2Authorization> authorizations = getRepository().findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(registeredClientId, principalName,Instant.now());
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findAvailableAuthorizations.");
        return authorizations;
    }

    public int findAuthorizationCount(String registeredClientId, String principalName) {
        List<DefaultOauth2Authorization> authorizations = findAvailableAuthorizations(registeredClientId, principalName);
        int count = 0;
        if (CollectionUtils.isNotEmpty(authorizations)) {
            count = authorizations.size();
        }
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service current authorization count is [{}].", count);
        return count;
    }
}
