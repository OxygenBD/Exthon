# Exthon - Productivity & Wellness App

A comprehensive Android application for managing YouTube playlists, daily schedules, prayer times, notes, and personal productivity tracking with achievement-based motivation system.

## Features

### 📺 YouTube Playlist Manager
- Add multiple YouTube playlists
- Track watch progress (last watched video with timestamp)
- Display playlist title with side-by-side video grid
- Resume watching from where you left off
- Auto-save viewing history

### ⏰ Daily Schedule & Timetable
- Customizable daily timetable
- Automatic prayer times (5x daily) based on location/timezone
- Alarm and notification system
- Time management with hourly breakdown

### 🍅 Pomodoro & Time Tracking
- Multi-category Pomodoro timer (Reading, Writing, Prayer, Play, Work, etc.)
- Automatic daily schedule integration
- Customizable work/break intervals
- Visual progress tracking

### 📝 Advanced Notes System
- Rich text editor (Bold, Italic, Underline, Strikethrough, Hyperlinks)
- Note organization by groups/categories
- Code block support
- Embedded calculator
- Auto-save functionality

### 🛡️ Wellness & Blocking Features
- Harmful content blocker (Porn sites, Social media)
- Website/app blocking with custom rules
- Achievement unlocking through consistent blocking
- Usage analytics and reports

### 🏆 Achievement System
- Weekly milestones and badges
- Monthly and yearly progress tracking
- Achievement categories (Focus, Prayer, Reading, Blocking, etc.)
- Motivation boosters and rewards
- Leaderboard system

### 📤 Import/Export
- Import/Export reading schedules as `.exthon` file format
- Backup and restore data
- Cloud sync capability
- Shareable schedule templates

### 🌳 Wellness Features (Forest-inspired)
- Tree growth metaphor during focused sessions
- Virtual forest creation
- Motivational streaks
- Social sharing of achievements

## Technology Stack

- **Platform**: Android (Kotlin/Java)
- **Architecture**: MVVM + Clean Architecture
- **Database**: Room Database
- **Networking**: Retrofit + OkHttp
- **UI**: Material Design 3
- **APIs**: YouTube Data API, Prayer Times API

## Project Structure

```
Exthon/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/oxygenbd/exthon/
│   │   │   │   ├── ui/
│   │   │   │   ├── viewmodel/
│   │   │   │   ├── data/
│   │   │   │   ├── domain/
│   │   │   │   ├── util/
│   │   │   │   └── MainActivity.kt
│   │   │   └── res/
│   │   └── test/
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## Installation

1. Clone the repository
2. Open in Android Studio
3. Configure YouTube API key in `local.properties`
4. Build and run on Android 8.0+ device

## Contributing

See CONTRIBUTING.md for guidelines

## License

GNU General Public License v3.0