# 同じ名前のテーブルがあれば削除
DROP TABLE IF EXISTS skiresort;

# areaテーブル
CREATE TABLE area (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY(id)
);

# impressionテーブル
CREATE TABLE impression (
  id int unsigned AUTO_INCREMENT,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
);

# areaとimpressionの外部キー参照を持つskiresortテーブル
CREATE TABLE skiresort (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  area_id int unsigned NOT NULL,
  impression_id int unsigned NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(area_id) REFERENCES area(id);
  FOREIGN KEY(impression_id) REFERENCES impression(id);
);

# 初期データの挿入
INSERT INTO area(id, name) VALUES (1, "hokkaido");
INSERT INTO area(id, name) VALUES (2, "iwate");
INSERT INTO area(id, name) VALUES (3, "nagano");

INSERT INTO impression(id, description) VALUES (1, "スノーボードデビューした広いゲレンデ");
INSERT INTO impression(id, description) VALUES (2, "晴天率が低いので有名");
INSERT INTO impression(id, description) VALUES (3, "春の兎平はコブの聖地");
INSERT INTO impression(id, description) VALUES (4, "標高2000以上ですごく寒いけど雪質は良い");

INSERT INTO skiresort(id, name, area_id, impression_id) VALUES (1, "niseko", 1, 1);
INSERT INTO skiresort(id, name, area_id, impression_id) VALUES (2, "appi", 2, 2);
INSERT INTO skiresort(id, name, area_id, impression_id) VALUES (3, "hakubahappoone", 3, 3);
INSERT INTO skiresort(id, name, area_id, impression_id) VALUES (4, "takamine", 3, 4);
