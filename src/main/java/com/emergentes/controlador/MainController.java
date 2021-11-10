/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.ConexionDB;
import com.emergentes.modelo.Eventos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;

/**
 *
 * @author HP
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        String op;
        ResultSet rs;
        
        ArrayList<Eventos> lista = new ArrayList<Eventos>();
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        if (op.equals("list")) {
            
            String sql = "select * from eventos";
            try {

                ps = conn.prepareStatement(sql);
                rs= ps.executeQuery();
                
                while (rs.next()){
                    Eventos even = new Eventos();
                    even.setId(rs.getInt("id"));
                    even.setTitulo(rs.getString("titulo"));
                    even.setExpositor(rs.getString("expositor"));
                    even.setFecha(rs.getString("fecha"));
                    even.setHoras(rs.getString("horas"));
                    even.setCupos(rs.getString("cupos"));
                    
                    lista.add(even);
                }
                
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        if (op.equals("nuevo")) {
           
            
            Eventos ev = new Eventos();
            request.setAttribute("even", ev);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        
        if (op.equals("eliminar")) {
            // 
            int id = Integer.parseInt(request.getParameter("id"));
            
            try {
                ps = conn.prepareStatement("delete from eventos where id = ?");
                ps.setInt(1, id);
                
                ps.executeUpdate();
                response.sendRedirect("MainController");
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(op.equals("editar")){
            try {
                Eventos even1 = new Eventos();
                int id = Integer.parseInt(request.getParameter("id"));
                
                ps= conn.prepareStatement("select * from eventos where id = ?");
                ps.setInt(1, id);
                rs= ps.executeQuery();
                if(rs.next()){
                    even1.setId(rs.getInt("id"));
                    even1.setTitulo(rs.getString("titulo"));
                    even1.setExpositor(rs.getString("expositor"));
                    even1.setFecha(rs.getString("fecha"));
                    even1.setHoras(rs.getString("horas"));
                    even1.setCupos(rs.getString("cupos"));
                }
                request.setAttribute("even", even1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id= Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String expositor = request.getParameter("expositor");
        String horas = request.getParameter("horas");
        String fecha = request.getParameter("fecha");
        String cupos = request.getParameter("cupos");
        
        
        
       Eventos even = new Eventos();
       even.setId(id);
       even.setTitulo(titulo);
       even.setExpositor(expositor);
       even.setFecha(fecha);
       even.setHoras(horas);
       even.setCupos(cupos);
       
       ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        
        
        if(id == 0){
            try {
                // insertar registro
                String sql = "insert into eventos (titulo,expositor,fecha,horas,cupos) values(?,?,?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1,even.getTitulo());
                ps.setString(2,even.getExpositor());
                ps.setString(3, even.getFecha());
                ps.setString(4, even.getHoras());
                ps.setString(5, even.getCupos());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                // update registro
                String sql1 = "update eventos set titulo=? , expositor=?, fecha=? ,horas=?, cupos=? where id =?";
                ps = conn.prepareStatement(sql1);
                ps.setString(1,even.getTitulo());
                ps.setString(2,even.getExpositor());
                ps.setString(3, even.getFecha());
                ps.setString(4, even.getHoras());
                ps.setString(5, even.getCupos());
                
                ps.setInt(6, even.getId());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("MainController");
    }

}
