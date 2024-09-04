package org.example.faturamento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Faturamento {
    public static void main(String[] args) throws IOException {
        // Carregar o Json
        JSONArray jsonArray = readJson("D:\\Projetos\\gabarito\\Target\\src\\main\\java\\org\\example\\faturamento\\faturamento.json");

        //Dias com faturamento
        List<JSONObject> faturamentos = new ArrayList<>();

        //Maior faturamento
        JSONObject diaMaiorFaturamento = null;
        Double maiorFaturamento = 0.0;

        //Menor faturamento
        JSONObject diaMenorFaturamento = null;
        Double menorFaturamento = 0.0;


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject faturamentoDia = jsonArray.getJSONObject(i);

            //Verifica se o dia teve faturamento
            if (!Objects.equals(faturamentoDia.get("valor").toString(), "0.0")) {
                faturamentos.add(faturamentoDia);

                //Verifica se o faturamento do dia é maior que o maior faturamento, caso sim, substitui a variável
                if (faturamentoDia.getDouble("valor") > maiorFaturamento) {
                    diaMaiorFaturamento = faturamentoDia;
                    maiorFaturamento = faturamentoDia.getDouble("valor");
                }
            }
        }

        //Define o menor faturamento como maior, para ver quais valores serão menores
        menorFaturamento = maiorFaturamento;

        //Verifia se o faturamento é menor que o menor faturamento, caso sim, substitui a variável
        for (JSONObject faturamento : faturamentos) {
            if (faturamento.getDouble("valor") < menorFaturamento){
                diaMenorFaturamento = faturamento;
                menorFaturamento = faturamento.getDouble("valor");
            }
        }

        Double mediaFaturamentos = calculaMedia(faturamentos);

        //Conta quantos dias tiveram o faturamento maior que a média
        int diasFaturamentoMaiorMedia = 0;
        for (JSONObject faturamento: faturamentos) {
            if (faturamento.getDouble("valor") > mediaFaturamentos) {
                diasFaturamentoMaiorMedia++;
            }
        }

        //Imprime as respostas
        System.out.println("Menor valor de faturamento é " + menorFaturamento + " no dia " + diaMenorFaturamento.get("dia"));
        System.out.println("Maior valor de faturamento é " + maiorFaturamento + " no dia " + diaMaiorFaturamento.get("dia"));
        System.out.println("Número de dias que faturaram mais que a média("+mediaFaturamentos+ "):" + diasFaturamentoMaiorMedia);
    }

    //Calcula a média dos faturamentos
    public static Double calculaMedia(List<JSONObject> faturamentos) {
        double soma = 0;
        for (JSONObject faturamento : faturamentos) {
            soma = soma + faturamento.getDouble("valor");
        }
        return soma / faturamentos.size();
    }

    //Leitura de arquivo JSON
    public static JSONArray readJson(String path) throws IOException {
        //Ler o json omo string
        String data = new String(Files.readAllBytes(Paths.get(path)));

        //Converter para jsonObject
        JSONObject jsonObject = new JSONObject(data);

        return jsonObject.getJSONArray("faturamento");
    }
}
