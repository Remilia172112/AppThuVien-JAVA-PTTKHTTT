package helper;

import java.sql.Date;
import java.util.regex.Pattern;

public class Validation {

    public static Boolean isEmpty(String input) {
        if (input == null) {
            return true;
        }
        return input.equals("");
    }

    public static Boolean isEmpty(Date input) {
        if (input == null) {
            return true;
        }
        return false;
    }

    public static Boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex); // dùng để biên dịch biểu thức chính quy
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean isNumber(String num) {
        boolean result = true;
        if (num == null) return false;
        try {
            long k = Long.parseLong(num);
            if(k < 0) {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
