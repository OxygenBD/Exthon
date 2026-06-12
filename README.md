# 🎬 Exthon - Productivity & Lifestyle Management App

A comprehensive Android app for managing YouTube playlists, daily schedules, prayer times, study sessions, and personal productivity with achievement tracking and content blocking features.

## 📱 Features

### 🎥 YouTube Playlist Management
- Add and manage multiple YouTube playlists
- Track last watched video with timestamp
- Resume from where you left off
- Display playlist names and videos in organized grid layout
- Direct YouTube integration

### ⏰ Schedule & Time Management
- Daily timetable/schedule management
- Pomodoro timer for different activities (reading, writing, prayer, play, work, etc.)
- Automatic daily reset with customizable time slots
- Visual calendar integration

### 🕌 Prayer Times
- 5 daily prayer times (Fajr, Dhuhr, Asr, Maghrib, Isha)
- Automatic prayer time notifications and alarms
- Prayer time adjustments based on location
- Prayer completion tracking

### 📝 Smart Notes
- Rich text editor with formatting (Bold, Italic, Strikethrough, Hyperlinks)
- Built-in calculator
- Organize notes into groups/categories
- Search functionality
- Export/Import notes in custom format (.exthon or .json)

### 🎖️ Achievement System
- Unlock achievements after completing tasks
- Weekly achievements for consistency
- Monthly and yearly milestone tracking
- Motivation-based reward system
- Achievement badges and levels

### 🔒 Digital Wellness
- Block adult/porn sites and apps
- Social media blocker
- Customizable harmful content filter
- Screen time monitoring
- Wellness achievement unlocking
- Self-improvement tracking

### 📊 Data Management
- Export/Import schedules and data
- Backup and restore functionality
- Custom file format support (.exthon format)
- Cloud sync options (Firebase integration)

---

## 🏗️ Project Structure

```
Exthon/
├── app/                          # Android Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/oxygenbdexthon/
│   │   │   │   ├── activities/              # UI Activities
│   │   │   │   ├── fragments/               # UI Fragments
│   │   │   │   ├── services/                # Background Services
│   │   │   │   ├── models/                  # Data Models
│   │   │   │   ├── database/                # Room Database
│   │   │   │   ├── api/                     # API Clients (YouTube)
│   │   │   │   ├── utils/                   # Utility Classes
│   │   │   │   ├── adapters/                # RecyclerView Adapters
│   │   │   │   ├── workers/                 # WorkManager Tasks
│   │   │   │   └── MainActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/                  # XML Layouts
│   │   │   │   ├── drawable/                # Images & Icons
│   │   │   │   ├── values/                  # Colors, Strings
│   │   │   │   ├── menu/                    # Menu Resources
│   │   │   │   └── anim/                    # Animations
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                            # Unit Tests
│   │   └── androidTest/                     # Instrumented Tests
│   └── build.gradle
├── docs/                         # Documentation
│   ├── FEATURES.md              # Feature Documentation
│   ├── ARCHITECTURE.md          # Architecture Guide
│   ├── API_INTEGRATION.md       # YouTube API Setup
│   ├── DATABASE_SCHEMA.md       # Database Design
│   └── USER_GUIDE.md            # User Guide
├── scripts/                      # Build & Export Scripts
│   ├── build-apk.sh            # Build APK Script
│   └── export-data.py           # Data Export Script
├── .github/
│   └── workflows/               # CI/CD Workflows
│       ├── build.yml           # Build on Push
│       ├── test.yml            # Run Tests
│       └── release.yml         # Release APK
├── build.gradle                 # Project Configuration
├── settings.gradle
├── gradle.properties
├── .gitignore
├── LICENSE                      # GPL v3
└── README.md                    # This File
```

---

## 🛠️ Tech Stack

**Frontend:**
- Kotlin/Java (Android Native)
- Material Design 3
- ConstraintLayout & RecyclerView
- Jetpack Components (Compose Optional)

**Backend & Database:**
- Room Database (Local SQLite)
- Firebase (Optional - Cloud Sync, Analytics)
- SharedPreferences (User Settings)

