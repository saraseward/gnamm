package com.sara.gnamm.helper;

import java.util.Date;

public class MixedCodeExample {

    public static void useDateHelper(){
        DateHelper.display(new Date()); //@JvmOverloads notation lets me use kotlin default values, otherwise this wouldn't compile
        DateHelper.display(new Date(), "MM/dd/yyyy");
    }
}
