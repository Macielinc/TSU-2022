package uia.com.compras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cotizar {
    private int clasificacion = 0;

    protected HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> cotizaciones = new HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>>();

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>> cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public void hazSolicituCotizacion(PeticionCotizacion miPeticionC) {
        validaExistencia(miPeticionC);
    }

    private void validaExistencia(PeticionCotizacion miPeticionC) {
        for (int i = 0; i < miPeticionC.getItems().size(); i++) {
            validaUso((PeticionCotizacion) miPeticionC.getItems().get(i), i);
        }
    }

    private void validaUso(PeticionCotizacion miPeticionC, int i) {
        switch (i % 3) {
            case 0:
                miPeticionC.setClasificacion(0);
                break;
            case 1:
                miPeticionC.setClasificacion(1);
                break;
            case 2:
                miPeticionC.setClasificacion(2);
                break;
        }
    }

    public SolicitudCotizacion buscaCotizacion(PeticionCotizacion miPeticionC) {
        //--Por downcasting se crea SolicituOrdenCompra para asignar las peticiones con proveedor
        SolicitudCotizacion miSolicitudC = new SolicitudCotizacion();

        for (int i = 0; i < miPeticionC.getItems().size(); i++) {
            SolicitudCotizacion item;
            item = new SolicitudCotizacion((PeticionCotizacion) miPeticionC.getItems().get(i));
            if (buscaCotizacion((SolicitudCotizacion) item, i) >= 0) {
                if (miSolicitudC.getItems() == null)
                    miSolicitudC.setItems(new ArrayList<InfoComprasUIA>());
                miSolicitudC.getItems().add(item);
            }
        }
        if (miSolicitudC != null)
            return miSolicitudC;
        else
            return null;

    }

    private int buscaCotizacion(SolicitudCotizacion solicitudC, int i) {
        switch (i % 3) {
            case 0:
                solicitudC.setCotizacion(0);
                return i;
            case 1:
                solicitudC.setCotizacion(1);
                return i;
            case 2:
                solicitudC.setCotizacion(2);
                return i;
        }
        return -1;
    }

    public void agrupaCotizaciones(PeticionCotizacion peticionC) {
        SolicitudCotizacion newItem = null;
        int key = 0;
        int keyLista = -1;

        if (cotizaciones == null)
            cotizaciones = new HashMap<Integer, HashMap<Integer, ArrayList<InfoComprasUIA>>>();

        for (int i = 0; i < peticionC.getItems().size(); i++) {
            newItem = (SolicitudCotizacion) peticionC.getItems().get(i);
            key = newItem.getCotizacion();
            keyLista = i % 3;

            if (cotizaciones.containsKey(key)) {
                if (cotizaciones.get(key).containsKey(keyLista)) {
                    cotizaciones.get(key).get(keyLista).add(newItem);
                } else {
                    ArrayList<InfoComprasUIA> newLista = new ArrayList<InfoComprasUIA>();
                    newLista.add(newItem);
                    HashMap<Integer, ArrayList<InfoComprasUIA>> nodo = new HashMap<Integer, ArrayList<InfoComprasUIA>>();
                    nodo.put(i, newLista);
                    cotizaciones.put(key, nodo);
                }
            } else {
                ArrayList<InfoComprasUIA> newLista = new ArrayList<InfoComprasUIA>();
                newLista.add(newItem);
                HashMap<Integer, ArrayList<InfoComprasUIA>> nodo = new HashMap<Integer, ArrayList<InfoComprasUIA>>();
                nodo.put(keyLista, newLista);
                cotizaciones.put(key, nodo);
            }
        }
    }
}
