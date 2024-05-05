package com.example.skiresortapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * スキーリゾートを表すEntityクラス
 */
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Skiresort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String area;
    private String impression;

    public Skiresort(int id, String name, String area, String impression) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.impression = impression;
    }

    // ID指定しないコンストラクタ
    public Skiresort(String name, String area, String impression) {
        this.id = 0;
        this.name = name;
        this.area = area;
        this.impression = impression;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }
}
