package com.example.actuatorservice;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class GetSetController {

    private final String url = "jdbc:mysql://localhost:3306/lab_expenses";
    private final String username = "root";
    private final String password = "9999";

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus check()
    {
        return HttpStatus.OK;

    }

    @GetMapping()
    public ArrayList<Data> gets(@RequestParam(value = "dateStart", defaultValue = "0000.00.00") String dateStart,
                                @RequestParam(value = "dateEnd", defaultValue = "9999.12.30") String dateEnd) {

        Data dt;
        ArrayList<Data> dtSql = new ArrayList<Data>();

        Date checkStart, checkEnd;

        try {
            checkEnd = new SimpleDateFormat("yyyy.MM.dd").parse(dateEnd);
            checkStart = new SimpleDateFormat("yyyy.MM.dd").parse(dateStart);

            if (checkEnd.getTime() >= checkStart.getTime())
            {

                try (Connection connection = DriverManager.getConnection(url, username, password)) {

                    Statement stmtObj = connection.createStatement();

                    ResultSet resObj = stmtObj.executeQuery("SELECT  expenses.id, costname.name, expenses.price, expenses.datePurchase, expenses.amount, " +
                                                                "expenses.dateTimeCreate, expenses.dateTimeInDB " +
                                                                "FROM lab_expenses.expenses " +
                                                                "inner join lab_expenses.costname on costname.id = expenses.idCname " +
                                                                "where expenses.datePurchase >= \""+dateStart+"\" and expenses.datePurchase <= \""+dateEnd+"\"");

                    while (resObj.next()) {
                        dt = new Data(
                                resObj.getInt("id"),
                                resObj.getString("name"),
                                resObj.getFloat("price"),
                                resObj.getString("datePurchase"),
                                resObj.getInt("amount"),
                                resObj.getString("dateTimeCreate"),
                                resObj.getString("dateTimeInDB"));

                        dtSql.add(dt);
                    }

                    stmtObj.close();
                    connection.close();

                } catch (SQLException e) {
                    System.out.println("Connection failed!");
                    e.printStackTrace();
                }

                return dtSql;

            }else
            {
                System.out.println("End date less in start date");
                return null;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date format");
            return null;
        }

    }

    @DeleteMapping()
    public  HttpStatus remove(@RequestParam (value = "id") String id)
    {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement stmtObj = connection.createStatement();

            stmtObj.execute("DELETE FROM lab_expenses.expenses WHERE id = \""+id+"\"");

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public HttpStatus set(@RequestBody ArrayList<Data> data)
    {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement stmtObj = connection.createStatement();
            String st = "";
            ResultSet resObj = stmtObj.executeQuery("SELECT * FROM lab_expenses.costname");
            ResultSet resObj2;

            ArrayList<Data> dtSql = new ArrayList<Data>();
            Data dt = new Data();
            Data d2;


            Date dateTimeInDB;
            String stringTimeInDB;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

            while (resObj.next()) {
                dt.setId(resObj.getInt("id"));
                dt.setName(resObj.getString("name"));
                dtSql.add(dt);

                dt = new Data();
            }

            for (Data d:data) {
                for (int i = 0; i < dtSql.size(); i++) {
                    d2 = dtSql.get(i);

                    if (d2.getName().equals(d.getName())) {

                        dateTimeInDB  = new Date();
                        stringTimeInDB = df.format(dateTimeInDB.getTime());

                        st = "(\"" + d2.getId() + "\",\"" + d.getPrice() + "\",\"" + d.getDate() + "\",\"" + d.getAmount() + "\",\"" + d.getDateTimeCreate() + "\",\"" + stringTimeInDB + "\")";

                    } else if (i + 1 == dtSql.size()) {

                        stmtObj.execute("insert into costname(name) value (\"" + d.getName() + "\")");

                        resObj2 = stmtObj.executeQuery("SELECT id FROM costname where name = \"" + d.getName() + "\"");
                        resObj2.next();

                        dateTimeInDB  = new Date();
                        stringTimeInDB = df.format(dateTimeInDB.getTime());

                        st = "(\"" + resObj2.getInt("id") + "\",\"" + d.getPrice() + "\",\"" + d.getDate() + "\",\"" + d.getAmount() + "\",\"" + d.getDateTimeCreate() + "\",\"" + stringTimeInDB + "\")";
                    }

                    if (!st.equals("")) {

                        stmtObj.execute("insert into expenses(idCname, price, datePurchase, amount, dateTimeCreate, dateTimeInDB) value " + st);
                        st = "";
                        break;
                    }
                }
            }
            stmtObj.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

}
