package com.aws.util;


import com.aws.Logger;

/**
 * Created by Gasiak.Y on 11/6/2015.
 */
public class BooleanUtils {

    public enum Notation {

        YES_NO {
            public String trueValue() {
                return "Yes";
            }

            public String falseValue() {
                return "No";
            }
        },
        YES_NO_ABBREVIATED {
            public String trueValue() {
                return "Y";
            }

            public String falseValue() {
                return "N";
            }
        },
        YES_NO_NUMERIC {
            public String trueValue() {
                return "1";
            }

            public String falseValue() {
                return "0";
            }
        };

        public abstract String trueValue();

        public abstract String falseValue();
    }

    public static String getEquivalentString(Notation boolNotation, String bool, String trueEquivalent, String falseEquivalent, String defaultEquivalent) {
        if (boolNotation.trueValue().equals(bool))
            return trueEquivalent;

        if (boolNotation.falseValue().equals(bool))
            return falseEquivalent;

        return defaultEquivalent;
    }

    public static String tryGetEquivalentString(Notation boolNotation, String bool, String trueEquivalent, String falseEquivalent) {
        return getEquivalentString(boolNotation, bool, trueEquivalent, falseEquivalent, null);
    }

    public static boolean getBoolean(String value){
        return getBoolean(value, false);
    }

    /**
     * Gets boolean value.
     * Returns false if the property exists and equals to "false" , "f", "no", "n" or "0" ignoring case.
     * If the property does not exist or its value differs from cases listed above, returns the fallbackValue.
     * @param value to get boolean for.
     * @param fallbackValue default property value.
     * @return true if the property exists and equals to "true" , "t", "yes", "y" or "1" ignoring case.
     */
    public static boolean getBoolean(String value, boolean fallbackValue){
        if(value == null)
            return fallbackValue;
        switch (value.trim().toUpperCase()){
            case "TRUE":
            case "T":
            case "YES":
            case "Y":
            case "1":
                return true;

            case "FALSE":
            case "F":
            case "NO":
            case "N":
            case "0":
                return false;

            default:
                Logger.GENERAL.writeError("Unexpected boolean value: '%s'", value);
                return fallbackValue;
        }
    }

}
