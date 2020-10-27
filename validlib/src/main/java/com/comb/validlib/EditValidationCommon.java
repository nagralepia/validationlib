package com.comb.validlib;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditValidationCommon {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "[0-9]+";

    // Error Messages
    private static final String REQUIRED_MSG = "Required Field";
    private static final String EMAIL_MSG = "Invalid email address";
    private static final String PHONE_MSG = "Allow Only Numbers";
    private static final String PHONE_VALIDATION = "Valid Mobile Number Required";

    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(editText)) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.requestFocus();
            editText.setError(errMsg);
            return false;
        }


        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.requestFocus();
            editText.setError(REQUIRED_MSG);

            return false;
        }

        return true;
    }

    public static boolean hasAmount(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);
        try {
            int val = Integer.parseInt(text);

            if (val < 10) {
                editText.requestFocus();
                editText.setError("Minimum Biding amount is 10");
                return false;
            }
        } catch (NumberFormatException e) {
            editText.setError("not a number");
        }
        // length 0 means there is no text


        return true;
    }

    public static  boolean isValidEmaillId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static boolean hasTextGmail(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.requestFocus();
            editText.setError(REQUIRED_MSG);
            return false;
        } else if (text.contains("@gmail") || text.contains("@gmail.com")) {
            editText.setError("Login Name should not contain EmailId");
            return false;
        }

        return true;
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean hasMinimunText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() <= 5) {
            editText.requestFocus();
            editText.setError("User Name should be atlesat 6 characters");
            return false;
        }

        return true;
    }

    public static boolean isValidPassword(EditText editText) {
        String text = editText.getText().toString().trim();
        //editText.setError(null);
        if (text != null && text.length() >= 6) {
            return true;

        }
        editText.requestFocus();
        editText.setError("Password Should be atlest 6 Digit/Characters");
        //editText.setError("Password Should be atlest 6 charcters");
        return false;
    }


    public static boolean validPasswordFormat(EditText editText) {
        String text = editText.getText().toString().trim();
        //editText.setError(null);
        if (text != null && text.length() >= 6) {
            Pattern pattern;
            Matcher matcher;

            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z]).{4,}$";
// final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(editText.getText().toString().trim());

            return matcher.matches();

        }
        editText.requestFocus();
        editText.setError("Password Should be atlest 6 Digit/Characters");
        //editText.setError("Password Should be atlest 6 charcters");
        return false;
    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z]).{4,}$";
// final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


    public static boolean mobileValidation(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);
        // length 0 means there is no text
        if (text.length() != 10) {

            editText.requestFocus();
            editText.setError(PHONE_VALIDATION);
            return false;
        }

        return true;
    }
}
