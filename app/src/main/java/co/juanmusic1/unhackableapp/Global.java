package co.juanmusic1.unhackableapp;

public class Global {
    private static Global data = null;
    public static final String DOMAIN_URL = "https://yourtestdomain.com"; // Cambia por tu dominio de prueba
    public static final int SPLASH_DISPLAY_LENGTH = 3000; // Duración en milisegundos (3 segundos)

    private String pin = "";

    private Global() {
    }

    public static Global getInstance() {
        if (data == null) {
            data = new Global();
        }
        return data;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }


}


