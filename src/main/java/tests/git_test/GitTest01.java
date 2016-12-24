package tests.git_test;

/**
 * Created by Pihnastyi.O on 12/22/2016.
 */
public class GitTest01 {
    //-------------------------------------8
/*
    This will reset the current branch head to the selected commit, and update the working tree  and the index according to the select mode:
    soft: Files won't change, differences will be staged for commit.
    Mixed: Files won't change, differences will be staged.
    Hard: Files will be reverted to the state of the selected commit. Any local changes will be lost.
    Keep: Files will be reverted to the state of the selected commit. But local changes will be kept intact.

    Это приведет к сбросу текущего головку ветви к выбранной фиксации, и обновить рабочее дерево и индекс в соответствии с выбора режима:
    soft: Файлы не изменится, различия будет поставлена для фиксации.
    mixed: Файлы не изменится, различия будет поставлена.
    hard:  Файлы будут восстановлены до состояния выбранной фиксации. Любые локальные изменения будут потеряны.
    keep:  Файлы будут восстановлены до состояния выбранной фиксации. Но местные изменения будут сохранены.


    Как правильно merge 2-x веток
    Задача: Я стою на ветке master. Мне надо изменения перелить с ветки www на ветку master.
    Решение: 1. [Стою на master]->[Выбираю (Local Brenches)== WWW]->[Merge]

    Как правильно исправить комментарий для коммитф
    Задача: Я стою на ветке master. Залит коммит и мне вдруг понадобилось исправить коментарий к коммиту.
    Решение: 1. [Стою на master]->[Становлюсь на коммите, в котором надо изменить коментарий]->[Опускаюсь на один коммит ниже (синяя полоса подсвечивает нижний коммит)]->
                ->[Reset Current Branch to Here]->[soft(описание смотри ниже)]->[затем сделать коммит с новым коментарием. Старого не будет в коммитеах]




*/
}
