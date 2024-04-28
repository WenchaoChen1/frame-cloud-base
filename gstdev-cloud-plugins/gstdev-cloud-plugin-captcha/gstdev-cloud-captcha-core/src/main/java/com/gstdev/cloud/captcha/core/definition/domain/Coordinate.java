package com.gstdev.cloud.captcha.core.definition.domain;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * <p>Description: 坐标 </p>
 *
 * @author : cc
 * @date : 2024/12/11 15:30
 */
public class Coordinate implements Serializable {

    private int x;
    private int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("x", x)
            .add("y", y)
            .toString();
    }
}
