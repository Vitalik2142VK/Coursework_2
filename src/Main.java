import taskList.*;
import taskList.exeptions.*;
import taskList.repeatability.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    //----------Вспомогательные методы----------//

    public static Type chooseTask(Scanner inInt) throws IncorrectArgumentException {
        int exit = 3;
        do {
            switch (inInt.nextInt()) {
                case 1: return Type.WORK;
                case 2: return Type.PERSONAL;
                default:
                    exit--;
                    if (exit != 0) {
                        System.out.println("Не корректно выбран пункт. Попробуйте еще раз. Внимание! Через '" + exit + "' будет произведен выход из программы.");
                    }
            }
        } while (exit != 0);
        throw new IncorrectArgumentException("Не корректно выбрана частота повторяемости задачи.");
    }

    public static TypeRepeatability choseRepeatability(Scanner inInt) throws IncorrectArgumentException {
        int exit = 4;
        do {
            switch (inInt.nextInt()) {
                case 1: return TypeRepeatability.ONETIME;
                case 2: return TypeRepeatability.DAILY;
                case 3: return TypeRepeatability.WEEKLY;
                case 4: return TypeRepeatability.MONTHLY;
                case 5: return TypeRepeatability.YEARLY;
                default:
                    exit--;
                    if (exit != 0) {
                        System.out.println("Не корректно выбран пункт. Попробуйте еще раз. Внимание! Через '" + exit + "' будет произведен выход из программы.");
                    }
            }
        } while (exit != 0);
        throw new IncorrectArgumentException("Не корректно выбран тип задачи.");
    }

    public static boolean exit() {
        int num = 4, choice;

        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\nВыйти из программы?\nВведите номер пункта:\n1.Да\t2.Нет");
            if (in.hasNextInt()) {
                choice = in.nextInt();
            } else
                choice = 1;

            if(choice == 1 || num == 0) {
                System.out.println("Спасибо за работу. Всего доброго!");
                return false;
            } else if (choice == 2) {
                return true;
            }
            else {
                num--;
                if (num > 0) {
                    System.out.println("Не корректно введен номер пункта. Попробуйте еще раз. Внимание! Через '" + num + "' раз будет произведен выход из программы.");
                }
            }
        } while (true);
    }

    //----------Основные методы----------//

    public static void addTask(TaskService ts) throws IncorrectArgumentException {
        Scanner in = new Scanner(System.in);

        String title, description;
        Type type;
        TypeRepeatability typeRepeatability;

        System.out.println("Введите название задачи:");
        title = in.nextLine();

        System.out.println("Введите описание задачи:");
        description = in.nextLine();

        System.out.println("Введите номер типа задачи:\n1.Рабочая\n2.Персональная");
        type = chooseTask(in);

        System.out.println("Введите номер частоты повторения задачи:\n1.Однократная\n2.Ежедневная\n3.Еженедельная\n4.Ежемесячная\n5.Ежегодная");
        typeRepeatability = choseRepeatability(in);

        Task task = new Task(title, description, type, typeRepeatability);
        ts.addTask(task);

        System.out.println("Задача добавлена.");
    }

    public static void removeTask(TaskService ts) {
        int id;
        Scanner in = new Scanner(System.in);

        System.out.println("Введите индекс задачи, которую хотите удалить:");
        id = in.nextInt();

        final Task task = ts.remove(id);

        System.out.println("Задача | '" + task.getTitle() + "' созданная - " + task.getDateTime() + " | удалена.");
    }

    private static void getAllByDate(TaskService ts, LocalDate localDate) {
        Collection<Task> taskList = ts.getAllByDate(localDate);

        if (taskList.size() == 0) {
            throw new TaskNotFoundException("Задачи на сегодня не найдены.");
        }

        System.out.println("\n-----#####-----\n");
        for (final var value : taskList) {
            System.out.println(value);
            System.out.println("\n-----#####-----\n");
        }
    }

    private static void getAllGroupByDate(TaskService ts) {
        Map<LocalDate, List<Task>> dataGroupMap = ts.getAllGroupByDate();

        if (dataGroupMap.size() == 0) {
            throw new TaskNotFoundException("Задач нет!");
        }

        for (final var list : dataGroupMap.values()) {
            System.out.println("\nГруппа задач по дате повтора: " + list.get(0).getFirstRepetitionDate() + "\n-----#####-----");
            for (final var value : list) {
                System.out.println(value);
                System.out.println("-----#####-----");
            }
        }
    }

    public static void updateTitle(TaskService ts) {
        Scanner in = new Scanner(System.in);

        String newTitle;
        int newId;

        System.out.println("Введите индек задачи:");
        newId = in.nextInt();
        in.nextLine();
        System.out.println("Введите новое название задачи:");
        newTitle = in.nextLine();

        ts.updateTitle(newId, newTitle);
        System.out.println("Название задачи изменено.");
    }

    public static void updateDescription(TaskService ts) {
        Scanner in = new Scanner(System.in);

        String newDescription;
        int newId;

        System.out.println("Введите индек задачи:");
        newId = in.nextInt();
        in.nextLine();
        System.out.println("Введите новое название задачи:");
        newDescription = in.nextLine();

        ts.updateDescription(newId, newDescription);
        System.out.println("Описание задачи изменено.");
    }

    private static void getRemovedTasks(TaskService st) {
        if(st.getRemovedTasks().size() == 0)
            throw new TaskNotFoundException("Удаленных задач нет.");
        for (final var value : st.getRemovedTasks()) {
            System.out.println("\n----------#####----------\n");
            System.out.println(value);
        }
        System.out.println("\n----------#####----------\n");
    }

    public static void fillTask(TaskService ts) {
        try {
            ts.addTask(new Task("Отремонтировать ноутбук", "Отнести в ремонт ноутбук.", Type.PERSONAL, TypeRepeatability.ONETIME));
            ts.addTask(new Task("Проверить почту", "Проверить почту на наличие писем и текщих задачь.", Type.WORK, TypeRepeatability.DAILY));
            ts.addTask(new Task("Сходить в магазин", "Вечером сходить в магазин, купить продукты из списка.", Type.PERSONAL, TypeRepeatability.DAILY));
            ts.addTask(new Task("Заполнить недельный отсчет", "Заполнить недельный отсчет, по выданному имуществу.", Type.WORK, TypeRepeatability.WEEKLY));
            ts.addTask(new Task("Провести инвентаризацию", "Провести ежемесячную инвентаризацию.", Type.WORK, TypeRepeatability.MONTHLY));
            ts.addTask(new Task("Поздравить с ДР", "Поздавить с днем рождения.", Type.PERSONAL, TypeRepeatability.YEARLY));
        } catch (IncorrectArgumentException | TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //----------Точка входа в программу----------//

    public static void main(String[] args) {
        Scanner in;

        int choice;

        TaskService taskSer = new TaskService();
        fillTask(taskSer);

        System.out.println("Добро пожаловать в ежедневник!");

        do {
            in = new Scanner(System.in);

            System.out.println("\n----------#####----------\n");
            System.out.println("Введите номер пункта:" +
                    "\n0.Тестирование;" +
                    "\n1.Добавить задачу;" +
                    "\n2.Удалить задачу по указанному индексу;" +
                    "\n3.Показать список задач на предстоящий день;" +
                    "\n4.Показать задачи сгруппированные по дате;" +
                    "\n5.Изменить заголовок задачи по индексу;" +
                    "\n6.Изменить описание задачи по индексу;" +
                    "\n7.Показать удаленные задачи;" +
                    "\n8.Выйти из программы.");
            try {

                if (in.hasNextInt()) {
                    choice = in.nextInt();
                } else {
                    throw new InputMismatchException();
                }

                switch (choice) {
                    case 0:
                        testTasks(taskSer);
                        break;
                    case 1:
                        addTask(taskSer);
                        break;
                    case 2:
                        removeTask(taskSer);
                        break;
                    case 3:
                        getAllByDate(taskSer, LocalDate.now());
                        break;
                    case 4:
                        getAllGroupByDate(taskSer);
                        break;
                    case 5:
                        updateTitle(taskSer);
                        break;
                    case 6:
                        updateDescription(taskSer);
                        break;
                    case 7:
                        getRemovedTasks(taskSer);
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("\nНе корректно введен номер пункта!");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Не корректно выбран пункт. Необходимо вводить целые числа.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } while (exit());
        in.close();
    }

    //----------Метод для тестирования----------//

    private static void testTasks(TaskService ts) throws TaskNotFoundException {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(0);
        localDate = localDate.plusWeeks(0);
        localDate = localDate.plusMonths(0);
        localDate = localDate.plusYears(1);

        System.out.println("\n------------------------------\nВыбранная дата:\t" +
                localDate + "\n------------------------------");

        getAllByDate(ts, localDate);
    }

}