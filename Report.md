# Отчётные документы по итогам тестирования
## Краткое описание:
Составлен [план](https://github.com/anastasiacat/diploma/blob/main/Plan.md) чтобы протестировать функции [приложения для оплаты тура](https://github.com/netology-code/qa-diploma#бизнес-часть). Проведена работа по автоматизации тестирования этого приложения. Были написаны позитивные и негативные [UI-тесты](https://github.com/anastasiacat/diploma/blob/main/src/test/java/UITest.java) и [API-тесты](https://github.com/anastasiacat/diploma/blob/main/src/test/java/APITest.java) с использованием баз данных MySQL и PostgreSQL.
## Тест-кейсы
- Всего: 46 тест-кейсов
- Успешные: 32 (69,56%)
- Неуспешные: 14 (30,44%)

![изображение](https://github.com/anastasiacat/diploma/assets/119497966/0eca2522-4756-4110-9e38-b2b41ac1449f)

## Найденные баги
1. [При вводе данных неактивной карты (DECLINE) совершается операция покупки](https://github.com/anastasiacat/diploma/issues/1)
2. [При вводе данных неизвестной карты появляются два сообщения: об ошибке и об успешной операции](https://github.com/anastasiacat/diploma/issues/2)
3. [При вводе невалидных данных в поле "Владелец" активной карты (APPROVED) совершается операция покупки](https://github.com/anastasiacat/diploma/issues/3)
4. [При совершении операции покупки с пустым полем CVC выводится лишнее сообщение об ошибке](https://github.com/anastasiacat/diploma/issues/4)
## Рекомендации
- Исправить найденные баги
- Сделать обязательную валидацию поля "Владелец"
