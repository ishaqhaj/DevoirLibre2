package ma.ensa.javaProject.Utils;

import ma.ensa.javaProject.Module.Costmer;
import ma.ensa.javaProject.Module.Order;

public class Utils {
    public static java.sql.Date convertDate(java.util.Date javaDate){ return new java.sql.Date(javaDate.getTime()); }


    // fonction de verifiction du costmer
    public static boolean isAlpha(String word) {
        // Vérifie si le mot n'est pas null et contient uniquement des lettres
        return word != null && word.matches("[a-zA-Z]+");
    }
    public static boolean isPhoneNumber(String PhoneNumber){
        String regex = "^\\+[0-9]{10,15}$";
        return PhoneNumber.matches(regex);
    }

    public static boolean isValideEmail(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public static boolean isValideCostumer(Costmer costmer){
        if (costmer.getId() <= 0){
            return false;
        }
        if (isAlpha(costmer.getNom()) == false){
            return false;
        }
        if (isPhoneNumber(costmer.getPhone()) == false){
            return false;
        }
        if (isValideEmail(costmer.getEmail())){
            return false;
        }
        return true;
    }

    //fonction de verification du order
    public static boolean isValidOrder(Order order){
        if (order.getId() <= 0 ){
            return false;
        }

        if (order.getDate() == null){
            return false;
        }

        if (order.getAmount() <= 0) {
            return false;
        }
        if (order.getCostmerId() <= 0){
            return false;
        }
        return true;
    }

}
