package jdi.data;

public class MetalColor {
    public int[] summary = {3,8};
    public String[] elements = {"Water", "Fire"};
    public String color = "Red";
    public String metals = "Selen";
    public String[] vegetables = {"Cucumber","Tomato"};

    public String[] result() {
        String[] res = new String[5];
        res[0] = "Summary: " + (summary[0]+summary[1]);
        res[1] = "Elements: " + elements[0] +", "+ elements[1];
        res[2] = "Color: " + color;
        res[3] = "Metal: " + metals;
        res[4] = "Vegetables: " + vegetables[0]+", "+ vegetables[1];
        return res;
    }
}
