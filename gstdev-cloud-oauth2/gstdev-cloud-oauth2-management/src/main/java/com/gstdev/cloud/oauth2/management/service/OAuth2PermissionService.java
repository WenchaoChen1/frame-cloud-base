package com.gstdev.cloud.oauth2.management.service;

import com.gstdev.cloud.data.core.repository.BaseRepository;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.oauth2.management.entity.OAuth2Permission;
import com.gstdev.cloud.oauth2.management.repository.OAuth2PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: OAuth2PermissionService </p>
 *
 * @author : cc
 * @date : 2022/4/1 13:53
 */
@Service
public class OAuth2PermissionService extends BaseServiceImpl<OAuth2Permission, String,OAuth2PermissionRepository> {

  private  OAuth2PermissionRepository authorityRepository;

  public OAuth2PermissionService(OAuth2PermissionRepository authorityRepository) {
      super(authorityRepository);
  }


    @Override
    public OAuth2Permission findById(String s) {
        return null;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long count(Specification<OAuth2Permission> specification) {
        return 0;
    }

    @Override
    public List<OAuth2Permission> findAll() {
        return null;
    }

    @Override
    public List<OAuth2Permission> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<OAuth2Permission> findAll(Specification<OAuth2Permission> specification) {
        return null;
    }

    @Override
    public List<OAuth2Permission> findAll(Specification<OAuth2Permission> specification, Sort sort) {
        return null;
    }

    @Override
    public Page<OAuth2Permission> findByPage(Pageable pageable) {
        return null;
    }

    @Override
    public Page<OAuth2Permission> findByPage(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Page<OAuth2Permission> findByPage(int pageNumber, int pageSize, Sort sort) {
        return null;
    }

    @Override
    public Page<OAuth2Permission> findByPage(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        return null;
    }

    @Override
    public Page<OAuth2Permission> findByPage(Specification<OAuth2Permission> specification, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OAuth2Permission> findByPage(Specification<OAuth2Permission> specification, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Page<OAuth2Permission> findByPage(int pageNumber, int pageSize, Sort.Direction direction) {
        return null;
    }

    @Override
    public void delete(OAuth2Permission entity) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public void deleteAll(Iterable<OAuth2Permission> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public OAuth2Permission save(OAuth2Permission domain) {
        return null;
    }

    @Override
    public <S extends OAuth2Permission> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public OAuth2Permission saveAndFlush(OAuth2Permission entity) {
        return null;
    }

    @Override
    public List<OAuth2Permission> saveAllAndFlush(List<OAuth2Permission> entities) {
        return null;
    }

    @Override
    public void flush() {

    }
}
