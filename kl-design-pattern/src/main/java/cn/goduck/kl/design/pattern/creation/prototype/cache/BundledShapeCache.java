package cn.goduck.kl.design.pattern.creation.prototype.cache;

import cn.goduck.kl.design.pattern.creation.prototype.shape.Circle;
import cn.goduck.kl.design.pattern.creation.prototype.shape.Rectangle;
import cn.goduck.kl.design.pattern.creation.prototype.shape.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/10/21 19:22
 */
public class BundledShapeCache {

    private Map<String, Shape> cache = new HashMap<>();

    public BundledShapeCache() {
        Circle circle = new Circle();
        circle.x = 5;
        circle.y = 7;
        circle.radius = 45;
        circle.color = "Green";

        Rectangle rectangle = new Rectangle();
        rectangle.x = 6;
        rectangle.y = 9;
        rectangle.width = 8;
        rectangle.height = 10;
        rectangle.color = "Blue";

        cache.put("Big green circle", circle);
        cache.put("Medium blue rectangle", rectangle);
    }

    public Shape put(String key, Shape shape) {
        cache.put(key, shape);
        return shape;
    }

    public Shape get(String key) {
        return cache.get(key).clone();
    }

}
