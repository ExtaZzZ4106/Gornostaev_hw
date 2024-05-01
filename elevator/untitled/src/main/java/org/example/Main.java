package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
   //--------------создание текстового файла-------------\\
    public static void writeToFile(String folderPath, String fileName, String content) {
        try {
            // Создаем новый файл в указанной папке
            File folder = new File(folderPath);
            File file = new File(folder, fileName);

            // Если файл не существует, создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            // Записываем дату
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);

            // Записываем текст в файл
            FileWriter writer = new FileWriter(file, true); // true для дозаписи в файл
            writer.write(timestamp + " - " + content + "\n"); // Добавляем перенос строки после каждой записи
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        int currentFloor = 1; // Начальный этаж


        //------цикл сканера ввода этажей-----\\\
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите номер этажа, на который вы хотите подняться (или 'q' для выхода): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Выход из программы.");
                break;
            }

            try {
                int targetFloor = Integer.parseInt(input);
                if (targetFloor > 10 || targetFloor < 0) {
                    System.out.println("неверно выбран этаж");
                } else {
                    move(currentFloor, targetFloor);
                    currentFloor = targetFloor; // Обновляем текущий этаж
                }

            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Введите число или 'q' для выхода.");
            }
        }
    }

    //------механизм работы-----\\\
    public static void move(int currentFloor, int targetFloor) {
        String folderPath = System.getProperty("user.dir");
        String fileName = "lift_movements.txt";

        if (currentFloor < targetFloor) {
            // Лифт поднимается
            for (int floor = currentFloor; floor <= targetFloor; floor++) {
                System.out.println("Лифт поднимается на этаж: " + floor);
                writeToFile(folderPath, fileName, "Лифт поднимается на этаж: " + floor);
                try {
                    Thread.sleep(1000); // Задержка в 1 секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (currentFloor > targetFloor) {
            // Лифт опускается
            for (int floor = currentFloor; floor >= targetFloor; floor--) {
                System.out.println("Лифт опускается на этаж: " + floor);
                writeToFile(folderPath, fileName, "Лифт опускается на этаж: " + floor);
                try {
                    Thread.sleep(1000); // Задержка в 1 секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Вы находитесь на целевом этаже: "+currentFloor);
            writeToFile(folderPath, fileName, "Вы находитесь на целевом этаже: "+currentFloor);
        }
    }

}
