/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author djiby
 */
public interface IDao<T> {
    //généricité : Argument dont le type est variable
    public int insert(T objet);
}
