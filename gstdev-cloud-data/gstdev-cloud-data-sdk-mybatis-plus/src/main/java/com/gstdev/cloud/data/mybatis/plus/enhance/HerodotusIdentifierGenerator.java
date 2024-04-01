package com.gstdev.cloud.data.mybatis.plus.enhance;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.data.id.Snowflake;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 自定义Mybatis Plus ID 生成器 </p>
 *
 * @author : cc
 * @date : 2021/8/28 15:52
 */
@Component
public class HerodotusIdentifierGenerator implements IdentifierGenerator {

  @Override
  public Number nextId(Object entity) {
    // 采用雪花算法获取id,时间回拨会存在重复,这里用随机数来减少重复的概率
    final Snowflake snowflake = IdUtil.getSnowflake(1, (int) (Math.random() * 20 + 1));
    return snowflake.next();
  }
}
