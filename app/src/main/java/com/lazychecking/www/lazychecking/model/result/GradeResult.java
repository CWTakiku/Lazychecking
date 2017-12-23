package com.lazychecking.www.lazychecking.model.result;

/**
 * Created by cwl on 2017/12/22.
 */

public class GradeResult {
    private String gradestring;




    public String getGradestring() {
        return gradestring;
    }

    public void setGradestring(String gradestring) {
        this.gradestring = gradestring;
    }

    private static GradeResult instance;
    private GradeResult(){}
    public static synchronized GradeResult getInstance(){
        if (instance==null)
            instance=new GradeResult();
        return instance;
    }

}
