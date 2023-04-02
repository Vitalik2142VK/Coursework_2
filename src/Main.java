import taskList.Task;
import taskList.TaskService;
import taskList.Type;
import taskList.repeatability.*;

import java.util.Scanner;

public class Main {
    //----------Вспомогательные методы----------//

    public static Type chooseTask(Scanner inInt) {
        int exit = 3;
        do {
            switch (inInt.nextInt()) {
                case 1: return Type.Work;
                case 2: return Type.Personal;
                default:
                    exit--;
                    if (exit != 0) {
                        System.out.println("Не корректно выбран пункт. Попробуйте еще раз. Внимание! Через '" + exit + "' будет произведен выход из программы.");
                    }
            }
        } while (exit != 0);
        return null;
    }

    public static Repeatability choseRepeatability(Scanner inInt) {
        int exit = 4;
        do {
            switch (inInt.nextInt()) {
                case 1: return new OneTimeTask();
                case 2: return new DailyTask();
                case 3: return new WeeklyTask();
                case 4: return new MonthlyTask();
                case 5: return new YearlyTask();
                default:
                    exit--;
                    if (exit != 0) {
                        System.out.println("Не корректно выбран пункт. Попробуйте еще раз. Внимание! Через '" + exit + "' будет произведен выход из программы.");
                    }
            }
        } while (exit != 0);
        return null;
    }

    public static boolean exit() {
        int num = 4, choice;

        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\nВыйти из программы?\nВведите номер пункта:\n1.Да\t2.Нет");
            choice = in.nextInt();
            if(choice == 1 || num == 0)
                return false;
            else if (choice == 2)
                return true;
            else {
                num--;
                if (num > 0) {
                    System.out.println("Не корректно введен номер пункта. Попробуйте еще раз. Внимание! Через '" + num + "' раз будет произведен выход из программы.");
                }
            }
        } while (true);
    }

    //----------Основные методы----------//

    public static void addTask(TaskService ts) {
        String title, description;
        Type type;
        Repeatability repeatability;

        Scanner inStr = new Scanner(System.in);
        Scanner inInt = new Scanner(System.in);

        System.out.println("Введите название задачи:");
        title = inStr.next();

        System.out.println("Введите номер типа задачи:\n1.Рабочая\n2.Персональная");
        type = chooseTask(inInt);
        if (type == null)
            return;

        System.out.println("Введите номер частоты повторения задачи:\n1.Однократная\n2.Ежедневная\n3.Еженедельная\n4.Ежемесячная\n5.Ежегодная");
        repeatability = choseRepeatability(inInt);
        if (repeatability == null)
            return;

        System.out.println("Введите описание задачи:");
        description = inStr.next();

        Task task = new Task(title, description, type, repeatability);
        ts.addTask(task);
    }

    public static void removeTask(TaskService ts) {
        int id;
        Scanner inInt = new Scanner(System.in);

        System.out.println("Введите индекс задачи, которую хотите удалить:");
        id = inInt.nextInt();
        ts.remove(id);
    }

    public static void getAllTasks(TaskService ts) {
        for (final var value : ts.getAllTasks()) {
            System.out.println("\n----------#####----------\n");
            System.out.println(value);
        }
        System.out.println("\n----------#####----------\n");
    }

    //----------Точка входа в программу----------//

    public static void main(String[] args) {

        Scanner inInt = new Scanner(System.in);

        TaskService taskSer = new TaskService();

        System.out.println("Добро пожаловать в задачник!");

        do {
            System.out.println("\n----------#####----------\n");
            System.out.println("Введите номер пункта:\n1.Добавить задачу;\n2.Удалить задачу по указанному индексу;" +
                    "\n3.Показать список задач на предстоящий день;\n4.Показать все задачи;\n5.Выйти из программы.");
            switch (inInt.nextInt()) {
                case 1: addTask(taskSer); break;
                case 2: removeTask(taskSer); break;
                case 3:
                    System.out.println("В процессе!");; break;
                case 4: getAllTasks(taskSer); break;
                case 5: break;
                default:
                    System.out.println("\nНе корректно введен номер пункта!");
            }

        } while (exit());
    }
}