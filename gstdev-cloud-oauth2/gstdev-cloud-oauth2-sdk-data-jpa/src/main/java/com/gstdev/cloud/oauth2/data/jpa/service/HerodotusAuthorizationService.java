package com.gstdev.cloud.oauth2.data.jpa.service;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.service.BaseService;
import com.gstdev.cloud.oauth2.data.jpa.entity.HerodotusAuthorization;
import com.gstdev.cloud.oauth2.data.jpa.repository.HerodotusAuthorizationRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class HerodotusAuthorizationService extends BaseService<HerodotusAuthorization, String> {

    private static final Logger log = LoggerFactory.getLogger(HerodotusAuthorizationService.class);

    private final HerodotusAuthorizationRepository herodotusAuthorizationRepository;

    @Autowired
    public HerodotusAuthorizationService(HerodotusAuthorizationRepository herodotusAuthorizationRepository) {
        this.herodotusAuthorizationRepository = herodotusAuthorizationRepository;
    }

    @Override
    public BaseRepository<HerodotusAuthorization, String> getRepository() {
        return this.herodotusAuthorizationRepository;
    }

    public Optional<HerodotusAuthorization> findByState(String state) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByState(state);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByState.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByAuthorizationCode(String authorizationCode) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByAuthorizationCodeValue(authorizationCode);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByAuthorizationCode.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByAccessToken(String accessToken) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByAccessTokenValue(accessToken);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByAccessToken.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByRefreshToken(String refreshToken) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByRefreshTokenValue(refreshToken);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByRefreshToken.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByOidcIdTokenValue(String idToken) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByOidcIdTokenValue(idToken);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByOidcIdTokenValue.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByUserCodeValue(String userCode) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByUserCodeValue(userCode);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByUserCodeValue.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByDeviceCodeValue(String deviceCode) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByDeviceCodeValue(deviceCode);
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findByDeviceCodeValue.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token) {

        Specification<HerodotusAuthorization> specification = (root, criteriaQuery, criteriaBuilder) -> {
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

        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findOne(specification);
        log.trace("[GstDev Cloud] |- HerodotusAuthorization Service findByDetection.");
        return result;
    }

    public void clearHistoryToken() {
        this.herodotusAuthorizationRepository.deleteByRefreshTokenExpiresAtBefore(LocalDateTime.now());
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service clearExpireAccessToken.");
    }

    public List<HerodotusAuthorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<HerodotusAuthorization> authorizations = this.herodotusAuthorizationRepository.findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(registeredClientId, principalName, LocalDateTime.now());
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service findAvailableAuthorizations.");
        return authorizations;
    }

    public int findAuthorizationCount(String registeredClientId, String principalName) {
        List<HerodotusAuthorization> authorizations = findAvailableAuthorizations(registeredClientId, principalName);
        int count = 0;
        if (CollectionUtils.isNotEmpty(authorizations)) {
            count = authorizations.size();
        }
        log.debug("[GstDev Cloud] |- HerodotusAuthorization Service current authorization count is [{}].", count);
        return count;
    }
}
