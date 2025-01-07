📊 Portfolio Tracker Application

A simple and efficient tool to manage and analyze your stock portfolio with real-time data integration.

🌟 Features

Add, View, Edit, and Delete Stock Holdings: Manage your portfolio effortlessly.
Real-Time Portfolio Value Tracking: Get live updates based on current stock prices.
Dashboard Metrics: View total portfolio value, top-performing stocks, and portfolio distribution.


Dashboard View
An intuitive dashboard showcasing portfolio metrics.

Stock Management
Easily add, edit, or delete stock details.


💻 Tech Stack
## Frontend
React

## Backend
Java with Spring Boot
RESTful API design with proper exception handling

## Database
PostgreSQL
Schema designed to store stock details and user portfolios

## Real-Time Data
Integrated with Marketstack API for live stock prices


📘 Installation Guide

Clone the repository:
git clone https://github.com/Jeet1811/Portfolio-Tracker.git  

## Frontend Setup

Navigate to the project directory:

cd frontend

Install dependencies:
npm install  

Start the development server:
npm start  

## Backend Setup

cd backend  
Configure the application.properties file with your PostgreSQL credentials and Marketstack API key.
Build and run the application:

mvn spring-boot:run 
(maven should be installed in your system)

📈 Usage
Add Stocks: Enter stock details like ticker, buy price, and quantity.
Track Performance: Monitor portfolio value and stock performance in real-time.
Edit/Delete Stocks: Modify or remove stock holdings as needed.