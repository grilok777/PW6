# Демонстрація асинхронності в Spring

## Мета
Цей проєкт демонструє використання асинхронності в Spring Framework за допомогою анотацій `@Scheduled` та `@EnableAsync`.

## Опис
Програма реалізує виконання задач у два способи:
1. **Заплановані задачі**: Виконуються кожні 10 секунд, якщо користувач обирає відповідь "Y".
2. **Асинхронні задачі**: Виконуються з випадковим інтервалом від 1 до 10 секунд.

## Функціональність
- Після запуску програми користувач вводить "Y" або "N".
  - Вибір "Y": Виконуються заплановані задачі кожні 10 секунд.
  - Вибір "N": Заплановані задачі не виконуються.
- У будь-якому випадку запускається асинхронна задача, яка виконується з випадковим інтервалом.
- Завершення програми відбувається після виконання 4 асинхронних задач та, за необхідності, 4 запланованих задач.

## Використання
1. Запустіть програму.
2. Введіть "Y" для запуску запланованих задач або "N", щоб пропустити їх.
3. Спостерігайте за виконанням асинхронних і запланованих задач у консольному виводі.

## Приклад виводу
Виберіть Y/N для запуску задачі кожні 10 секунд (Y/N): Y

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 5 секунд.

Виконується запланована задача кожні 10 секунд, час від запуску: 10 секунд.

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 10 секунд.

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 12 секунд.

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 16 секунд.

Виконується запланована задача кожні 10 секунд, час від запуску: 20 секунд.

Виконується запланована задача кожні 10 секунд, час від запуску: 30 секунд.

Виконується запланована задача кожні 10 секунд, час від запуску: 40 секунд.

Вимкнення програми...

АБО

Виберіть Y/N для запуску задачі кожні 10 секунд (Y/N): N

Поточна задача пропущена.

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 10 секунд.

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 14 секунд.

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 22 секунд.

Виконується асинхронна задача з випадковим інтервалом, час від запуску: 26 секунд.

Вимкнення програми...