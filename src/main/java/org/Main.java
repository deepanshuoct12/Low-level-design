package org;

import org.Driver;

public class Main {
    private static Driver driver = new Driver();

    public static void main(String[] args) {
        System.out.println("Learn lld nigga!");
        practise();
    }

    public static void practise() {
        //1. tictactoe
       // driver.ticTacToeStart();

        //2. snake and ladder
       // driver.snakeAndLadderStart();

        //3. hashmap
      //  driver.hashMap();

        //4. elevator system
        //driver.elevatorSystemDemo();

        //5. ecommerce site
        driver.eCommerce();
    }
}