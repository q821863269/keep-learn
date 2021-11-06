package cn.goduck.kl.design.pattern.structure.facade;

import java.io.File;

/**
 * Desc: 外观模式
 *       外观模式是一种结构型设计模式， 能为程序库、 框架或其他复杂类提供一个简单的接口。
 * Author: Kon
 * Date: 2021/11/6 17:40
 */
public class Demo {

    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
        System.out.println(mp4Video.getAbsolutePath());
        // ...
    }

}
