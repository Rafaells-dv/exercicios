package org.example.fibo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FiboCheck {
    public static void main(String[] args) {
        int num;

        //Requisita input de número ao usuario
        num = Integer.parseInt(JOptionPane.showInputDialog("Informe um número: "));

        //Verifica se NUM está na sequência e retorna a resposta adequada
        if (isFibonacci(num)){
            JOptionPane.showMessageDialog(null, num + " pertence a sequência fibonacci");
        } else {
            JOptionPane.showMessageDialog(null, num + " não pertence a sequência fibonacci");
        }
    }

    //Calcula a sequência fibonacci até NUM ser menor que o ultimo número gerado
    public static boolean isFibonacci(int num) {

        //Cria a lista inicial da sequência fibonacci
        List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(0);
        fibonacci.add(1);

        //Gera um número fibonacci até ele ser maior que NUM
        while (num > fibonacci.indexOf(fibonacci.getLast())){
            int nextFibo = fibonacci.getLast() + fibonacci.get(fibonacci.size() - 2);
            fibonacci.add(nextFibo);
        }

        //Verifica se NUM está dentro do array fibonacci
        return fibonacci.contains(num);
    }
}

