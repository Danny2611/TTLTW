package com.example.finallaptrinhweb.log;

import com.example.finallaptrinhweb.dao.LogDAO;

 public abstract class Log {
     static LogDAO logDAO = new LogDAO();
    public static  void  infor(int userId, String location, String oldData, String newData){
          logDAO.saveLog(userId,location,oldData,newData,"INFOR");
    }
    public  static  void danger(int userId, String location, String oldData, String newData){
         logDAO.saveLog(userId,location,oldData,newData,"DANGER");
    }
     public  static  void alert(int userId, String location, String oldData, String newData){
          logDAO.saveLog(userId,location,oldData,newData,"ALERT");
     }
     public  static  void warning(int userId, String location, String oldData, String newData){
          logDAO.saveLog(userId,location,oldData,newData,"WARNING");
     }
     public  static  void error(int userId, String location, String oldData, String newData){
          logDAO.saveLog(userId,location,oldData,newData,"ERROR");
     }
}
