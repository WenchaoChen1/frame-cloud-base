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

package com.gstdev.cloud.oauth2.core.enums;

/**
 * <p>Description: 证书使用策略 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/3/6 18:32
 */
public enum Certificate {

    /**
     * Spring Authorization Server 默认的 JWK 生成方式
     */
    STANDARD,
    /**
     * 自定义证书 JWK 生成方式
     */
    CUSTOM;
}
