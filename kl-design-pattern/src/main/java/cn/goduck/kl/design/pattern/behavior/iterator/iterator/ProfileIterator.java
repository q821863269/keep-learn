package cn.goduck.kl.design.pattern.behavior.iterator.iterator;

import cn.goduck.kl.design.pattern.behavior.iterator.profile.Profile;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 15:55
 */
public interface ProfileIterator {

    boolean hasNext();

    Profile getNext();

    void reset();

}
