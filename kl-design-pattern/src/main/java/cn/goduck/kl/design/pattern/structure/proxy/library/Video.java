package cn.goduck.kl.design.pattern.structure.proxy.library;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 10:39
 */
public class Video {

    public String id;
    public String title;
    public String data;

    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }

}
