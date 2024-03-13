package com.example.procesossoftware;

public class Estimaciones {
    // Supongamos que el costo promedio de un cigarro es de 0.25 euros
    public Estimaciones(){} // constructor por defecto, se puede modificar
    private static final double COSTO_CIGARRO = 0.25;
    public static double ahorroSemanal(Registro registro){
        // Calcula la cantidad total de cigarros fumados en la última semana
        Integer[] semana = Ultimos(registro);
        
        return ahorro(registro.getDailyCigaretteAverage(), semana);
    }

    private static double ahorro(Integer dailyCigaretteAverage, Integer[] semana){
        int totalCigarros = 0;
        for (int i = 1; i <= (semana.length - 1); i++){
            totalCigarros += semana[i];
        }
        // Calcula el ahorro semanal en euros
        int mediaCigarros = dailyCigaretteAverage * (semana.length - 1);  //.length por si el usuario lleva menos de una semana en la app
        return (mediaCigarros - totalCigarros) * COSTO_CIGARRO;
    }

    public static double ahorroTotal(Registro registro){
        double ahorroTotal = 0;
        for (Integer[] semana : registro.reg){
            ahorroTotal += ahorro(registro.getDailyCigaretteAverage(), semana);
        }
        return ahorroTotal;
    }

    private static Integer[] Ultimos(Registro r){
        Integer[] ultimosDias;
        if(r.reg.size() >= 2){  //si hay más de una semana ponemos lo sultimos 7 dias
            ultimosDias = new Integer[8];
            Integer[] semana1 = r.reg.get(r.reg.size()-2);//semana anterior
            Integer[] semana2 = r.reg.get(r.reg.size()-1);
            Integer[] semana = semana2;
            int cont = r.lastDay; //nos vamos al primero de los últimos 7 dias
            for(int i = 7; i > 0 ; i--){
                ultimosDias[i] = semana[cont];
                cont--;
                if(cont == 0){
                    cont = 7;
                    semana = semana1;
                }
            }
        } else {  //si solo hay una semana guardada mostramos los dias que tenemos
            ultimosDias = r.reg.get(0);
        }
        return ultimosDias;
    }
    public static String getBalance(Double balance){

        String balanceString;
        if (balance >= 0) {
            String strBalance = String.format("%.02f", balance);
            balanceString = ("Has ahorrado " + strBalance + "€");
            if (balance > 5 && balance < 10){
                balanceString = ("Con el dinero que te has ahorrado podrías comprarte un menú kebab.");
            } else if (balance >= 10 && balance < 20) {
                balanceString = ("Con el dinero que te has ahorrado podrías ir al cine e invitar a alguien.");
            } else if (balance >= 20 && balance < 70) {
                balanceString = ("Con el dinero que te has ahorrado podrías tener una cena especial.");
            } else if (balance >= 50 && balance < 100) {
                balanceString = ("Con el dinero que te has ahorrado podrías asistir a un festival.");
            } else if (balance >= 100 && balance < 200) {
                balanceString = ("Con el dinero que te has ahorrado podrías irte de escapada de fin de semana.");
            } else if (balance >= 200 && balance < 500) {
                balanceString = ("Con el dinero que te has ahorrado podrías comprarte una nueva consola.");
            } else if (balance >= 500) {
                balanceString = ("¡Guau! Con el dinero que te has ahorrado podrías organizarte un viaje fuera del país.");
            }
        } else {
            String strBalance = String.format("%.02f", -balance);
            balanceString = ("Has gastado " +  strBalance + "€ más de lo normal.\n\nNo te desmotives, conseguirás que ese número sea positivo.");
        }
        return balanceString;
    }
}
