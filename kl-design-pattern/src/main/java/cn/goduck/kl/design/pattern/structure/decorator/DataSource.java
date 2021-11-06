package cn.goduck.kl.design.pattern.structure.decorator;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/6 16:29
 */
public interface DataSource {

    void writeData(String data);

    String readData();

}
