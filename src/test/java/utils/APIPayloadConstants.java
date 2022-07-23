package utils;

import org.json.JSONObject;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload="{\n" +
                "  \"emp_firstname\": \"flowerlily\",\n" +
                "  \"emp_lastname\": \"rose\",\n" +
                "  \"emp_middle_name\": \"sunfl\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1997-07-04\",\n" +
                "  \"emp_status\": \"lolo\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }
    public static String creatBodyWithJson(){

        JSONObject obj=new JSONObject();
        obj.put("emp_firstname" , "zerox");
        obj.put("emp_lastname" , "batman");
        obj.put("emp_middle_name" , "sniper");
        obj.put("emp_gender" ,"F");
        obj.put("emp_birthday"  ,"1997-07-03");
        obj.put("emp_status" , "unemployed");
        obj.put("emp_job_title","QA");

        return obj.toString();

    }
    public static String payLoadMoreDynamic(String emp_firstname, String emp_lastname,
                                            String emp_middle_name,
                                            String emp_gender,
                                            String emp_birthday, String emp_status,
                                            String emp_job_title){

        JSONObject obj=new JSONObject();
        obj.put("emp_firstname" ,emp_firstname);
        obj.put("emp_lastname" ,emp_lastname);
        obj.put("emp_middle_name" ,emp_middle_name);
        obj.put("emp_gender" ,emp_gender);
        obj.put("emp_birthday"  ,emp_birthday);
        obj.put("emp_status" ,emp_status);
        obj.put("emp_job_title" ,emp_job_title);

        return obj.toString();

    }
    public static String updateempBody(){

        JSONObject obj=new JSONObject();
        obj.put("employee_id" , "39502A");
        obj.put("emp_firstname" , "snow");
        obj.put("emp_lastname" , "bar");
        obj.put("emp_middle_name" , "lion");
        obj.put("emp_gender" , "M");
        obj.put("emp_birthday"  ,"1997-07-03");
        obj.put("emp_status" , "unemployed");
        obj.put("emp_job_title" , "QA");

        return obj.toString();

    }
}
