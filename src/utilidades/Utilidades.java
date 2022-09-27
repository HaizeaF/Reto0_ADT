package utilidades;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

    public static String introducirCadena() {
        String cadena = "";
        boolean error;
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);

        do {
            error = false;
            try {
                cadena = teclado.readLine();
            } catch (IOException e) {

                System.out.println("Error en la entrada de datos");
                error = true;
                //e.printStackTrace();  Error por default 
            }
        } while (error);
        return cadena;
    }

    public static String introducirCadena(String string) {
        return null;
    }

    public static String leerString(int x, String mensaje) {
        String cadena = null;
        boolean ok;
        do {
            ok = true;
            cadena = introducirCadena(mensaje);
            if (cadena.length() > x) {
                System.out.println("Error al introducir datos. ");
                ok = false;
            }
        } while (!ok);

        return cadena;
    }

    public static String leerCorreo(String mensaje) {
        String cadena = null;
        boolean ok;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        do {
            ok = true;
            cadena = introducirCadena(mensaje);
            Matcher matcher = pattern.matcher(cadena);
            if (matcher.find() == true) {
                System.out.println("El email introducido no es valido.");;
                ok = false;
            }
        } while (!ok);

        return cadena;
    }

    public static double leerDouble(int x, int y, String mensaje) {
        double num = 0;
        boolean ok;
        do {
            try {
                ok = true;
                num = Double.parseDouble(introducirCadena(mensaje));

            } catch (NumberFormatException e) {
                System.out.println("Hay que introducir numeros");
                ok = false;
                num = x;

            }
            if (num < x || num > y) {
                System.out.println("Dato fuera de rango, introduce entre" + x + " y " + y);
                ok = false;
            }
        } while (!ok);
        return num;
    }

    public static double leerDouble(String mensaje) {
        double fNumero = 0;
        boolean ok;
        do {
            try {
                ok = true;
                fNumero = Double.parseDouble(introducirCadena(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Error al introducir el n�mero");
                ok = false;
            }
        } while (!ok);
        return fNumero;
    }

    public static boolean esBoolean(String mensaje) {
        String respu;
        do {
            respu = introducirCadena(mensaje).toLowerCase();
        } while (!respu.equals("0") && !respu.equals("1") && !respu.equals("si") && !respu.equals("no") && !respu.equals("s") && !respu.equals("n") && !respu.equals("true") && !respu.equals("false"));
        if (respu.equals("1") || respu.equals("si") || respu.equals("s") || respu.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static char leerCharArray(char caracteres[], String mensaje) {
        int i;
        boolean error = false;
        String letra;
        char aux = 0;

        do {
            error = false;
            letra = introducirCadena(mensaje);
            if (letra.length() != 1) {
                System.out.println("Error, introduce un caracter: ");
                error = true;
            } else {
                aux = letra.charAt(0);
                for (i = 0; i < caracteres.length; i++) {
                    if (Character.toUpperCase(caracteres[i]) == Character.toUpperCase(aux)) {
                        break;
                    }
                }
                if (i == caracteres.length) {
                    error = true;
                    System.out.println("Error, el caracter introducido no es valido. ");
                }
            }
        } while (error);
        return aux;
    }

    public static char leerChar(String mensaje) {
        boolean error = false;
        String letra;

        do {
            error = false;
            letra = introducirCadena(mensaje);
            if (letra.length() != 1) {
                System.out.println("Error, introduce un car�ccter: ");
                error = true;
            }

        } while (error);
        return letra.charAt(0);
    }
    
    public static char leerChar() {
        char letra = ' ';
        String cadena = introducirCadena();
        boolean error;

        do{
            error = false;
            if(cadena.length() != 1){
                System.out.println("Error, introduce un unico caracter: ");
                error = true;
            }
        }while(error);
        letra = cadena.charAt(0);
        return letra;
    }

    public static float leerFloat() {
        float num = 0;
        boolean error;

        do{
            error = false;
            try {
                num = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, valor no numerico, introduce de nuevo: ");
                error = true;
            }
        }while(error);
        return num;
    }

    public static float leerFloat(float x, float y, String mensaje) {
        float fNumero = 0;
        boolean ok;
        do {
            try {
                ok = true;
                fNumero = Float.parseFloat(introducirCadena(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Hay que introducir numeros. Vuelve aintroducir: ");
                ok = false;
                fNumero = x;
            }
            if (fNumero < x || fNumero > y) {
                System.out.println("Dato fuera de rando. Introduce entre " + x + " y " + y);
                ok = false;
            }
        } while (!ok);
        return fNumero;
    }

    public static int leerInt() {
        int num = 0;
        boolean error;

        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, valor no numerico, introduce de nuevo: ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int leerInt(String mensaje) {
        int iNumero = 0;
        boolean ok;
        do {
            try {
                ok = true;
                iNumero = Integer.parseInt(introducirCadena(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("hay que introducir numeros");
                ok = false;
            }
        } while (!ok);
        return iNumero;
    }

    public static int leerInt(int min, int max) {
        int num = 0;
        boolean error;

        do{
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, valor no numerico, introduce de nuevo: ");
                error = true;
                num = min;
            }

            if(num<min || num>max){
                System.out.println("Numero fuera de rango, introduce numero entre " + min + " y " + max + ": ");
                error = true;
            }
        }while(error);
        return num;
    }

    public static LocalDate leerFechaDMA() {
        LocalDate date = null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString;
        boolean error;

        do {
            dateString = introducirCadena();
            error = false;
            try {
                date = LocalDate.parse(dateString, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Error, introduce una fecha en formato dd/mm/aaaa");
                error = true;
            }
        } while (error);

        return date;
    }

    public static LocalDate leerFechaAMD() {
        LocalDate date = null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateString;
        boolean error;

        do {
            dateString = introducirCadena();
            error = false;
            try {
                date = LocalDate.parse(dateString, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Error, introduce una fecha en formato aaaa/MM/dd");
                error = true;
            }
        } while (error);

        return date;
    }

    public static int calculoFichero(File fich) {
        int cont = 0;
        if (fich.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(fich);
                ois = new ObjectInputStream(fis);

                Object aux = ois.readObject();

                while (aux != null) {
                    cont++;
                    aux = ois.readObject();
                }

            } catch (EOFException e1) {
                System.out.println("Has acabado de leer, tienes " + cont + " objetos");

            } catch (Exception e2) {
                e2.printStackTrace();
            }

            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar los flujos");

            }
        }
        return cont;
    }

    public static int transformarMes(String mes) {
        int mesTrans = 0;
        boolean ok = true;

        do {
            if (mes.equalsIgnoreCase("Enero") || mes.equalsIgnoreCase("01") || mes.equalsIgnoreCase("1")) {
                mesTrans = 1;
            } else if (mes.equalsIgnoreCase("Febrero") || mes.equalsIgnoreCase("02") || mes.equalsIgnoreCase("2")) {
                mesTrans = 2;
            } else if (mes.equalsIgnoreCase("Marzo") || mes.equalsIgnoreCase("03") || mes.equalsIgnoreCase("3")) {
                mesTrans = 3;
            } else if (mes.equalsIgnoreCase("Abril") || mes.equalsIgnoreCase("04") || mes.equalsIgnoreCase("4")) {
                mesTrans = 4;
            } else if (mes.equalsIgnoreCase("Mayo") || mes.equalsIgnoreCase("05") || mes.equalsIgnoreCase("5")) {
                mesTrans = 5;
            } else if (mes.equalsIgnoreCase("Junio") || mes.equalsIgnoreCase("06") || mes.equalsIgnoreCase("6")) {
                mesTrans = 6;
            } else if (mes.equalsIgnoreCase("Julio") || mes.equalsIgnoreCase("07") || mes.equalsIgnoreCase("7")) {
                mesTrans = 7;
            } else if (mes.equalsIgnoreCase("Agosto") || mes.equalsIgnoreCase("08") || mes.equalsIgnoreCase("8")) {
                mesTrans = 8;
            } else if (mes.equalsIgnoreCase("Septiembre") || mes.equalsIgnoreCase("09") || mes.equalsIgnoreCase("9")) {
                mesTrans = 9;
            } else if (mes.equalsIgnoreCase("Octubre") || mes.equalsIgnoreCase("10")) {
                mesTrans = 10;
            } else if (mes.equalsIgnoreCase("Noviembre") || mes.equalsIgnoreCase("11")) {
                mesTrans = 11;
            } else if (mes.equalsIgnoreCase("Diciembre") || mes.equalsIgnoreCase("12")) {
                mesTrans = 12;
            } else {
                System.out.println("\nEl mes introducido no es correcto.\n");
                ok = false;
            }
        } while (!ok);
        return mesTrans;
    }
}
