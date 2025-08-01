#!/bin/bash

echo "🚀 Weather API Test Framework"
echo "==============================="

# Проверка наличия Java
if ! command -v java &> /dev/null; then
    echo "❌ Java не найдена. Пожалуйста, установите Java 11+"
    exit 1
fi

# Проверка наличия Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven не найден. Пожалуйста, установите Maven"
    exit 1
fi

echo "✅ Java и Maven найдены"

# Запуск тестов
echo "🧪 Запуск тестов..."
mvn clean test

# Проверка результата
if [ $? -eq 0 ]; then
    echo "✅ Тесты выполнены успешно!"
    
    # Генерация Allure отчёта если установлен
    if command -v allure &> /dev/null; then
        echo "📊 Генерация Allure отчёта..."
        allure generate target/allure-results --clean
        echo "🌐 Запуск Allure отчёта в браузере..."
        allure serve target/allure-results
    else
        echo "⚠️  Allure не установлен. Для генерации отчётов установите Allure CLI"
        echo "📁 Cucumber отчёты доступны в: target/cucumber-reports/"
    fi
else
    echo "❌ Тесты завершились с ошибками"
    exit 1
fi 