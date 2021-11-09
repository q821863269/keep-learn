package cn.goduck.kl.design.pattern.behavior.visitor.visitor;

import cn.goduck.kl.design.pattern.behavior.visitor.shape.Circle;
import cn.goduck.kl.design.pattern.behavior.visitor.shape.CompoundShape;
import cn.goduck.kl.design.pattern.behavior.visitor.shape.Dot;
import cn.goduck.kl.design.pattern.behavior.visitor.shape.Rectangle;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/8 17:50
 */
public interface Visitor {

    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);

}