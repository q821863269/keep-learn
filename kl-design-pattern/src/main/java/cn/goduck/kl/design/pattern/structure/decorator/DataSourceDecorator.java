package cn.goduck.kl.design.pattern.structure.decorator;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/6 16:35
 */
public class DataSourceDecorator implements DataSource {

    private DataSource dataSource;

    public DataSourceDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeData(String data) {
        dataSource.writeData(data);
    }

    @Override
    public String readData() {
        return dataSource.readData();
    }

}
