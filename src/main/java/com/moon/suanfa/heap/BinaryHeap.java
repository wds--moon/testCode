package com.moon.suanfa.heap;

import com.moon.suanfa.heap.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉堆 主要是用来 实现局部排序存在的,也就是说只有根节点才是真真意义上的最大值或者最小值
 * 有了这个基础,就可以实现top N 算法,优先级队列等实现上还需要说明他应该是一个完全二叉树,
 * 存储元素应该是一个数组,按照树的层序遍历存放
 * <p>
 * 如果任意根节点都大于他的子节点,我们称为最大堆,相反者为最小堆
 * <p>
 * 完全二叉树存在如下公式
 * {
 * parent(i) = floor((i - 1)/2)
 * left(i)   = 2i + 1
 * right(i)  = 2i + 2
 * }
 *
 * @param <E>
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {


    /**
     * 存储数组的元素
     */
    private E[] elements;

    /**
     * 默认初始容量大小
     */
    private static final int DEFAUT_CAPACITY = 10;


    public BinaryHeap(E[] e1) {
        this(e1, null);
    }

    public BinaryHeap(Comparator<E> cmp) {
        this(null, cmp);
    }

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAUT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(DEFAUT_CAPACITY, size);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];

            }
            /**
             * 快速构建堆
             */
            heapify();
        }
    }

    /**
     * 批量构建堆,siftUp和siftDown都行,前者存在大量叶子到根的路径处理,后者是根到飞叶子节点的路径处理
     */
    private void heapify() {
        // 自上而下的上滤 nlog(n)级别
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

        // 自下而上的下滤 nlog(h),非叶子节点开始,一直到根节点
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 关于新增节点 我们是直接加到最后的n+1的位置,这个时候需要验证是否满足最大堆 满足就此结束,不满足就需要不断向上查询并且进行交换
     *
     * @param element
     */
    @Override
    public void add(E element) {
        if (element == null) {
            return;
        }
        /**
         * 由于数据是从0开始 size就是下一个需要插入的元素
         */
        elements[size++] = element;
        /**
         * 现在需要不断向上查询交换位置,size-1就是新增节点的位置
         */
        siftUp(size - 1);


    }

    /**
     * 上滤
     *
     * @param index
     */
    private void siftUp(int index) {
        E element = elements[index];
        int parentIndex = (index - 1) >> 1;
        while (parentIndex >= 0) {
            E parent = elements[parentIndex];
            //大于0的话,就交换值
            if (compare(element, parent) > 0) {
                elements[parentIndex] = element;
                elements[index] = parent;
                index = parentIndex;
                parentIndex = (parentIndex - 1) >> 1;

            } else {
                break;
            }
        }
    }

    @Override
    public E get() {
        return elements[0];
    }

    @Override
    public E remove() {
        //删除是删除堆顶元素,像操作二叉树一样用前置接点替换根节点

        E head = elements[0];
        if (size <= 1) {
            elements[0] = null;
            size--;
            return head;
        }
        /**
         * 尾部节点
         */
        E tail = elements[size - 1];
        //互换
        elements[0] = tail;
        elements[size - 1] = null;
        size--;
        //现在需要进行下滤
        siftDown(0);
        return head;
    }

    private void siftDown(int index) {
        //得到分页子节点
        int half = size >> 1;
        while (index < half) {
            int leftIndex = (index << 1) + 1;
            int rightIndex = leftIndex + 1;
            /**
             * 选出左右节点和根节点进行交换
             * 三种情况 存在左右节点都存在,存在左节点,左右节点相等还需要和父节点比较
             */
            if ((rightIndex < size) && compare(elements[leftIndex], elements[rightIndex]) >= 0) {
                if (compare(elements[leftIndex], elements[index]) > 0) {
                    E tmp = elements[leftIndex];
                    elements[leftIndex] = elements[index];
                    elements[index] = tmp;
                    index = leftIndex;
                } else {
                    return;
                }

            } else if ((rightIndex < size) && compare(elements[leftIndex], elements[rightIndex]) < 0) {
                if (compare(elements[rightIndex], elements[index]) > 0) {
                    E tmp = elements[rightIndex];
                    elements[rightIndex] = elements[index];
                    elements[index] = tmp;
                    index = rightIndex;
                } else {
                    return;
                }

            } else if ((rightIndex >= size)) {
                //说明不存在右节点
                if (compare(elements[leftIndex], elements[index]) > 0) {
                    E tmp = elements[leftIndex];
                    elements[leftIndex] = elements[index];
                    elements[index] = tmp;
                    index = leftIndex;
                } else {
                    return;
                }
            } else {
                return;
            }
        }

    }

    /**
     * 替换根节点
     *
     * @param element
     * @return
     */
    @Override
    public E replace(E element) {
        E top = null;
        if (size == 0) {
            elements[size++] = element;
        } else {
            top = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return top;
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
