package com.moon.adapter;

import java.util.Arrays;

interface Processor {
    /**
     * 高版本jdk 接口支持默认实现了,牛逼我只能说,不然我还得写一个抽象类
     * @return
     */
    default  String name() {
        return getClass().getSimpleName();
    }

     Object process(Object input) ;
}

class Upcase implements Processor {
    /**
     * 返回值Object->String 是支持的,子类实现父类只允许扩大
     * 如果你上面是string 下面是Object就不行
     *
     * @param input
     * @return
     */
    @Override
    public String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class Downcase implements Processor {

    @Override
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class Splitter implements Processor {

    @Override
    public String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}


class FilterAdapter implements Processor{
    /**
     * 关联包中的Filter 然后把我们的方法,委托给他处理
     */
    Filter filter;

    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    /**
     * 下面的方法严格来说我们用的是委托
     * @return
     */
    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public Object process(Object input) {
        return filter.process((Waveform) input);
    }
}

/**
 * ==========================================分割线,发现相同功能被人写的==============================================
 */

class  Waveform{
    private static long counter;
    /**
     * new 一次id自增一次,这里这个long不能使用Long Long的默认值是null,然后程序就null指针了
     */
    private final long id=counter++;

    @Override
    public String toString() {
        return "Waveform "+ id;
    }
}

/**
 * 这个Filter和Processor 代码逻辑一样
 */
class Filter{
    public String name() {
        return getClass().getSimpleName();
    }

    public Waveform process(Waveform input) {
        return input;
    }
}

class LowPass extends Filter{
    double cutoff;

    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public Waveform process(Waveform input) {
        //不做处理
        return input;
    }
}

class HighPass extends Filter{
    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public Waveform process(Waveform input) {
        //不做处理
        return input;
    }
}

class BandPass extends Filter{
    double lowCutoff;
    double highCutoff;

    public BandPass(double lowCutoff, double highCutoff) {
        this.lowCutoff = lowCutoff;
        this.highCutoff = highCutoff;
    }

    @Override
    public Waveform process(Waveform input) {
        //不做处理
        return input;
    }
}

public class Applicator {

    public static void apply(Processor p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }

    public static void main(String[] args) {
        String s = "No picture say you JB";
        Applicator.apply(new Upcase(),s);
        Applicator.apply(new Downcase(),s);
        Applicator.apply(new Splitter(),s);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        Waveform w = new Waveform();
        Applicator.apply(new FilterAdapter(new LowPass(1.0)), w);
        Applicator.apply(new FilterAdapter(new HighPass(2.0)), w);
        Applicator.apply(new FilterAdapter(new BandPass(3.0, 4.0)), w);
    }
}
