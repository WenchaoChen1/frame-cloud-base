package com.gstdev.cloud.rest.service.feign;

import com.gstdev.cloud.base.core.utils.http.HeaderUtils;
import com.gstdev.cloud.rest.core.annotation.Inner;
import feign.MethodMetadata;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.core.convert.ConversionService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

/**
 * <p>Description: 自定义 Inner 处理器 </p>
 *
 * @author : cc
 * @date : 2022/5/31 11:28
 */
public class FeignInnerContract extends SpringMvcContract {

  private static final Logger log = LoggerFactory.getLogger(FeignInnerContract.class);

  public FeignInnerContract() {
  }

  public FeignInnerContract(List<AnnotatedParameterProcessor> annotatedParameterProcessors) {
    super(annotatedParameterProcessors);
  }

  public FeignInnerContract(List<AnnotatedParameterProcessor> annotatedParameterProcessors, ConversionService conversionService) {
    super(annotatedParameterProcessors, conversionService);
  }

  public FeignInnerContract(List<AnnotatedParameterProcessor> annotatedParameterProcessors, ConversionService conversionService, boolean decodeSlash) {
    super(annotatedParameterProcessors, conversionService, decodeSlash);
  }

  @Override
  protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {

    if (methodAnnotation instanceof Inner) {
      Inner inner = findMergedAnnotation(method, Inner.class);
      if (ObjectUtils.isNotEmpty(inner)) {
        log.debug("[GstDev Cloud] |- Found inner annotation on Feign interface, add header!");
        data.template().header(HeaderUtils.X_HERODOTUS_FROM_IN, "true");
      }
    }

    super.processAnnotationOnMethod(data, methodAnnotation, method);
  }
}
