# Отчётные документы по итогам тестирования
## Краткое описание:
Составлен [план](https://github.com/anastasiacat/diploma/blob/main/Plan.md) чтобы протестировать функции [приложения для оплаты тура](https://github.com/netology-code/qa-diploma#бизнес-часть). Проведена работа по автоматизации тестирования этого приложения. Были написаны позитивные и негативные [UI-тесты](https://github.com/anastasiacat/diploma/blob/main/src/test/java/UITest.java) и [API-тесты](https://github.com/anastasiacat/diploma/blob/main/src/test/java/APITest.java) с использованием баз данных MySQL и PostgreSQL.
## Тест-кейсы
- Всего: 46 тест-кейсов
- Успешные: 32 (69,56%)
- Неуспешные: 14 (30,44%)

![изображение](https://github.com/anastasiacat/diploma/assets/119497966/91893f32-1b41-4277-a5f1-7be56b7ccc65)

## Найденные баги
1. [При вводе данных неактивной карты (DECLINE) совершается операция покупки](https://github.com/anastasiacat/diploma/issues/1)
2. [При вводе данных неизвестной карты появляются два сообщения: об ошибке и об успешной операции](https://github.com/anastasiacat/diploma/issues/2)
3. [При вводе только имени в поле "Владелец" активной карты (APPROVED) совершается операция покупки](https://github.com/anastasiacat/diploma/issues/3)
4. [При вводе одной буквы в поле "Владелец" активной карты (APPROVED) совершается операция покупки](https://github.com/anastasiacat/diploma/issues/5)
5. [При вводе цифр в поле "Владелец" активной карты (APPROVED) совершается операция покупки](https://github.com/anastasiacat/diploma/issues/6)
6. [При вводе специальных символов в поле "Владелец" активной карты (APPROVED) совершается операция покупки](https://github.com/anastasiacat/diploma/issues/7)
7. [При совершении операции покупки с пустым полем CVC выводится лишнее сообщение об ошибке](https://github.com/anastasiacat/diploma/issues/4)
## Рекомендации
- Исправить найденные баги
- Сделать обязательную валидацию поля "Владелец"
