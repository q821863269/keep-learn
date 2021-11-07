package cn.goduck.kl.design.pattern.behavior.iterator.social.networks;

import cn.goduck.kl.design.pattern.behavior.iterator.iterator.LinkedInIterator;
import cn.goduck.kl.design.pattern.behavior.iterator.iterator.ProfileIterator;
import cn.goduck.kl.design.pattern.behavior.iterator.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 16:10
 */
public class LinkedIn implements SocialNetwork {

    private List<Profile> contacts;

    public LinkedIn(List<Profile> contacts) {
        if (contacts != null) {
            this.contacts = contacts;
        } else {
            this.contacts = new ArrayList<>();
        }
    }

    public Profile requestContactInfoFromLinkedInAPI(String profileEmail) {
        simulateNetworkLatency();
        System.out.println("LinkedIn: Loading profile '" + profileEmail + "' over the network...");

        return findProfile(contacts, profileEmail);
    }

    public List<String> requestRelatedContactsFromLinkedInAPI(String profileEmail, String contactType) {
        simulateNetworkLatency();
        System.out.println("LinkedIn: Loading '" + contactType + "' list of '" + profileEmail + "' over the network...");

        Profile profile = findProfile(contacts, profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new LinkedInIterator(this, "friends", profileEmail);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new LinkedInIterator(this, "coworkers", profileEmail);
    }

}
