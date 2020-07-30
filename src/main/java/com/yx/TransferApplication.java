package com.yx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/7/29
 */

@SpringBootApplication
@EnableTransactionManagement
public class TransferApplication {

public static void main(String[] args){
  SpringApplication.run(TransferApplication.class, args);
}

}
