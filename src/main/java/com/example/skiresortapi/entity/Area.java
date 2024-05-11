package com.example.skiresortapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * スキーリゾートのエリアを表すEntityクラス
 */
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Area(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // ID指定しないコンストラクタ
    public Area(String name) {
        this.id = 0;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
