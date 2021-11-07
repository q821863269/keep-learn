package cn.goduck.kl.design.pattern.behavior.iterator.social.networks;

import cn.goduck.kl.design.pattern.behavior.iterator.iterator.ProfileIterator;
import cn.goduck.kl.design.pattern.behavior.iterator.profile.Profile;

import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 15:59
 */
public interface SocialNetwork {

    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);

    default Profile findProfile(List<Profile> profiles, String profileEmail) {
        for (Profile profile : profiles) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }
        return null;
    }

    default void simulateNetworkLatency() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
