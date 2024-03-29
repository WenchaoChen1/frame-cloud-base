/*
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君) (herodotus@aliyun.com & www.herodotus.cn)
 *
 * Dante Engine licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * <http://www.gnu.org/licenses/lgpl-3.0.html>
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gstdev.cloud.commons.ass.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import com.gstdev.cloud.commons.ass.definition.enums.BaseUiEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 服务器类型 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/21 16:29
 */
@Schema(name = "令牌格式")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ServerDevice implements BaseUiEnum<Integer> {

    /**
     * enum
     */
    PHYSICAL_MACHINE(0, "实体机"),
    VIRTUAL_MACHINE(1, "虚拟机");

    private static final Map<Integer, ServerDevice> INDEX_MAP = new HashMap<>();
    private static final List<Map<String, Object>> JSON_STRUCTURE = new ArrayList<>();

    static {
        for (ServerDevice serverDevice : ServerDevice.values()) {
            INDEX_MAP.put(serverDevice.getValue(), serverDevice);
            JSON_STRUCTURE.add(serverDevice.getValue(),
                    ImmutableMap.<String, Object>builder()
                            .put("value", serverDevice.getValue())
                            .put("key", serverDevice.name())
                            .put("text", serverDevice.getDescription())
                            .put("index", serverDevice.getValue())
                            .build());
        }
    }

    @Schema(title = "枚举值")
    private final Integer value;
    @Schema(name = "文字")
    private final String description;

    ServerDevice(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ServerDevice get(Integer index) {
        return INDEX_MAP.get(index);
    }

    public static List<Map<String, Object>> getPreprocessedJsonStructure() {
        return JSON_STRUCTURE;
    }

    /**
     * 不加@JsonValue，转换的时候转换出完整的对象。
     * 加了@JsonValue，只会显示相应的属性的值
     * <p>
     * 不使用@JsonValue @JsonDeserializer类里面要做相应的处理
     *
     * @return Enum枚举值
     */
    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }
}