# Repositoryクラスとは

データベースとアプリケーションの間でデータの永続化と操作を担当するクラス
<br>
データベースへのCRUD処理を行う

- ライブラリに依存関係を追加<br>
  `implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
  `
- リポジトリクラスにインポート文を追加<br>
  `import org.springframework.data.jpa.repository.JpaRepository;
  `
- Entityに`@Entity`を追加し、JRAによって管理されるエンティティであることを宣言する

# AreaRepository

- 継承:`JpaRepository<Area, Integer>`を継承することで、基本的なCRUD操作を利用可能にする
    - `Area`:エンティティのタイプ
    - `Integer`:エンティティのIDタイプ
- カスタムメソッド:`findByName`メソッドのように、エンティティ特定のフィールドに基づいて検索するためのカスタムメソッドを追加できる
