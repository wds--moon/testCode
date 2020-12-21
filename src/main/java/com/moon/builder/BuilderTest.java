package com.moon.builder;

/**
 * 产品类
 */
class Computer {
    String cpu;
    String ram;
    String hardDisk;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", hardDisk='" + hardDisk + '\'' +
                '}';
    }
}

/**
 * 定义固定的流程
 */
interface ComputerBuilder {

    void setCpu();

    void setRam();

    void setHardDisk();

    /**
     * 注意这里为啥不把cpu ram disk这个参数设置写在install 中呢,如果写进去了他就变成工厂模式了
     * 这就是工厂和建造者的区别 建造者在乎整个设置过程,而工厂只关心结果
     * @return
     */
    Computer install();
}

/**
 * 高端电脑
 */
class HighComputerBuilder implements ComputerBuilder {

    Computer computer = new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("i7");
    }

    @Override
    public void setRam() {
        computer.setRam("16");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("1T");
    }

    @Override
    public Computer install() {
        return computer;
    }
}

/**
 * 中端
 */
class MiddleComputerBuilder implements ComputerBuilder {

    Computer computer = new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("i5");
    }

    @Override
    public void setRam() {
        computer.setRam("8");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("1T");
    }

    @Override
    public Computer install() {
        return computer;
    }
}

/**
 * 低级
 */
class LowComputerBuilder implements ComputerBuilder {

   private Computer computer = new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("i3");
    }

    @Override
    public void setRam() {
        computer.setRam("8");
    }

    @Override
    public void setHardDisk() {
        computer.setHardDisk("500G");
    }

    @Override
    public Computer install() {
        return computer;
    }
}

class Director {

    public Computer build(ComputerBuilder computerBuilder) {
        computerBuilder.setCpu();
        computerBuilder.setRam();
        computerBuilder.setHardDisk();
        return computerBuilder.install();
    }
}


/**
 * 配置电脑
 */
public class BuilderTest {

    public static void main(String[] args) {
        Director director=new Director();
        System.out.println(director.build(new HighComputerBuilder()));
    }
}
