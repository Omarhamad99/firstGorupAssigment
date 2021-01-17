package com.example.firstgroupassigment.module;

import com.example.firstgroupassigment.R;

public class Cars {
    private String name;
    private String Descreption;
    private int Image_id;

    public Cars(String name, String descreption, int image_id) {
        this.name = name;
        Descreption = descreption;
        Image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public String getDescreption() {
        return Descreption;
    }

    public int getImage_id() {
        return Image_id;
    }

    public static final Cars [] cars ={
                      new Cars("EVO","Made in Japan Color :grey Engine :2.5 litters twin turbo HP:460", R.drawable.evo),
            new Cars("GClass","Made in : Gremany Color : White Engine : 5.6 litters V10 HP :510",R.drawable.gwagon),
            new Cars("ford","Made in USA Color : Red Engine :3 litters HP : 250 ",R.drawable.ford),
            new Cars("Honda","Made in Japan Color : Blue Engine : 1.6 litters HP 105",R.drawable.honda),
            new Cars("Hyundai"," Made in : Korya Color : blue Engine : 2 Litters turbo HP : 180",R.drawable.hyundai),
            new Cars("Isuzu","Made in Japan Color : black Engine 3 litters turbo HP : 260",R.drawable.isuzu),
            new Cars("Jeep","Made in USA Color : grey Engine :2.5 litters turbo HP : 200",R.drawable.jeep),
            new Cars("Kia","Made in korya Color : grey Engine :1.5 litters turbo HP : 100",R.drawable.kia),
            new Cars("Lamborghini ","Made in Italy Color : blue Engine 5.6 twin turbo Hp : 650",R.drawable.labo),
            new Cars("Skoda","made in Gremany Color : White Engine 2 Litters turbo HP : 180",R.drawable.skoda),

    };

}