**APIs & Services:**
- YouTube Data API v3
- Notifications & Alarms (Android Services)
- WorkManager (Background Tasks)
- GeoLocation (Prayer Times)

**Build & Deployment:**
- Gradle Build System
- GitHub Actions (CI/CD)
- Android Studio

---

## 📋 Getting Started

### Prerequisites
- Android Studio (Latest)
- JDK 11+
- Android SDK 26+
- Gradle 8.0+

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/OxygenBD/Exthon.git
cd Exthon
```

2. **Open in Android Studio:**
```bash
# Or directly open with Android Studio
studio .
```

3. **Configure YouTube API:**
   - Get API key from [Google Cloud Console](https://console.cloud.google.com/)
   - Add to `local.properties` or `secrets.properties`

4. **Build the project:**
```bash
./gradlew build
```

5. **Run on emulator or device:**
```bash
./gradlew installDebug
```

---

## 🚀 Building APK

### Debug APK
```bash
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk
```

### Release APK (Signed)
```bash
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release.apk
```

### Automatic Build Script
```bash
chmod +x scripts/build-apk.sh
./scripts/build-apk.sh
```

---

## 📊 Database Schema

### Tables
- **Playlists** - YouTube playlist metadata
- **Videos** - Video entries with watch progress
- **Schedules** - Daily timetable entries
- **PrayerTimes** - Prayer time configurations
- **PomodoroSessions** - Timer sessions for activities
- **Notes** - Rich text notes with groups
- **Achievements** - User achievements and milestones
- **BlockedApps** - Blocked app configurations
- **Settings** - User preferences

---

## 🔄 CI/CD Pipeline

GitHub Actions workflows automate:
- ✅ Build on every commit
- ✅ Run unit tests
- ✅ Lint code quality checks
- ✅ Generate signed APK for releases
- ✅ Auto-deploy to GitHub Releases

---

## 📤 Data Export/Import

### Supported Formats
- `.exthon` - Custom compressed format (Recommended)
- `.json` - Human-readable format
- `.csv` - Schedule/Notes export

### Export Data
```bash
python scripts/export-data.py --format exthon --output backup.exthon
```

### Import Data
- Use in-app Import feature
- Select backup file format
- Merge or replace options

---

## 🎖️ Achievement System

### Categories
1. **Productivity** - Pomodoro sessions, task completion
2. **Reading** - Reading time milestones
3. **Prayer** - Prayer consistency tracking
4. **Wellness** - Social media/porn blocking streaks
5. **Weekly/Monthly/Yearly** - Time-based milestones

### Reward Tiers
- 🥉 Bronze - Week completion
- 🥈 Silver - Month completion
- 🥇 Gold - Year completion
- 💎 Diamond - Special achievements

---

## 🔒 Digital Wellness Features

### Blocking Capabilities
- Adult content filter
- Social media app lock with timer
- Notification control
- Screen time limits
- Customizable block schedules

### Tracking
- Daily active time
- App usage statistics
- Improvement trends
- Wellness achievements

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 🐛 Bug Reports & Issues

Found a bug? Have a feature request?
- [Open an Issue](https://github.com/OxygenBD/Exthon/issues/new)
- Include reproduction steps
- Attach logs if applicable
- Suggest fixes or improvements

---

## 📝 License

This project is licensed under the **GNU General Public License v3.0** - see [LICENSE](LICENSE) file for details.

---

## 👥 Author

**OxygenBD** - [GitHub Profile](https://github.com/OxygenBD)

---

## 🙏 Acknowledgments

- YouTube Data API documentation
- Android Jetpack libraries
- Material Design community
- Prayer time API providers

---

## 📞 Support

For questions or support:
- 📧 Email: support@exthon.app (if applicable)
- 💬 GitHub Discussions
- 🐦 Twitter/X: [@OxygenBD](https://twitter.com/OxygenBD)

---

**Last Updated:** June 12, 2026
**Status:** 🚧 In Development
**Target Release:** v1.0.0
