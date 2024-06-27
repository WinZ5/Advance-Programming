package se233.chapter1.model.item;

import javafx.scene.input.DataFormat;
import se233.chapter1.model.DamageType;

import java.io.Serializable;

public class BasedEquipment implements Serializable {
    public static final DataFormat DATA_FORMAT = new DataFormat("src.main.java.se223.chapter1.model.item.BasedEquipment");
    protected String name;
    protected String imgpath;
    // new property added to use for determine which class can use what weapon
    protected DamageType damageType;

    public DamageType getDamageType() { return damageType; }
    public String getName() { return name; }
    public String getImgpath() { return imgpath; }

    public void setImgpath(String imgpath) { this.imgpath = imgpath; }
}
