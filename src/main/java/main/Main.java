package main;

import database.JdbcVentasDatabaseService;
import model.DefaultEstacionDeServicio;
import ui.MainView;

public class Main {

    //Conexion para DB cliente / servidor
    public static final String CONNSTR = "jdbc:derby://localhost:1527/ventas;create=true";
    //Conexion para DB en memoria
    //public static final String CONNSTR = "jdbc:derby:memory:ventas;create=true";
    public static final String USERNAME = "app";
    public static final String PWD = "app";

    public static void main(String[] args) {
        inicializarDatabase();
        launchApplication();
    }

    private static void launchApplication() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JdbcVentasDatabaseService registroVentas =
                            new JdbcVentasDatabaseService(CONNSTR, USERNAME, PWD);
                    DefaultEstacionDeServicio estacion =
                            new DefaultEstacionDeServicio(registroVentas);
                    new MainView(estacion).launch();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static void inicializarDatabase() {
        var jdbc = new SetUpDatabase(CONNSTR,
                USERNAME,
                PWD);
        jdbc.inicializar();
    }
}