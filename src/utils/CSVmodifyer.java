package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVmodifyer {
	public static void eliminarComasCSV(String rutaArchivo, String rutaSalida) {
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
             PrintWriter pw = new PrintWriter(new FileWriter(rutaSalida))) {
            while ((linea = br.readLine()) != null) {
                String[] fila = linea.split(",");
                // It replaces the commas inside the String of referee's column.
                fila[10] = fila[10].replace(",", "");
                pw.println(String.join(",", fila));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		eliminarComasCSV("data/results.csv","data/newResults.csv");
	}
}
