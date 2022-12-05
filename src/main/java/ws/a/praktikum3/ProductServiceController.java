/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.a.praktikum3;

import java.util.HashMap;
import java.util.Map;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */

//Digunakan untuk menentukan layanan web RESTful
@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>(); //menginiasialisasi HashMap untuk menampung data
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    //METHOD DELETE
    //Menentukan Url permintaan untuk mengakses REST endpoint pada method DELETE
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){//mekanisme yang digunakan untuk request menggunakan Path
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);   
    }
    
    //METHOD PUT
    //Menentukan Url permintaan untuk mengakses REST endpoint pada method PUT
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    //Membuat method untuk melakukan perubahan pada data yang ada di productRepo(HashMap)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){//mekanisme yang digunakan untuk request menggunakan Path
        
        //membuat variabel bertipe String untuk dipakai di Conditional Statments If
        String pesan = "Sorry, Product Not Found";
        
        //Membuat Conditional Statments agar data dengan Id yang tidak terdapat dalam productRepo tidak dapat diubah
        //Kondisi jika Data yang akan diubah tidak terdapat dalam productRepo
        if (!productRepo.containsKey(id)){
            //menampilkan pesan apabila data yang akan diubah tidak terdapat dalam productRepo
            return new ResponseEntity<>(pesan, HttpStatus.NOT_FOUND);
        }
        //Kondisi jika Data yang akan diubah terdapat dalam productRepo
        else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
        }        
    }
    
    //METHOD POST
    //Menentukan Url permintaan untuk mengakses REST endpoint pada method POST
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    //Membuat method untuk menambahkan produk atau data pada productRepo(HashMap)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        
        //membuat variabel bertipe String untuk dipakai di Conditional Statments If
        String pesan = "Sorry, Please input the different Id, because the Id is Already exists";
        
        //Membuat Conditional Statement agar data dengan Id yang sama tidak dapat dimasukan
        //Kondisi jika user menginput data dengan Id yang sama
        if (productRepo.containsKey(product.getId()))
        {
            //Menampilkan pesan jika user input data dengan Id yang sama
            return new ResponseEntity<>(pesan, HttpStatus.NOT_FOUND);
        }
        //Kondisi jika user menginput data dengan Id yang belum ada
        else{
            productRepo.put(product.getId(), product);
            //Menampilkan pesan jika user input data dengan Id yang belum ada
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED); 
        }   
    }
    
    //METHOD GET
    //Menentukan Url permintaan untuk mengakses REST endpoint pada method GET
    @RequestMapping(value = "/products")
    //Membuat method untuk menampilkan produk yang telah di input atau ada di HashMap (productRepo)
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
