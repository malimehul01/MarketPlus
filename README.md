<div align="center">

# 📈 MarketPlus 🚀

**A modern, offline-first cryptocurrency tracking Android application**
built with Kotlin, Jetpack Compose, Room Database, and Retrofit using the CoinGecko API.

![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)
![Material 3](https://img.shields.io/badge/Material-3-6750A4?logo=materialdesign&logoColor=white)
![Room](https://img.shields.io/badge/Room-Database-orange)
![Retrofit](https://img.shields.io/badge/Retrofit-REST%20API-48B983)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20MVVM-success)
![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)

</div>

---

## 📖 About the Project

**MarketPlus** is a native Android app for tracking cryptocurrency prices and market data in real time using the **CoinGecko API**. It is built **offline-first**: data is cached locally in Room, so the app stays usable even when the internet connection drops. The project follows strict Clean Architecture under the `com.mddevlabs.marketplus` package, with a clear separation between data caching, domain contracts, and UI presentation.

---

## ✨ Features

- 📊 **Live Market Listings** of top cryptocurrencies from CoinGecko
- 🔍 **Coin Detail Screen** with detailed metrics
- 📴 **Offline-First Support** with Room database caching
- 🌐 **Internet Connectivity Observer** to detect network changes in real time
- 🔄 **Automatic Data Refresh** when the connection is restored
- 🎨 **Material 3 UI** built entirely with Jetpack Compose
- 🧱 **Clean Architecture** with strict separation of concerns
- ⚡ **Reactive UI** powered by Coroutines & StateFlow

---

## 📂 Project Architecture & Structure

```
com.mddevlabs.marketplus/
│
├── data/                       # Data Layer implementation
│   ├── local/                  # Room Database (CoinDao, CoinDatabase, CoinEntity)
│   ├── mapper/                 # Entity-to-Domain mappers (CoinMapper.kt)
│   ├── remote/                 # Network & API bindings
│   │   └── dto/                # DTOs & API interfaces (CoinDto, CoinGeckoApi)
│   └── repository/             # Concrete repository implementation (CoinRepositoryImpl)
│
├── domain/                     # Core Business Logic Layer (Pure Kotlin)
│   ├── model/                  # Domain models
│   ├── repository/             # Repository contracts (CoinRepository)
│   └── usecase/                # Business rule interactors
│
├── presentation/               # UI Layer (Jetpack Compose & ViewModels)
│   ├── theme/                  # Theme styling & Material components
│   ├── coinDetailScreen.kt     # Detailed coin metrics view
│   ├── CoinDetailViewmodel.kt  # ViewModel for coin details
│   ├── CoinListItem.kt         # List element component
│   └── CoinViewModel.kt        # ViewModel for market listings
│
├── ui/                         # UI components
├── utils/                      # Helper utilities (internetObserver.kt, isInternetAvailable.kt)
├── MarketPlus.kt               # Application Class
└── MainActivity.kt             # Single entry point Activity
```

### 🧩 Layer Responsibilities

| Layer | Responsibility | Depends On |
|-------|----------------|------------|
| **Presentation** | Compose UI, ViewModels, UI state handling | Domain |
| **Domain** | Business rules, use cases, repository contracts, models | Nothing (pure Kotlin) |
| **Data** | Room caching, Retrofit API calls, mappers, repository implementation | Domain |

---

## 🔄 Data Flow (Offline-First)

```
Compose UI  ──▶  ViewModel  ──▶  UseCase  ──▶  CoinRepository (interface)
    ▲                                                   │
    │                                                   ▼
    │                                         CoinRepositoryImpl
    │                                           │             │
    │                                           ▼             ▼
    └────────── StateFlow ◀──────────────  Room DB  ◀──  CoinGecko API
```

1. UI observes data from **Room** via Flow (single source of truth).
2. Repository fetches fresh data from **CoinGecko** when the internet is available.
3. Fresh data is saved into Room using `@Upsert`.
4. Room emits the updated data, and the UI refreshes automatically.
5. When offline, the UI keeps showing the last cached data.

---

## 🛠 Tech Stack & Principles

| Category | Technology |
|----------|-----------|
| **Language** | 100% Kotlin |
| **UI Toolkit** | Jetpack Compose & Material 3 |
| **Architecture** | Clean MVVM + Unidirectional Data Flow (UDF) |
| **Local Database** | Room Database (`CoinDatabase`, `@Upsert` caching) |
| **Networking** | Retrofit, OkHttp, CoinGecko REST API |
| **Async** | Kotlin Coroutines & StateFlow |
| **Connectivity** | Custom Internet Observer & Network State Handlers |

---

## 🌟 Key Technical Highlights

- **Offline-First Architecture:** Local Room caching ensures smooth data availability even when internet connectivity drops.
- **Clean Data Mapping:** Strict separation using `CoinMapper` to decouple raw network DTOs and database entities from domain business models.
- **Reactive State Management:** Seamless asynchronous UI updates using Kotlin Coroutines and Flow across list and detail screens.
- **Connectivity Awareness:** Custom `internetObserver` and `isInternetAvailable` utilities to react to network changes.

---

## ⚙️ Getting Started

### Prerequisites

- Android Studio (latest stable version recommended)
- JDK 17 or higher
- Android SDK (min SDK as defined in `build.gradle`)
- Internet connection for the first data sync

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/malimehul01/MarketPlus.git

# 2. Open the project in Android Studio
# 3. Sync Gradle
# 4. Run the app on an emulator or physical device
```

### 🔑 API Configuration

The app uses the public **CoinGecko API**. If you use a Demo/Pro API key, add it to `local.properties`:

```properties
COINGECKO_API_KEY=your_api_key_here
```

> ⚠️ **Never commit** API keys to a public repository. Make sure `local.properties` is in `.gitignore`.

> ℹ️ The free CoinGecko API has rate limits. Caching in Room helps reduce unnecessary requests.

---

## 📸 Screenshots

| Market List | Coin Detail | Offline Mode |
|:-----------:|:-----------:|:------------:|
| <img width="720" height="1520" alt="WhatsApp Image 2026-10-22 at 9 36 35 AM" src="https://github.com/user-attachments/assets/df9f8d17-95f6-4e30-806e-6d8f3d17d17c" />|<img width="720" height="1520" alt="WhatsApp Image 2026-10-22 at 9 36 34 AM" src="https://github.com/user-attachments/assets/9873180f-dc06-4ba0-af41-77816d1a8d10" /> | <img width="720" height="1520" alt="WhatsApp Image 2026-10-22 at 9 36 34 AM (1)" src="https://github.com/user-attachments/assets/48fbc284-5f7a-4867-a211-146a22e9a8b3" />|

---


## 🙏 Acknowledgements

- [CoinGecko API](https://www.coingecko.com/en/api) for providing cryptocurrency market data
- [Android Jetpack](https://developer.android.com/jetpack) for Compose and Room

---

## 📄 License

This project is licensed under the **MIT License**. See the `LICENSE` file for details.

---

## 👨‍💻 Author

**Mehul Mali**

- GitHub: [@malimehul01](https://github.com/malimehul01)

---

<div align="center">

⭐ If you like this project, don't forget to give it a star! ⭐

</div>
