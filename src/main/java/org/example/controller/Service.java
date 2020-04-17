package org.example.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Service {
    private Scanner scanner;
    private Controller controller;

    public Service() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public void run() {
        System.out.print("Для введения отношения, нажмите - 1," + "\n" +
                "Для загрузки отношений из файла, нажмите - 2," + "\n" +
                "Для нахождения величины по пропорции, нажмите - 3," + "\n" +
                "Для загрузки файла с выражениями для поиска, нажмите - 4 \n" +
                "Для завершения работы приложения, нажмите - 5" + "\n");
        int i = 0;
        try {
            i = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.print("Ожидание ввода от 1 до 4\n");
            run();
        }
        switch (i) {
            case (1):
                getTextWithData();
                break;
            case (2):
                uploadDataFromFile();
                break;
            case (3):
                getAnswer();
                break;
            case (4):
                uploadQustionsFromFile();
                break;
            case (5):
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.print("Ожидание ввода от 1 до 5\n\n");
                run();
        }

    }

    private void getTextWithData() {
        System.out.print("Введите отношение по формату a V = b W с учетом пробелов, коэффициенты a и b не должны равняться нулю \n");
        String str = scanner.nextLine();
        try {
            controller.addSingleString(str);
            System.out.print("Отношение добавлено успешно \n\n");
        } catch (MyException e) {
            System.out.print(e.getMessage());
        }
        run();
    }

    private void uploadDataFromFile() {
        System.out.print("Введите имя файла из которого загрузить отношения, Например 'task.txt' \n");
        String str = scanner.nextLine();
        try {
            FileReader fileReader = new FileReader(str);
            Scanner scan2 = new Scanner(fileReader);
            String richString = "";
            while (scan2.hasNextLine()) {
                richString += scan2.nextLine() + "\n";
            }
            controller.addManyStrings(richString);
            System.out.print("Файл загружен успешно \n\n");
        } catch (FileNotFoundException e) {
            System.out.print("Файл с такким именем не найден\n");
        } catch (MyException e) {
            System.out.print(e.getMessage() + "Остальные отношения в этом файле тоже добавлены не будут из-за ошибки \n\n");
        }
        run();
    }

    private void uploadQustionsFromFile() {
        System.out.print("Введите имя файла из которого загрузить вопросы, Например 'task2.txt' \n");
        String str = scanner.nextLine();
        try {
            FileReader fileReader = new FileReader(str);
            Scanner scan2 = new Scanner(fileReader);
            while (scan2.hasNextLine()) {
                try {
                    String string = scan2.nextLine();
                    String array[] = string.split(" ");
                    if (array.length > 4) {
                        double var = controller.getSingleAnswer(string);
                        String formated = new DecimalFormat("#0.00").format(var);
                        System.out.print(array[0]+" "+ array[1]+ " = "+ formated+ " "+ array[4]+"\n\n");
                    }
                } catch (MyException e) {
                    System.out.print(e.getMessage() + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("Файл с такким именем не найден\n");
        }
        run();
    }

    private void getAnswer() {
        System.out.print("Введите отношение по формату a V = ? W с учетом пробелов, коэффициент a не должен равняться нулю \n");
        String str = scanner.nextLine();
        try {
            String array[] = str.split(" ");
            if (array.length > 4) {
                if (array[3].equals("?")) {
                    double var = controller.getSingleAnswer(str);
                    String formated = new DecimalFormat("#0.00").format(var);
                    System.out.print("Ответ: " + array[0] + " " + array[1] + " = " + formated + " " + array[4] + "\n");
                } else {
                    throw new MyException("Строка введена не по шаблону,отсутствует знак '?' \n");
                }
            } else {
                throw new MyException("Неправильный формат ввода \n ");
            }
        } catch (MyException e) {
            System.out.print(e.getMessage());
        }
        run();
    }
}
