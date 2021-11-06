package cn.goduck.kl.design.pattern.structure.facade;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/6 17:31
 */
public class VideoFile {

    private String name;
    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

    public String getCodecType() {
        return codecType;
    }

    public String getName() {
        return name;
    }

}
