package cn.goduck.kl.design.pattern.behavior.mediator.component;

import cn.goduck.kl.design.pattern.behavior.mediator.Mediator;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 18:01
 */
public interface Component {

    void setMediator(Mediator mediator);

    String getName();

}
