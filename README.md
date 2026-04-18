# 📱 PixSearch-Android

Pexels API を利用した **Android向け画像検索アプリ**です。  

キーワード検索により高品質な写真を取得し、一覧・詳細表示ができます。

---

## 🖼️ Demo

![demo](demo.gif)

---

## 🚀 Features

- 🔍 キーワードによる画像検索

- 🖼️ 検索結果の一覧表示

- 🔎 画像詳細画面の表示

- ⚡ 非同期通信によるデータ取得

- 📱 Jetpack Compose によるモダンUI

---

## 🏗️ Architecture

本アプリは **MVVMアーキテクチャ** を採用しています。
```
UI (Compose)
└ ViewModel
└ Repository
└ Network (API)
```
---

## 🛠️ Tech Stack

- Kotlin

- Jetpack Compose

- MVVM

- Retrofit / OkHttp

- Coil

- Navigation Compose

---

## 💡 工夫した点

本プロジェクトでは、実務を意識して以下の点を工夫しました。

- MVVMアーキテクチャを採用し、UI・ロジック・データの責務分離を明確化

- Jetpack Compose による宣言的UIで、可読性と開発効率を向上

- APIキーをソースコードに含めず、`local.properties` で管理することでセキュリティに配慮

- 機能ごとにパッケージを分割し、拡張性・保守性を意識した構成に設計

- API通信部分を分離し、将来的な変更や差し替えに対応しやすい構造にした

---

## 🔑 APIキーの設定

本プロジェクトでは、セキュリティの観点からAPIキーをコードに含めていません。

### 手順

`local.properties` に以下を追加してください：

```properties

PEXELS_API_KEY=your_api_key_here
```
## 🏃‍♂️ Getting Started

1. リポジトリをクローン
```
git clone https://github.com/DingCheng19/PixSearch-Android.git
```
2. APIキーを設定

local.properties に追加：
```
PEXELS_API_KEY=your_api_key
```
3. ビルド & 実行

Android Studio で実行、または：
```
./gradlew installDebug
```
## 👤 Author

* DingCheng
