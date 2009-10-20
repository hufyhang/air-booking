/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author HANG Feifei
 */
public class signup {

    private String user = null;
    private String password = null;
    private String host = null;
    private String database = null;
    private String url = null;
    private Connection connect = null;
    Statement stmt;

    public signup ( String user, String password, String host, String database ) {
        
		this.user = user;
		this.password = password;
		this.host = host;
		this.database = database;

		this.url = "jdbc:mysql://" + host + "/" + database;

		try {
			Class.forName ( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace ();
		}

		try {
			connect = DriverManager.getConnection ( this.url, this.user, this.password );
			stmt = connect.createStatement ();
		} catch ( SQLException sqlExp ) {
			sqlExp.printStackTrace ();
		}
    }
}
