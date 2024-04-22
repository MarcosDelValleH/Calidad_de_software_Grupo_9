package com.example.procesossoftware;

public class PrevisionesManager {
    Registro r;
    public PrevisionesManager(Registro r){
        this.r = r;
    }
    public int getPerfil(){ // Devuelve el perfil del usuario fumón
        if(r.lastWeek-r.semanaInstalado<2) return 2; // Perfil acaba de empezar a usar la aplicación
        int[] last7 = r.getLast7();
        int cont = 0;
        for(int i = 0; i<8;i++){
            if (last7[i]==0) cont++;
        }
        if (cont>=4) return 1; // Perfil solo fuma en dias puntuales
        else return 0; // Perfil fuma todos los días
    }
    public int[] getPrevision(int perfil){
        int[] prevision = new int[3];
        int[] last7 = r.getLast7();
        prevision[0] = last7[0]-1; //Objetivo dia
        if (prevision[0]<0) prevision[0] = 0;
        int totalLast7 = 0;
        for(int i = 0;i<7;i++) totalLast7 += last7[i];
        if(perfil==1){
            prevision[1] = (int)(totalLast7*0.7); // Objetivo semana
            prevision[2] = (int)(totalLast7*0.6*4);
        }
        else{
            prevision[1] = (int)(totalLast7*0.8); // Objetivo mes
            prevision[2] = (int)(totalLast7*0.7*4);
        }
        return prevision;
    }
    public String[] toString(int[] prevision){
        String[] previsionS = new String[3];
        previsionS[0] = "El objetivo de hoy es: "+prevision[0]+ " cigarros.";
        previsionS[1] = "El objetivo para esta semana es: "+prevision[0]+ " cigarros.";
        previsionS[2] = "El objetivo de este mes es: "+prevision[0]+ " cigarros.";
        return previsionS;
    }
}
