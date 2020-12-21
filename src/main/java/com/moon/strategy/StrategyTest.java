package com.moon.strategy;


interface Comparator{
    void sort();
}

class QuickSort  implements Comparator{

    @Override
    public void sort() {
        System.out.println("快排序...........");
    }
}
class HeadSort  implements Comparator{

    @Override
    public void sort() {
        System.out.println("堆排序...........");
    }
}

class BubbleSort  implements Comparator{

    @Override
    public void sort() {
        System.out.println("冒泡排序...........");
    }
}

/**
 * 新增数据分析维度,这个概念叫簇
 */

interface Analysis{

    void operation();
}

/**
 * 分类分析
 */
class Classify  implements Analysis{

    @Override
    public void operation() {
        System.out.println("分类统计.........");
    }
}

/**
 * 相识度分析
 */
class Similarity  implements Analysis{

    @Override
    public void operation() {
        System.out.println("相识度分析.........");
    }
}

class Causal  implements Analysis{

    @Override
    public void operation() {
        System.out.println("因果分析.........");
    }
}
/**
 * 这个类,可以提供抽象接口 做一些初始操作,
 * 如果我需要增加排序可以重新实现Comparator
 */
class Context{
    /**
     * 每当新增一个特性就需要修改当前类
     */
    private Comparator comparator;

    private Analysis analysis;


    /**
     * 多个特性,这样的话我就需要实现多个构造方法,其实也还行(#^.^#)
     * 当然还可以提供一种初始化方法 init里面加载contextSort()和dataAnalysis() 我还是趋向多构造
     */

    public Context(Comparator comparator) {
        this.comparator = comparator;
    }

    public Context(Analysis analysis) {
        this.analysis = analysis;
    }

    public Context(Comparator comparator,Analysis analysis) {
        this.comparator = comparator;
        this.analysis=analysis;
    }
    /**
     * 重新设置
     * @param comparator
     */
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public void contextSort(){
        comparator.sort();
    }

    public void dataAnalysis(){
        analysis.operation();
    }

}
/**
 * 模拟多个排序,支持动态切换
 * 这里可以自己实现Comparator 重写方法,满足开闭原则,又去掉了很多if else
 */
public class StrategyTest {
    public static void main(String[] args) {
        new Context(new QuickSort()).contextSort();
        new Context(new Similarity()).dataAnalysis();
    }
}
