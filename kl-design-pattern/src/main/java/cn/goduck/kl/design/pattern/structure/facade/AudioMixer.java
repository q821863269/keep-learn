package cn.goduck.kl.design.pattern.structure.facade;

import java.io.File;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/6 17:37
 */
public class AudioMixer {

    public File fix(VideoFile result){
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }

}
