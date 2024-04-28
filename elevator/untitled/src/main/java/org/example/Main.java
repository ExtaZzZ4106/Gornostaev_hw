package org.example;
import java.util.Scanner;

public class Main {
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


        if (currentFloor < targetFloor) {
            // Лифт поднимается
            for (int floor = currentFloor; floor <= targetFloor; floor++) {
                System.out.println("Лифт поднимается на этаж: " + floor);
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
                    try {
                        Thread.sleep(1000); // Задержка в 1 секунду
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Вы находитесь на целевом этаже.");
            }
        }
    }

