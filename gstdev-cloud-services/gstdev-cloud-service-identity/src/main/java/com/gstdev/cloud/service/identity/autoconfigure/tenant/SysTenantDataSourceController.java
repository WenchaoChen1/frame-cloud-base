//package com.gstdev.cloud.oauth2.authorization.server.autoconfigure.tenant;
//
//import com.gstdev.cloud.assistant.definition.domain.Result;
//import com.gstdev.cloud.data.core.service.WriteableService;
//import com.gstdev.cloud.data.tenant.entity.SysTenantDataSource;
//import com.gstdev.cloud.rest.core.annotation.AccessLimited;
//import com.gstdev.cloud.rest.core.controller.BaseWriteableRestController;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.Parameters;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.tags.Tags;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p>Description: 多租户数据源接口 </p>
// *
// * @author : cc
// * @date : 2023/3/29 21:23
// */
//@RestController
//@RequestMapping("/security/tenant/datasource")
//@Tags({
//        @Tag(name = "系统安全管理接口"),
//        @Tag(name = "多租户数据源接口")
//})
//public class SysTenantDataSourceController extends BaseWriteableRestController<SysTenantDataSource, String> {
//
//    private final SysTenantDataSourceService sysTenantDataSourceService;
//
//    public SysTenantDataSourceController(SysTenantDataSourceService sysTenantDataSourceService) {
//        this.sysTenantDataSourceService = sysTenantDataSourceService;
//    }
//
//    @Override
//    public WriteableService<SysTenantDataSource, String> getWriteableService() {
//        return sysTenantDataSourceService;
//    }
//
//    @AccessLimited
//    @Operation(summary = "根据租户代码查询数据源", description = "根据输入的租户代码，查询对应的数据源",
//            responses = {
//                    @ApiResponse(description = "查询到的数据源", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SysTenantDataSource.class))),
//                    @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
//                    @ApiResponse(responseCode = "500", description = "查询失败")
//            }
//    )
//    @Parameters({
//            @Parameter(name = "tenantId", in = ParameterIn.PATH, required = true, description = "租户代码"),
//    })
//    @GetMapping("/{tenantId}")
//    public Result<SysTenantDataSource> findByRoleCode(@PathVariable("tenantId") String tenantId) {
//        SysTenantDataSource sysTenantDataSource = sysTenantDataSourceService.findByTenantId(tenantId);
//        return result(sysTenantDataSource);
//    }
//}
