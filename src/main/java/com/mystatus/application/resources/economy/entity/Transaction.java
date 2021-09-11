package com.mystatus.application.resources.economy.entity;

import com.Acrobot.ChestShop.Events.TransactionEvent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Transaction {
    private String item;
    private int id;
    private int count;
    private Double price;
    private String player;
    private String type;
    private String owner;
}
