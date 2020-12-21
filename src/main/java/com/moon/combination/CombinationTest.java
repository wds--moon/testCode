package com.moon.combination;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 组合类型,把菜单和菜单项的方法全部抽取到抽象类中,统一管理,子类没有实现的(不能实现)直接抛异常,客户端直接捕获吃掉
 * 其目的就是为了抽象化,变成统一对象来处理
 */
abstract class MenuComposite {
    private String name;
    private String description;

    public MenuComposite(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ==============================菜单和菜单项都有中公有的或者私有的都需要放在组合类中,但是都不实现==============================
     */
    /**
     * ++++++++++++++++++++++++++++菜单方法++++++++++++++++++++++++++++++
     */
    public void add(MenuComposite menu) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComposite menu) {
        throw new UnsupportedOperationException();
    }

    public MenuComposite getChild(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * ++++++++++++++++++++++++++++菜单项方法++++++++++++++++++++++++++++++
     */
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    public List getList() {
        throw new UnsupportedOperationException();
    }

    public  CompositeIterator iterator(){
        throw new UnsupportedOperationException();
    }
}


/**
 * 菜单 名称和说明
 */
class Menu extends MenuComposite {


    private List<MenuComposite> list = new ArrayList<>();

    public Menu(String name, String description) {
        super(name, description);
    }

    @Override
    public void add(MenuComposite menu) {
        list.add(menu);
    }

    @Override
    public void remove(MenuComposite menu) {
        list.remove(menu);
    }

    @Override
    public MenuComposite getChild(int i) {
        return list.get(i);
    }


    @Override
    public List<MenuComposite> getList() {
        return list;
    }

    @Override
    public CompositeIterator iterator() {
        return new CompositeIterator(list.iterator());
    }
}

/**
 * 菜单项 (具体的菜名称)
 */
class MenuItem extends MenuComposite {

    private boolean vegetarian;

    private double price;

    MenuItem(String name, String description, boolean vegetarian, double price) {
        super(name, description);
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }


    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

/**
 * 迭代器模式运用 对于树形机构java未提供迭代工具
 */
class CompositeIterator implements Iterator<MenuComposite> {

    private Stack<Iterator<MenuComposite>> s = new Stack<>();

    public CompositeIterator(Iterator<MenuComposite> it) {
        s.push(it);
    }

    @Override
    public boolean hasNext() {
        if (s.isEmpty()) {
            return false;
        } else {
            Iterator<MenuComposite> peek = s.peek();
            /**
             * 如果发现还存在下一个节点就弹出栈,并且递归
             */
            if (!peek.hasNext()) {
                s.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public MenuComposite next() {
        Iterator<MenuComposite> peek = s.peek();
        MenuComposite next = peek.next();
        if (next instanceof Menu) {
            s.push(((Menu) next).getList().iterator());
        }
        return next;
    }
}

public class CombinationTest {

    public static void main(String[] args) {
        MenuComposite menu = new Menu("川菜", "舌尖上的中国推荐");

        MenuComposite menu1 = new Menu("烧菜", "烧菜精品");
        MenuComposite menuItem0 = new MenuItem("雅鱼", "传说中,雅安的名菜", false, 86);
        MenuComposite menuItem1 = new MenuItem("红烧肉", "红烧肉好吃不腻!", false, 26);
        menu1.add(menuItem0);
        menu1.add(menuItem1);
        MenuComposite menu2 = new Menu("炖菜", "独特的四川魅力");
        MenuComposite menuItem3 = new MenuItem("羊肉炖酸菜", "羊肉炖酸菜!", false, 68);
        MenuComposite menuItem4 = new MenuItem("小鸡炖蘑菇", "小鸡炖蘑菇,专治出轨!", false, 99);
        menu2.add(menuItem3);
        menu2.add(menuItem4);
        MenuComposite menu3 = new Menu("凉菜", "正宗四川凉菜");
        MenuComposite menuItem5 = new MenuItem("口水鸡", "鸡肉拌菜!", false, 25);
        MenuComposite menuItem6 = new MenuItem("虎皮青椒", "酸!", true, 12);
        menu3.add(menuItem5);
        menu3.add(menuItem6);
        MenuComposite menu4 = new MenuItem("火锅", "重庆火锅,按30元/位!", false, 30);
        menu.add(menu1);
        menu.add(menu4);
        menu.add(menu2);
        menu.add(menu3);
        System.out.println(menu);
        CompositeIterator mc = menu.iterator();
        while (mc.hasNext()){
            MenuComposite next = mc.next();
            System.out.println(next.getName() + ": " + next.getDescription());
//            try {
//                //根据价格,是否荤素,描述进行筛选
//                if (next.getPrice()>50){
//                    System.out.println(next.getName() + ": " + next.getDescription());
//                }
//            } catch (Exception e) {
//                //不处理异常
//            }

        }

    }
}
