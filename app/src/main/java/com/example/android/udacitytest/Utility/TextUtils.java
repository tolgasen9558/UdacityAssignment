package com.example.android.udacitytest.Utility;

import android.text.Html;
import android.text.Spanned;



public class TextUtils {

    public static Spanned getStyledString(String string) {
        boolean boldFlag = false;
        boolean italicFlag = false;

        StringBuilder styledStringBuilder = new StringBuilder();
        String appending;
        for (char c : string.toCharArray()) {
            if (c == '*') {
                if (!boldFlag) {
                    appending = "<b>";
                }
                else {
                    appending = "</b>";
                }
                boldFlag = !boldFlag;
            }
            else if (c == '_') {
                if (!italicFlag) {
                    appending = "<i>";
                }
                else {
                    appending = "</i>";
                }
                italicFlag = !italicFlag;
            }
            else {
                appending = "" + c;
            }
            styledStringBuilder.append(appending);
        }

        return Html.fromHtml(styledStringBuilder.toString());
    }

}
