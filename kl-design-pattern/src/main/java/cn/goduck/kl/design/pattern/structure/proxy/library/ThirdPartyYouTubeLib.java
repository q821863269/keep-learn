package cn.goduck.kl.design.pattern.structure.proxy.library;

import java.util.HashMap;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 10:39
 */
public interface ThirdPartyYouTubeLib {

    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);

}
