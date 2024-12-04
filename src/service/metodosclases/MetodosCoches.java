package service.metodosclases;

import model.Coche;

import java.util.ArrayList;
import java.util.List;

public class MetodosCoches {

    public List<Coche> getCochesList() {
        List<Coche> listaCoches = new ArrayList<>();

        Coche coche1 = new Coche("1234ABC", "Toyota");
        Coche coche2 = new Coche("5678XYZ", "Ford");

        listaCoches.add(coche1);
        listaCoches.add(coche2);

        return listaCoches;


    }
}


