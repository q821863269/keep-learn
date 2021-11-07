package cn.goduck.kl.design.pattern.structure.facade.codec;

import cn.goduck.kl.design.pattern.structure.facade.VideoFile;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/6 17:33
 */
public class CodecFactory {

    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equals("mp4")) {
            System.out.println("CodecFactory: extracting mpeg audio...");
            return new MPEG4CompressionCodec();
        }
        else {
            System.out.println("CodecFactory: extracting ogg audio...");
            return new OggCompressionCodec();
        }
    }

}
