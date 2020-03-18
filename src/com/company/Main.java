package com.company;
import java.util.*;

public class Main {


    float t0 = 0; //termo independente da função
    float t1 = 0;
    float t2 = 0;
    float t3 = 0;
    float t4 = 0;
    float t5 = 0;
    double epsilon = 0.001;

    ArrayList<Integer[]> intervalos;


    public void preencherFuncao(float a0, float a1, float a2, float a3, float a4, float a5){
        t0 = a0;
        t1 = a1;
        t2 = a2;
        t3 = a3;
        t4 = a4;
        t5 = a5;
    }

    public double preencherEpsilon(int n){
        epsilon = Math.pow(10,(double) n);
        return epsilon;
    }

    public double resolverFuncao(double x){
        return (Math.pow(x,5) * t5 + Math.pow(x,4) * t4 + Math.pow(x,3) * t3 + Math.pow(x,2) * t2 + x * t1 + t0);
    }

    /**
     * Acha os intervalos dos zeros da função
     *
     * @return intervalos com os inteiros
     */
    public ArrayList<Integer[]> acharIntervalos(){


        ArrayList<Integer[]> intervalos = new ArrayList<>();
        Integer[] intervalo;

        for(int i = -99; i <100;i++){
            intervalo = new Integer[2];
            //System.out.println(resolverFuncao(i));
            //System.out.println(resolverFuncao(i-1));
            if(resolverFuncao(i)*resolverFuncao(i-1)<0){
                intervalo[0] = i-1;
                intervalo[1] = i;
                intervalos.add(intervalo);
            }
        }
        return intervalos;
    }

    public Double refinarValores(Integer[] intervalo){

        double a = intervalo[0];
        double b = intervalo[1];

        do{
            double aux = ((b+a)/2);

            if(resolverFuncao(aux)*resolverFuncao(a)<0){
                b = aux;
            }else {
                a = aux;
            }
        }while(Math.abs(b-a)>epsilon);

        if(Math.abs(resolverFuncao(b))<Math.abs(resolverFuncao(a))){
            return b;
        }else {
            return a;
        }

    }

    public ArrayList<Double> refinarValores1(Integer[] intervalo){

        ArrayList<Double> retorno = new ArrayList<>();
        double a = intervalo[0];
        double b = intervalo[1];

        do{
            double aux = ((b+a)/2);
            retorno.add(aux);
            if(resolverFuncao(aux)*resolverFuncao(a)<0){
                b = aux;
            }else {
                a = aux;
            }
        }while(Math.abs(b-a)>epsilon);

        if(Math.abs(resolverFuncao(b))<Math.abs(resolverFuncao(a))){
            retorno.add(b);
        }else {
            retorno.add(a);
        }
        return retorno;

    }

    public String exibirIntervalos(){
        StringBuilder retorno = new StringBuilder("Intervalos: \n");
        intervalos = acharIntervalos();

        for(Integer[] i : intervalos){
            retorno.append(String.format("[%d,%d] \n", i[0], i[1]));
        }
        return retorno.toString();
    }
    //|b-a| < epsilon

}