import taskList.Task;
import taskList.TaskService;
import taskList.Type;
import taskList.exeptions.IncorrectArgumentException;
import taskList.exeptions.TaskNotFoundException;
import taskList.repeatability.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    //----------Вспомогательные методы----------//

    public static Type chooseTask(Scanner inInt) {
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
        return null;
    }

    public static TypeRepeatability choseRepeatability(Scanner inInt) {
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
        return null;
    }

    public static boolean exit() {
        int num = 4, choice;

        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\nВыйти из программы?\nВведите номер пункта:\n1.Да\t2.Нет");
            choice = in.nextInt();
            if(choice == 1 || num == 0) {
                System.out.println("Спасибо за работу. Всего доброго!");
                return false;
            }
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

    public static void addTask(TaskService ts) throws IncorrectArgumentException {
        String title, description;
        Type type;
        TypeRepeatability typeRepeatability;

        Scanner inStr = new Scanner(System.in);
        Scanner inInt = new Scanner(System.in);

        System.out.println("Введите название задачи:");
        title = inStr.next();

        System.out.println("Введите номер типа задачи:\n1.Рабочая\n2.Персональная");
        type = chooseTask(inInt);
        if (type == null)
            throw new IncorrectArgumentException("Не корректно выбран тип задачи.");

        System.out.println("Введите номер частоты повторения задачи:\n1.Однократная\n2.Ежедневная\n3.Еженедельная\n4.Ежемесячная\n5.Ежегодная");
        typeRepeatability = choseRepeatability(inInt);
        if (typeRepeatability == null)
            throw new IncorrectArgumentException("Не корректно выбрана частота повторяемости задачи.");

        System.out.println("Введите описание задачи:");
        description = inStr.next();

        Task task = new Task(title, description, type, typeRepeatability);
        ts.addTask(task);

        System.out.println("Задача добавлена.");
    }

    public static void removeTask(TaskService ts) throws TaskNotFoundException {
        int id;
        Scanner inInt = new Scanner(System.in);

        System.out.println("Введите индекс задачи, которую хотите удалить:");
        id = inInt.nextInt();

        if (ts.remove(id) == null)
            throw new TaskNotFoundException("Задачи с таким индексом не существует.");

        System.out.println("Задача удалена.");
    }

    private static void getAllByDate(TaskService taskSer) throws TaskNotFoundException {
        LocalDate localDate = LocalDate.now();
        Collection<Task> taskList = taskSer.getAllByDate(localDate);

        if (taskList.size() == 0) {
            throw new TaskNotFoundException("Задачи на сегодня не найдены.");
        }

        System.out.println("\n-----#####-----\n");
        for (final var value : taskList) {
            System.out.println(value);
            System.out.println("\n-----#####-----\n");
        }
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
            System.out.println("Введите номер пункта:" +
//                    "\n0.Добавить задачу;" +
                    "\n1.Добавить задачу;" +
                    "\n2.Удалить задачу по указанному индексу;" +
                    "\n3.Показать список задач на предстоящий день;" +
                    "\n4.Показать все задачи;" +
                    "\n5.Выйти из программы.");
            try {
                switch (inInt.nextInt()) {
//                    case 0:
                    case 1:
                        addTask(taskSer); break;
                    case 2:
                        removeTask(taskSer); break;
                    case 3:
                        getAllByDate(taskSer); break;
                    case 4:
                        getAllTasks(taskSer); break;
                    case 5:
                        break;
                    default:
                        System.out.println("\nНе корректно введен номер пункта!");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (exit());
    }

}