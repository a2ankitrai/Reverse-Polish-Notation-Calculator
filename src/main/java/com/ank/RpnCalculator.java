package com.ank;

import com.ank.service.InputProcessor;

import java.util.Scanner;

public class RpnCalculator {

    public static void main(String[] args) {

        InputProcessor ip = new InputProcessor();

        Scanner in = new Scanner(System.in);
        System.out.println("Calculator Started. Enter input:");

        while (true) {
            String input = in.nextLine();
            String result = ip.process(input);
            System.out.println("Stack: " + result);

        }

    }
}
