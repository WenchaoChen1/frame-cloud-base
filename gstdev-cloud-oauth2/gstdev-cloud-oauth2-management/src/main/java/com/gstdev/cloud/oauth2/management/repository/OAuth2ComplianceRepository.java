package com.gstdev.cloud.oauth2.management.repository;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Compliance;
import org.springframework.stereotype.Repository;

/**
 * <p>Description: ActionAuditRepository </p>
 *
 * @author : cc
 * @date : 2022/7/7 20:39
 */
@Repository
public interface OAuth2ComplianceRepository extends BaseRepository<OAuth2Compliance, String> {
}
