package com.gstdev.cloud.rest.core.controller;

import com.gstdev.cloud.rest.core.annotation.AccessLimited;
import com.gstdev.cloud.rest.core.definition.dto.Pager;
import com.gstdev.cloud.commons.ass.definition.constants.BaseConstants;
import com.gstdev.cloud.commons.ass.definition.domain.Result;
import com.gstdev.cloud.commons.ass.definition.domain.base.AbstractEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>Description: 只读RestController </p>
 *
 * @author : cc
 * @date : 2021/7/5 17:21
 */
@SecurityRequirement(name = BaseConstants.OPEN_API_SECURITY_SCHEME_BEARER_NAME)
public abstract class BaseReadableRestController<E extends AbstractEntity, ID extends Serializable> implements ReadableController<E, ID> {

  @AccessLimited
  @Operation(summary = "分页查询数据", description = "通过pageNumber和pageSize获取分页数据",
    responses = {
      @ApiResponse(description = "单位列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
      @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
      @ApiResponse(responseCode = "500", description = "查询失败")
    })
  @Parameters({
    @Parameter(name = "pager", required = true, in = ParameterIn.QUERY, description = "分页Bo对象", schema = @Schema(implementation = Pager.class))
  })
  @GetMapping
  public Result<Map<String, Object>> findByPage(@Validated Pager pager) {
    if (ArrayUtils.isNotEmpty(pager.getProperties())) {
      Sort.Direction direction = Sort.Direction.valueOf(pager.getDirection());
      return ReadableController.super.findByPage(pager.getPageNumber(), pager.getPageSize(), direction, pager.getProperties());
    } else {
      return ReadableController.super.findByPage(pager.getPageNumber(), pager.getPageSize());
    }
  }
}
