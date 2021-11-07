package cn.goduck.kl.design.pattern.structure.facade.codec;

import cn.goduck.kl.design.pattern.structure.facade.AudioMixer;
import cn.goduck.kl.design.pattern.structure.facade.BitrateReader;
import cn.goduck.kl.design.pattern.structure.facade.VideoFile;

import java.io.File;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/6 17:38
 */
public class VideoConversionFacade {

    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if (format.equals("mp4")) {
            destinationCodec = new OggCompressionCodec();
        } else {
            destinationCodec = new MPEG4CompressionCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }

}
