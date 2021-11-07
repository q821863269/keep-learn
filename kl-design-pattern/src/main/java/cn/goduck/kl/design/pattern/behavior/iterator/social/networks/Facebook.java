package cn.goduck.kl.design.pattern.behavior.iterator.social.networks;

import cn.goduck.kl.design.pattern.behavior.iterator.iterator.FacebookIterator;
import cn.goduck.kl.design.pattern.behavior.iterator.iterator.ProfileIterator;
import cn.goduck.kl.design.pattern.behavior.iterator.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 15:59
 */
public class Facebook implements SocialNetwork {

    private List<Profile> profiles;

    public Facebook(List<Profile> cache) {
        if (cache != null) {
            this.profiles = cache;
        } else {
            this.profiles = new ArrayList<>();
        }
    }

    public Profile requestProfileFromFacebook(String profileEmail) {
        simulateNetworkLatency();
        System.out.println("Facebook: Loading profile '" + profileEmail + "' over the network...");

        return findProfile(profiles, profileEmail);
    }

    public List<String> requestProfileFriendsFromFacebook(String profileEmail, String contactType) {
        simulateNetworkLatency();
        System.out.println("Facebook: Loading '" + contactType + "' list of '" + profileEmail + "' over the network...");

        Profile profile = findProfile(profiles, profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new FacebookIterator(this, "friends", profileEmail);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new FacebookIterator(this, "coworkers", profileEmail);
    }

}
