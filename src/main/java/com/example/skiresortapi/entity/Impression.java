package com.example.skiresortapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * スキーリゾートの印象を表すEntityクラス
 */
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Impression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String impression;

    public Impression(int id, String impression) {
        this.id = id;
        this.impression = impression;
    }

    // ID指定しないコンストラクタ
    public Impression(String impression) {
        this.id = 0;
        this.impression = impression;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImpression() {
        return this.impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }
}
