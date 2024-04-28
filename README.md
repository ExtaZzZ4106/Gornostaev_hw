# Симулятор лифта

Это простое консольное приложение на Java, которое симулирует работу лифта. Пользователь может ввести номер этажа, на который он хочет подняться или опуститься, и программа будет отображать движение лифта в консоли.

## Функциональность

- Программа запрашивает у пользователя номер целевого этажа.
- Пользователь может ввести номер этажа от 0 до 10.
- Если введенный номер этажа некорректен, программа выводит соответствующее сообщение.
- Если пользователь вводит 'q', программа завершается.
- При движении лифта на консоль выводится информация о текущем этаже.
- Между выводом информации о каждом этаже есть задержка в 1 секунду для имитации реального движения лифта.

## Технологии

Проект написан на Java 8 и использует:

- Циклы и условные конструкции
- Обработку ввода пользователя с помощью Scanner
- Обработку исключений (NumberFormatException)
- Метод Thread.sleep() для имитации задержки

## Запуск

Чтобы запустить приложение, скомпилируйте файлы Java и запустите главный класс Main:
javac *.java
java org.example.Main
