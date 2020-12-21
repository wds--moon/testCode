package com.moon.interator;

import java.util.ArrayList;
import java.util.List;

/**
 * 既然是迭代器那么就需要判断下一个节点是否存在,还有就是指向下一个节点的方法
 */
interface Iterator {

    public abstract boolean hasNext();

    public abstract Object next();
}

/**
 * 书本类
 */
class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * 书架可以,存放,拿走,数量,等方法啥
 */
class BookShelf implements Aggregate{
    /**
     * 严格来说应该使用数组的,懒得写了,将就吧
     */
    List<Book> books;

    public BookShelf() {
        this.books = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        return books.add(book);
    }

    public Book getBookIndex(int i) {
        return books.get(i);
    }

    public int getLength() {
        return books.size();
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
    /**
     * 这里为什么要写成类部类?因为jdk都是这么玩的,应为每一个集合数据结构不同,那么实现迭代器的方式会不同,也可以认为不想多一个文件吧
     * ListIterator 是单独的文件,HashIterator 就是直接写在当前类的,这几爷子在搞啥,我表示没看懂,为何会这样,我自己认为是因为HashMap(1.8改成红黑树了,迭代器链表肯定要重新,还需要支持红黑树)版本的问题,升级版本需要对迭代器升级.
     * 这里要实现自定义的迭代器,并且实现自己数据相应的迭代器
     */
    class BookShelfIterator implements Iterator {

        private BookShelf bookShelf;
        private int index=0;

        public BookShelfIterator(BookShelf bookShelf) {
            this.bookShelf = bookShelf;
        }

        @Override
        public boolean hasNext() {
            if (index < bookShelf.getLength()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            Book book = bookShelf.getBookIndex(index);
            index++;
            return book;
        }

    }
}



/**
 * 声明迭代器,方便书架(集合类)实现,并且重写iterator方法
 */
interface Aggregate {
    public abstract Iterator iterator();
}

public class InteratorTest {

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();

        bookShelf.addBook(new Book("java编程思想"));
        bookShelf.addBook(new Book("深入理解jvm"));
        bookShelf.addBook(new Book("ssm框架"));
        bookShelf.addBook(new Book("设计模式"));
        bookShelf.addBook(new Book("spring设计原理"));
        bookShelf.addBook(new Book("计算机组成原理"));
        bookShelf.addBook(new Book("操作系统"));
        bookShelf.addBook(new Book("计算网络"));
        bookShelf.addBook(new Book("数据结构"));
        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
