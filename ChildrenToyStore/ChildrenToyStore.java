package ChildrenToyStore;

import java.io.*;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static ChildrenToyStore.ToyRaffle.*;

public class ChildrenToyStore {
    private static final Random RANDOM = ThreadLocalRandom.current();

    public static void main(String[] args) {
        ToyRaffle toyRaffle = new ToyRaffle();
        String [] nameToysCreate = {"Beer","Airplane","Car","Balloon"};

        System.out.println(ANSI_GREEN + "Введите частоту выпадения приза " +ANSI_RESET+ ANSI_BLUE+  "от \"0\" до  \"100\"" + ANSI_RESET
                +ANSI_GREEN +", другие значения не принимаются ."+ANSI_RESET);
        Scanner scannerLossRateToy = new Scanner(System.in);

        if(scannerLossRateToy.hasNextInt()) {
            int lossRate = scannerLossRateToy.nextInt();
            while (!(lossRate > 0 && lossRate < 100)){
                System.out.println(ANSI_RED+ "НЕ верное значение, "+ANSI_RESET + ANSI_GREEN+"Введите частоту выпадения приза " + "от \"0\" до  \"100\""+ANSI_RESET);
                lossRate = scannerLossRateToy.nextInt();
            }
            if(args.length > 0){
                createToysMap(toyRaffle, args ,lossRate);
            }else {
                createToysMap(toyRaffle, nameToysCreate,lossRate);
            }

        }else {
            System.err.println("Введено не верное значение частоты выпадения приза, перезапустите программу.");
            System.exit(1);
        }
        rafflePrizes(toyRaffle,nameToysCreate);
    }

    static void createToysMap(ToyRaffle toyRaffle, String [] nameToysCreate,int lossRate) {
        Map<String, Map<Toys, Integer>> map = toyRaffle.addingToys(nameToysCreate, lossRate);
        System.out.println(ANSI_BLUE+ "Игрушки учасвующие в розыгрыше "+ "\n"+ANSI_RESET +ANSI_YELLOW+map+ANSI_RESET+ ".");
    }

    static void rafflePrizes(ToyRaffle toyRaffle, String [] nameToysCreate){
        System.out.println(ANSI_GREEN + "Введите число в консоле " +ANSI_RESET+ ANSI_BLUE+  "\"0\" " + ANSI_RESET
                +ANSI_GREEN +"для запуска розыгыша или любое другое число для досрочного завершения розыгрыша."+ANSI_RESET);
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            while (scanner.nextInt() == 0 ){
                int toySelectRandom = RANDOM.nextInt(0 , 4);
                String selectionOfToy = toyRaffle.selectionOfToys(nameToysCreate[toySelectRandom]);
                if(!(selectionOfToy.isEmpty())){
                    if(!selectionOfToy.equals("0") && !selectionOfToy.equals("1")){
                        File result = toyRaffle.receivingToys(selectionOfToy);
                        try {
                            FileReader read = new FileReader(result);
                            BufferedReader bufferedReader = new BufferedReader(read);
                            String line;
                            while ((line = bufferedReader.readLine()) != null){
                                System.out.println(line);
                            }
                            read.close();
                        }catch (IOException e) {
                            System.out.println("Ошибка при чтении файла"+ e.getMessage());
                        }
                    }else if(selectionOfToy.equals("0")){
                        System.out.println("\u001B[33m" + "Игрушка "+ "\"" + nameToysCreate[toySelectRandom] + "\"" + " закончилась, попробуй ещё." + "\u001B[0m");
                    } else {
                        System.out.println(ANSI_RED+"Все игрушки закончились."+ANSI_RESET);
                        System.out.println(ANSI_GREEN+"Розыгыш закончен."+ANSI_RESET);
                        scanner.close();
                        System.exit(0);
                    }
                }
            }
            System.out.println(ANSI_RED+"ВЫ досрочно завершили розыгрыш."+ANSI_RESET);
        }else {
            System.err.println("Введено не верное значение, перезапустите программу.");
            System.exit(1);
        }
    }
}
