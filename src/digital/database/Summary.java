/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.database;

import java.sql.Date;

/**
 *
 * @author Sayanthini
 */
public class Summary {

    static void setSummary(String some_text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String Sumary;
    private String Author;
    private String Keyword;
    private Date Date;

    public String getSumary() {
        return Sumary;
    }

    public void setSummary_text(String summary_text) {
        this.Sumary = Sumary;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        this.Keyword = Keyword;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this. Date = Date;
    }
    
    
}
