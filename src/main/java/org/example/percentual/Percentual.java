package org.example.percentual;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Percentual {
    static Double valorTotal = 0.0;

    public static void main(String[] args) throws IOException {
        // Carregar o Json
        JSONArray jsonArray = readJson("D:\\Projetos\\gabarito\\Target\\src\\main\\java\\org\\example\\percentual\\data.json");
        List<JSONObject> estados = new ArrayList<>();

        //Calula o valor total mensal
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject response = jsonArray.getJSONObject(i);
            valorTotal = valorTotal + response.getDouble("Valor");
            estados.add(response);
        }

        //Para cada estado em estados calcula seu valor percentual
        for (JSONObject estado : estados) {
            String percentual = calcPercentual(estado.getDouble("Valor"));
            System.out.println(estado.getString("Estado")+": %" + percentual);
        }

    }


    //Leitura de arquivo JSON
    public static JSONArray readJson(String path) throws IOException {
        //Ler o json omo string
        String data = new String(Files.readAllBytes(Paths.get(path)));

        //Converter para jsonObject
        JSONObject jsonObject = new JSONObject(data);

        return jsonObject.getJSONArray("DATA");
    }

    //Calcula o percentual dos valores em relação ao valor total
    public static String calcPercentual(Double valor) {
        return String.format("%.2f", valor * 100 / valorTotal);
    }
}
