package com.gstdev.cloud.captcha.core.dto;

import com.gstdev.cloud.captcha.core.definition.domain.Coordinate;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * <p>Description: 验证数据实体 </p>
 *
 * @author : cc
 * @date : 2024/12/14 16:04
 */
public class Verification extends Captcha {

    /**
     * 滑块拼图验证参数
     */
    private Coordinate coordinate;
    /**
     * 文字点选验证参数
     */
    private List<Coordinate> coordinates;
    /**
     * 图形验证码验证参数
     */
    private String characters;

    public Verification() {
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Verification that = (Verification) o;
        return Objects.equal(characters, that.characters);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(characters);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("characters", characters)
            .toString();
    }
}
