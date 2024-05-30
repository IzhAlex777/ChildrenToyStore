package ChildrenToyStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ToyRaffle {
    private static final Random RANDOM = ThreadLocalRandom.current();
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    File file = new File("./ChildrenToyStore","Список_полученных_игрушек.txt");
    Map<String,Map<Toys,Integer>> toysMap = new HashMap<>();

    public Map<String,Map<Toys,Integer>> addingToys(String []  toy, int lossRateToy){
         for (String t : toy){
            Map<Toys,Integer> toysIntegerMap = new HashMap<>();
            Toys toys = new Toys(t);
            toys.setLossRateToy(RANDOM.nextInt(0,lossRateToy));
            int quantity = toys.addTenPercent();
            toysIntegerMap.put(toys,quantity);
            toysMap.put(t,toysIntegerMap);
        }
        return toysMap;
    }

    public String selectionOfToys(String toySelect ){
        if(!toysMap.isEmpty()){
            if(toysMap.containsKey(toySelect)) {
                Map<Toys,Integer> toy =  toysMap.get(toySelect);
                Set<Toys> toys = toy.keySet();
                for (Toys t : toys) {
                    if(t.getNameToy().equals(toySelect)) {
                        t.setQuantity(t.getQuantity() - 1);
                        System.out.println(ANSI_GREEN + "Остаток игрушек "  +ANSI_RESET+ ANSI_BLUE+ toysMap+ANSI_RESET);
                        if(t.getQuantity() == 0) {
                            System.out.println(ANSI_YELLOW + "Игрушка " + t.getNameToy() + " закончилась." + ANSI_RESET);
                            toysMap.remove(t.getNameToy());
                            System.out.println(ANSI_GREEN + "Остаток игрушек после удаления изсписка"  +ANSI_RESET+ ANSI_BLUE+ toysMap+ANSI_RESET);
                        }
                    }
                }
                return toySelect;
            }else {
                System.out.println(ANSI_GREEN + "Остаток игрушек "  +ANSI_RESET+ ANSI_BLUE+ toysMap+ANSI_RESET);
                return "0";
            }
        }else {
            return "1";
        }
    }

    public File receivingToys(String toySelect){
        if (!toySelect.isEmpty()){
            try {
                return getFile(toySelect);
            }catch (IOException e) {
                System.out.println(ANSI_RED+"Ошибка при создании файла выдачи игрушек."+ANSI_RESET);
            }
        }
        return file;
    }

    private File getFile(String toySelect) throws IOException {
        if (file.exists()){
            FileWriter writer = new FileWriter(file, true);
            writer.write( "Вы выигрвли ишрушку " + ANSI_BLUE+toySelect+ANSI_RESET + "." + "\n");
            writer.close();
        }
        else{
            FileWriter writer = new FileWriter(file);
            writer.write( "Вы выигрвли ишрушку " + ANSI_BLUE+toySelect+ANSI_RESET + "." + "\n");
            writer.close();
        }
        return file;
    }
}
