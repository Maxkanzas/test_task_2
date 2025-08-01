@echo off
echo 🚀 Weather API Test Framework
echo ===============================

REM Проверка наличия Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java не найдена. Пожалуйста, установите Java 11+
    pause
    exit /b 1
)

REM Проверка наличия Maven
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Maven не найден. Пожалуйста, установите Maven
    pause
    exit /b 1
)

echo ✅ Java и Maven найдены

REM Запуск тестов
echo 🧪 Запуск тестов...
mvn clean test

REM Проверка результата
if %errorlevel% equ 0 (
    echo ✅ Тесты выполнены успешно!
    
    REM Генерация Allure отчёта если установлен
    allure --version >nul 2>&1
    if %errorlevel% equ 0 (
        echo 📊 Генерация Allure отчёта...
        allure generate target/allure-results --clean
        echo 🌐 Запуск Allure отчёта в браузере...
        allure serve target/allure-results
    ) else (
        echo ⚠️  Allure не установлен. Для генерации отчётов установите Allure CLI
        echo 📁 Cucumber отчёты доступны в: target/cucumber-reports/
    )
) else (
    echo ❌ Тесты завершились с ошибками
    pause
    exit /b 1
)

pause 