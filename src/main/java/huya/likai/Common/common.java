package huya.likai.Common;

import huya.likai.Param.logResponses;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class common {


    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.ENGLISH);
    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //11/Dec/2015:11:24:49 +0000
    public static logResponses logResponses = new logResponses();

}
