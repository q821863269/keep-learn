package cn.goduck.kl.design.pattern.structure.facade;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/6 17:37
 */
public class BitrateReader {

    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader: reading file...");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader: writing file...");
        return buffer;
    }

}
