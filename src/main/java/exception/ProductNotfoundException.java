/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author ASUS
 */
@ControllerAdvice
public class ProductNotfoundException extends RuntimeException {
    private static final long serialVersionUID =1L;
}