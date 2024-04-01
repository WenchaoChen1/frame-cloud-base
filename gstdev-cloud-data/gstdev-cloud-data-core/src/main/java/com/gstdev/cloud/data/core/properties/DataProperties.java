package com.gstdev.cloud.data.core.properties;

import com.gstdev.cloud.data.core.constants.DataConstants;
import com.gstdev.cloud.data.core.enums.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Data 相关模块配置 </p>
 *
 * @author : cc
 * @date : 2023/10/4 12:02
 */
@ConfigurationProperties(DataConstants.PROPERTY_PREFIX_DATA)
public class DataProperties {

  /**
   * 基础数据源切换。用于某些基础核心应用底层存储切换的配置。默认，JPA
   */
  private DataSource dataSource = DataSource.JPA;

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }
}
