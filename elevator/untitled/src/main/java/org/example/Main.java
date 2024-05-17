package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class Main {


    //   jdbc:mysql:aws://localhost:3306/elevator/tables/logs_


   //--------------создание текстового файла-------------\\


    public static void writeToDatabase(String timestamp, String content) {
        String url = "jdbc:mysql://localhost:3306/elevator";
        String username = "root";
        String password = "123456789";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO logs_ (timestamp, message) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, timestamp);
            statement.setString(2, content);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


        int currentFloor = 1; // Начальный этаж


        //------цикл сканера ввода этажей-----\\
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

    //------механизм работы-----\\
    public static void move(int currentFloor, int targetFloor) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        if (currentFloor < targetFloor) {
            // Лифт поднимается
            for (int floor = currentFloor; floor <= targetFloor; floor++) {
                String message = "Лифт поднимается на этаж: " + floor;
                System.out.println(message);
                writeToDatabase(timestamp, message);
                try {
                    Thread.sleep(1000); // Задержка в 1 секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (currentFloor > targetFloor) {
            // Лифт опускается
            for (int floor = currentFloor; floor >= targetFloor; floor--) {
                String message = "Лифт опускается на этаж: " + floor;
                System.out.println(message);
                writeToDatabase(timestamp, message);
                try {
                    Thread.sleep(1000); // Задержка в 1 секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            String message = "Вы находитесь на целевом этаже: " + currentFloor;
            System.out.println(message);
            writeToDatabase(timestamp, message);
        }
    }

}