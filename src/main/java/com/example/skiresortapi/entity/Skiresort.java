package com.example.skiresortapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * スキーリゾートを表すEntityクラス
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skiresort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "area_id") // カラム名を指定
    private Area area;

    @ManyToOne
    @JoinColumn(name = "impression_id") // カラム名を指定
    private Impression impression;
}
