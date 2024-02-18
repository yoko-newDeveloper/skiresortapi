# Github Actionsとは

ワークフローを自動化する

Actions:実態はDockerコンテナ

## ワークフロー構文

- `name`: (省略可能)ワークフロー名。GitリポジトリのActionsタブに表示される
- `on`:アクションのトリガー
    - `push`や`pull_request`でトリガーのタイミングを指定可能
    - `branchs`ブランチを指定可能

- `jobs`:ワークフローファイルで実行されるすべてのジョブをグループ化している
- `runs-on`:ジョブを実行するマシンの種類を設定(ホストランナーorセルフホストランナー)
- `steps`:具体的なタスク
- `uses`:ジョブで指定するリポジトリ
- `run`:具体的なシェルで`|`を使えばパイプライン処理も可能

## タスク順序

- Hello worldするだけのワークフローを作成
- Pull requestをトリガーにして動くようワークフローを修正
- Gradleでtestする方法を調べる
- GitHub ActionsでGradleでtestする方法を調べてワークフローに修正
