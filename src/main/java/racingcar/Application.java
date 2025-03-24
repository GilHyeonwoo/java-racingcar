package racingcar;
import java.lang.*;
import java.util.*;
import camp.nextstep.edu.missionutils.*;

import static camp.nextstep.edu.missionutils.Console.readLine;

class Car{
    private String name;
    private int postion;

    public Car(String name){
        if (name.length() > 5){
            throw new IllegalArgumentException("자동차 이름은 5글자 이하만 가능합니다.");
        }
        this.name = name;
        this.postion = 0;
    }

    public void move(){
        Random rand = new Random();
        int randomValue = rand.nextInt(10);
        if (randomValue >= 4){
            postion++;
        }
    }


    public int getPostion() {
        return postion;
    }
    public String getName() {
        return name;
    }
}

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("경주할 자동차 이름: ");
        String[] carNames = readLine().split(",");
        //String[] carNames = scanner.nextLine().split(",");

        List<Car> cars = new ArrayList<>();
        for (String name : carNames) {
            cars.add(new Car(name.trim()));
        }

        System.out.println("시도할 횟수: ");
        int attempts = Integer.parseInt(readLine());
        //int attempts = scanner.nextInt();

        for (int i = 0; i < attempts; i++) {
            for (Car car : cars) {
                car.move();
            }
        }
        System.out.println("실행 결과:");
        for (int i = 0; i< attempts; i++) {
            for (Car car : cars){
                car.move();
            }
            printRaceStatus(cars);
        }
        printWinners(cars);
    }

    private static void printRaceStatus(List<Car> cars){
        for (Car car : cars) {
            System.out.println(car.getName() + ": " +"-".repeat(car.getPostion()));
        }
        System.out.println();
    }

    private static void printWinners(List<Car> cars){
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPostion() > maxPosition) {
                maxPosition = car.getPostion();
            }
        }
        for (Car car : cars) {
            if  (car.getPostion() == maxPosition) {
                winners.add(car.getName());
            }
        }
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
