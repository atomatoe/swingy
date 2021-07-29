# avaj-launcher

<p align="center"><img src="" alt="Здесь должно быть фото"</p>

________________________________________________________________

#### <p align=center> "Введение в разработку графического интерфейса"</p> ####
________________________________________________________________


### История:
Группа хипстеров-предпринимателей хочет запустить новую текстовую рпг. Поскольку они не верят, что красивая графика важна, и считают, что игра (техническая часть) - это самый важный аспект, они решили вложить свои деньги в это направление и сделать игру текстовой. 
Они даже хотят выпустить ее в 2 этапа:
- Первый этап для хардкорных хипстеров, который будет основан на консолях.
- Вторая фаза для обычных хипстеров, которая также будет иметь простой графический интерфейс для приема пользовательских данных и ввода данных.

Они приходят в ваш магазин Java, потому что знают, что получат лучший продукт, если будут работать с вами. Хотя они в основном заботятся о функциях, у них есть некоторые требования к программированию, которые должны быть реализованы в проекте:

- Соблюдение паттерна проектирования Model-View-Controller.
- Автоматизированная сборка с помощью Maven.
- Валидация пользовательского ввода на основе аннотаций.
Только хорошая и понятная реализация будет принята. Для этого она должна иметь чистый дизайн, будет легко читаться и пониматься вашими коллегами и будет легко изменить в случае изменения требований.
________________________________________________________________

## Описание задачи:

Вам предстоит реализовать минималистичную текстовую RPG игру и применить лучшие практики, подходящие для решения этой задачи. В конце проекта вы поймете, как абстрагировать пользовательский интерфейс.

## Стек:
- Java
- Maven
- SWING, GUI
- Postgresql
- MVC
- Javax, pack validation

### V.1 Игровой процесс
У игрока может быть несколько героев разных типов. Мы оставляем на ваше усмотрение название героев и настраивать различные начальные статы между ними. Когда игрок начинает у него есть 2 варианта:
- Создать героя
- Выбрать ранее созданного героя.

В любом случае, игрок может видеть статистику героя:

- Имя героя
- класс героя
- Уровень
- Опыт
- Атака
- Защита
- Очки попадания

На статистику героя влияют уровень героя и артефакты. Существует 3 типа артефактов:

- Оружие - увеличивает атаку
- Броня - увеличивает защиту
- Шлем - увеличивает хит-пойнты

После выбора героя начинается собственно игра. Герою необходимо перемещаться по квадратной карте, размер которой рассчитывается по формуле (уровень-1)*5+10 -(герой 7 уровня будет будет помещен на карту размером 39X39. Начальная позиция героя находится в центре карты. Он побеждает в игре, если достигнет одной из границ карты. Каждый ход он может перемещаться на одну позицию в одном из 4 четырех направлений:
- Север
- Восток
- Юг
- Запад

При создании карты злодеи различной силы будут распределены по карте случайным образом по карте. Когда герой перемещается на позицию, занятую злодеем, у него есть 2 варианта действий:
- Сражаться, что вовлекает его в битву со злодеем.
- Бежать, что дает ему 50% шанс сбежать. Если шансы не на его стороне, он должен сразиться со злодеем.

Вам нужно будет смоделировать битву между героем и монстром и представить пользователю пользователю результат битвы. Мы оставляем за вами право найти хороший алгоритм моделирования, который на основе статистики героя и монстра определит, кто победит. Вы можете включить небольшой компонент "удачи" в алгоритм, чтобы сделать игру более увлекательной. Если герой проигрывает битву, он умирает и теряет миссию.

Если герой побеждает в битве, он получает:
- Очки опыта, в зависимости от силы злодея. Конечно, он повышает свой уровень, если
достигнет опыта следующего уровня.
- Артефакт, который он может оставить себе или бросить. Конечно, победа в битве не гарантирует, что артефакт выпадет, и качество артефакта также варьируется
в зависимости от силы злодея.

Повышение уровня происходит по следующей формуле уровень*1000+(уровень-1)ˆ2 * 450. Таким образом, опыт, необходимый для повышения уровня, будет выглядеть следующим образом:

- Level 1 - 1000 XP
- Level 2 - 2450 XP
- Level 3 - 4800 XP
- Level 4 - 8050 XP
- Level 5 - 12200 XP

### V.2 Особенности
Игра может быть запущена в 2 режимах (консольный и графический). Герои пользователя и их состояние будут сохранены, когда пользователь выйдет из игры, в
текстовом файле. При запуске игры ваша программа будет загружать героев из этого файла.

### V.3 Валидация
Вам потребуется интегрировать в свой проект библиотеку стороннего производителя, чтобы обеспечить проверку на основе аннотаций. Мы настоятельно рекомендуем вам использовать библиотеку, которая реализует спецификацию javax.validation. Вы не допустите, чтобы нештатный пользовательский ввод нарушил поведение игры. Валидация будет показана пользователю.

### V.4 Бонус
Бонусные баллы будут начислены, если:

- Вы сохраняете героев пользователя в реляционной базе данных, а не в текстовом файле.
- Вы можете переключаться между консольным видом и видом графического интерфейса во время выполнения, не закрывая игру.
________________________________________________________________

<details>
<summary>Полезные ссылки</summary>
<p></p>
<p><a href="https://github.com/atomatoe">Мой github</a></p>
<p><a href="http://java-online.ru/libs-swing.xhtml">Java Swing</a></p>
<p><a href="https://habr.com/ru/post/68318/">Javax Validation для начинающих</a></p>
<details>
<summary>Дополнительно</summary>
<p></p>
<p>Советую не сразу приступать к выполнению проекта. Потренируйтесь. Поработайте с Java Swing, задайте себе вопрос - как вы будете делать GUI - "фронтовую" часть. Большую часть времени в этом проекте я потратил на разбор работы GUI интерфейса и его разметки.</p>
<p><a href="http://www.quizful.net/post/swing-layout-managers">Layout Swing. Кратко.</a></p>
<p><a href="https://spec-zone.ru/RU/Java/Tutorials/uiswing/layout/gridbag.html">GridBagLayout. Как использовать</a></p>
<p><a href="https://ipsoftware.ru/posts/gridbaglayout/">Еще о GridBagLayot</a></p>

</details>
  
</details>
