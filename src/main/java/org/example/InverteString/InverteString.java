package org.example.InverteString;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InverteString {

    public static void main(String[] args) {
        String string = JOptionPane.showInputDialog(null, "Digite uma palavra:");

        //Lista de string para conversão
        ArrayList<String> arrayListReversed = new ArrayList<>();

        //Iterar cada indice, do maior para o menor e adicionar na lista de string invertida
        for (int i = string.length() - 1; i >= 0; i--) {
            arrayListReversed.add(String.valueOf(string.charAt(i)));
        }

        System.out.println(arrayListReversed);
    }
}
