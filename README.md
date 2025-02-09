# IBroker - Chrome Extension for Real Estate Alerts

## 📌 Overview
IBroker is a Chrome extension designed for **bina.az**, a real estate listing platform. It helps users automate property searches and get notifications about new listings or price changes through a Telegram bot.

## 🔧 Features
### 1️⃣ Find a Suitable House ("Mənə Uyğun Evi Tap")
- Users authenticate through Telegram, receiving a unique **identification number**.
- They input a **search URL** (filtered based on their criteria) into the extension.
- The extension checks the URL every **minute** to detect new listings.
- If a new listing is found, it is sent to the user via the Telegram bot.
- Users can **filter out agency listings** and search only for owner-posted properties.
- AI (via Gemini) scans property descriptions for specific keywords (e.g., "urgent") and only sends listings that match.

### 2️⃣ Price Drop Notifications ("Qiymət Endi! Bildirişi Al")
- Users save a specific listing along with its **current price**.
- The extension checks the listing every **5 minutes**.
- If the price drops, a **Telegram notification** is sent to the user.

## 🏗️ Technologies Used
- **Java (Spring Boot)**: Backend development
- **Feign Client**: Communication with Telegram API
- **JSoup**: Web scraping
- **Gemini AI**: Text analysis for filtering listings
- **Spring Scheduler**: Periodic tasks execution
- **ModelMapper**: DTO to Entity conversion
- **PostgreSQL**: Database

## 🔍 How It Works
### General Service (`GeneralService`)
- Saves search details in the database.
- Retrieves stored searches.
- Deletes searches based on chat ID.
- **Scheduled Task:** Scrapes new listings every **minute**, applies filters, and sends matches via Telegram.

### AI Processing (`GeminiService`)
- Sends property descriptions to **Gemini AI** for keyword analysis.
- Returns `true` if a listing matches the user’s keywords.

### Price Monitoring (`SpecificService`)
- Stores and retrieves listings for price monitoring.
- **Scheduled Task:** Checks listing prices every **5 minutes**, notifies users on price drops.

### Telegram Communication (`TelegramService`)
- Handles Telegram bot messages.
- Assigns unique **identification numbers** to users.
- Sends notifications when conditions are met.



