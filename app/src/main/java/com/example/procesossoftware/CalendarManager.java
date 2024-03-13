package com.example.procesossoftware;

import java.util.Calendar;

public class CalendarManager {
    public CalendarManager(){}
    public Boolean updateRegistro(Registro r){
        Boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        int numeroSemana = calendar.get(Calendar.WEEK_OF_YEAR);
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        diaSemana--;
        if(diaSemana==0){
            diaSemana=7;
        }
        if(diaSemana!=r.lastDay || numeroSemana!=r.lastWeek) flag = true;
        changeCont(diaSemana,numeroSemana,r);
        r.SetDay(diaSemana);
        r.SetWeek(numeroSemana);
        return flag;
    }
    private void changeCont(int diaSemana, int numeroSemana, Registro r) {
        if(numeroSemana == r.lastWeek){
            r.setNumDias(r.numDias+(diaSemana-r.lastDay));
        }
        else{
            int dif = (numeroSemana - r.lastWeek)*7;
            if (r.lastDay < diaSemana ){
                dif += (diaSemana - r.lastDay);
            }
            else{
                dif -= (r.lastDay-diaSemana);
            }
            r.setNumDias(dif);

        }
    }

}
