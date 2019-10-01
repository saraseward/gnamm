package com.sara.gnamm.helper;

import com.sara.gnamm.extensions.DateHelper;

import java.util.Date;

public class MixedCodeExample {

    public static void useDateHelper(){
        //Kt in name means we are using a static top-level function from a kotlin file
        DateHelper.display(new Date()); //@JvmOverloads notation lets me use kotlin default values, otherwise this wouldn't compile
        DateHelper.display(new Date(), "MM/dd/yyyy");
        //Date().display() // This won't work in Java classes, only KT
        DateHelper.getISORepresentation(new Date()); //KT => randomBirthDate().ISORepresentation
    }
}
