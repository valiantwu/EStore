package org.woo.framework.lifecycle;

public class ServiceItem {
    public IService instance;
    private String name;
    private String className;
    public boolean disable;
    public boolean started;
    public boolean ended;
    private String[] depends;

    public ServiceItem(String name, String value) {
        this.name = name;
        this.className = value;
        try {
            Class<?> clazz=Class.forName(value);
            instance=(IService)clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

//        String[] parts = value.split(";");
//        this.className = parts[0];
//        if (parts.length > 1) {
//            this.depends = parts[1].split(",");
//            for (int i = 0; i < this.depends.length; i++) {
//                this.depends[i] = this.depends[i].trim();
//            }
//        }
    }
}
