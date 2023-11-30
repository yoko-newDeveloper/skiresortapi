# 同じ名前のテーブルがあれば削除
DROP TABLE IF EXISTS skiresortapi

CREATE TABLE skiresortapi (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  area VARCHAR(20) NOT NULL,
  impression VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO skiresortapi(id, name, area, impression) VALUES (1, "niseko", "hokkaido", "スノーボードデビューした広いゲレンデ");
INSERT INTO skiresortapi(id, name, area, impression) VALUES (2, "appi", "iwate", "晴天率が低いので有名");
INSERT INTO skiresortapi(id, name, area, impression) VALUES (3, "hakubahappoone", "nagano", "春の兎平はコブの聖地");
INSERT INTO skiresortapi(id, name, area, impression) VALUES (4, "takamine", "nagano", "標高2000以上ですごく寒いけど雪質は良い");
