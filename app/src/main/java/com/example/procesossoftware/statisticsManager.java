package com.example.procesossoftware;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class statisticsManager extends Fragment {
    Registro registro;
    View view_general; //for savings
    View view_grafic;
    public statisticsManager(View v1, View v2, Registro r){
        this.registro = r;
        this.view_general= v1;
        this.view_grafic = v2;
    }

    public void setAhorro(){
        TextView text = view_general.findViewById(R.id.ahorro_texto);

        Double balance = Estimaciones.ahorroSemanal(registro);

        if (balance >= 0) {
            String strBalance = String.format("%.02f", balance);
            text.setText("Esta semana has ahorrado " + strBalance + "€");
        } else {
            String strBalance = String.format("%.02f", -balance);
            text.setText("Esta semana has gastado " +  strBalance + "€");
        }
    }
    public void setRegistro(Registro r){
        this.registro = r;
    }
    public void setGrafica(){
        // Cuando accede desde el onResume, view no es la de graifco_cigarros.xml y aún así encuentra barChart
        BarChart barChart = view_grafic.findViewById(R.id.barChart);

        // Configurar la descripción (título) del gráfico
        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);

        //Quita el eje y de la derecha
        barChart.getAxisRight().setDrawLabels(false);

        List<String> xValues = Arrays.asList("7", "6", "5", "4", "3", "2", "1");

        //CONFIGURA EL EJE X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition((XAxis.XAxisPosition.BOTTOM));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));

        xAxis.setLabelCount(7);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        //CONFIGURA EJE Y
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(20f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(8);
        yAxis.setDrawGridLines(false);
        List<BarEntry> entries1 = new ArrayList<>();
        if(registro.reg.size()<2){//si solo hay una semana guardada mostramos los dias que tenemos
            Integer[] semana = registro.reg.get(registro.reg.size()-1);
            int j = 0;
            for(int i = 1; i<=registro.lastDay;i++){
                entries1.add(new BarEntry(j, semana[i]+4));
                j++;
            }
        }
        else{ //si hay más de una semana ponemos lo sultimos 7 dias
            Integer[] ultimos7 = new Integer[8];
            Integer[] semana1 = registro.reg.get(registro.reg.size()-2);//semana anterior
            Integer[] semana2 = registro.reg.get(registro.reg.size()-1);
            Integer[] semana = semana2;
            int cont = registro.lastDay; //nos vamos al primero de los últimos 7 dias
            for(int i = 7;i>0;i--){
                ultimos7[i]=semana[cont];
                cont--;
                if(cont==0){
                    cont=7;
                    semana = semana1;
                }
            }
            for(int i = 1;i<=7;i++){
                entries1.add(new BarEntry(i,ultimos7[i]));
            }
        }



        BarDataSet dataSet = new BarDataSet(entries1, "Cigarros fumados");
        dataSet.setColor(Color.BLUE);
        BarData lineData = new BarData(dataSet);

        barChart.setData(lineData);
        // Actualizar el gráfico
        barChart.invalidate();
    }
}
