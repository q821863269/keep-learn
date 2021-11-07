package cn.goduck.kl.design.pattern.structure.proxy;

import cn.goduck.kl.design.pattern.structure.proxy.library.ThirdPartyYouTubeClass;
import cn.goduck.kl.design.pattern.structure.proxy.library.ThirdPartyYouTubeLib;
import cn.goduck.kl.design.pattern.structure.proxy.library.Video;

import java.util.HashMap;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 10:50
 */
public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {

    private ThirdPartyYouTubeLib youTubeService;
    private HashMap<String, Video> cachePopular = new HashMap<>();
    private HashMap<String, Video> cacheAll = new HashMap<>();

    public YouTubeCacheProxy() {
        this.youTubeService = new ThirdPartyYouTubeClass();
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        if (cachePopular.isEmpty()) {
            cachePopular = youTubeService.popularVideos();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = youTubeService.getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    public void reset() {
        cachePopular.clear();
        cacheAll.clear();
    }

}
