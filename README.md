# NavigationSandBoxForInterview
Первый SandBox для прохождения интервью из серии -то, что у тебя спросят, но то что ты никогда не будешь использовать
Часто интервью сводятся к вольному полету мысли интервьюеров, кое какие графы я запоминаю и далее буду описывать их как сценарии, в коде можно ориентироваться
по метке конкретного сценария

Я считаю активити, фрагменты и viewmodels в андройде контроллерами и отношусь к ним исключительно
как к контроллерам presentation слоя.
Таким образом навигация - смена или дополнительный вызов нового контроллера на вершину стека
--------------------------
Дано: 2 активити NavigationPerControllerFirst, NavigationPerControllerSecond
-1-
Навигация с first на second
D/TAG_FIRST_ACTIVITY: onStart
D/TAG_FIRST_ACTIVITY: onResume
D/TAG_FIRST_ACTIVITY: Click
D/TAG_FIRST_ACTIVITY: onPause
D/TAG_FIRST_ACTIVITY: onStop
Click Back
D/TAG_FIRST_ACTIVITY: onStart
D/TAG_FIRST_ACTIVITY: onResume
-2-
Навигация с first на second, вызываем метод finish
D/TAG_FIRST_ACTIVITY: onStart
D/TAG_FIRST_ACTIVITY: onResume
D/TAG_FIRST_ACTIVITY: Click
D/TAG_FIRST_ACTIVITY: onPause
D/TAG_FIRST_ACTIVITY: onStop
D/TAG_FIRST_ACTIVITY: onDestroy
Click Back
Выходим в скрин телефона
-3-
Навигация с передачей данных
-4-
Если мы хотим получить результат от активити, которая выше по стаку
-5-
Запустили 1 активити, перешли на 2 активити, нажали кнопку, повращали экран, данные потеряны
Вообще тут множество вариантов:
1) Сохранить данные в preference, не описываю т.к. уже раз 100 это делал
2) Сохранить данные в бд, не описываю т.к. уже раз 100 это делал
3) Сохранить данные во viewModel:
- Сохранить state в stateFlow -5.1-
- Тригернуть sharedFlow -5.2-
- LiveData -5.3- надо будет не забыть описать
4) onSaveInstanceState(Bundle)  -5.4-
-6-
А что если мы хотели бы тригернуть несколько наблюдателей
//заметка
жизненный цикл viewModel будет прерван только когда вызывается метод finish того контроллера, который
названачен owner-ом viewModel
