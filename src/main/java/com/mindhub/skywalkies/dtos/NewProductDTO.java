package com.mindhub.skywalkies.dtos;

import com.mindhub.skywalkies.models.Product;
import com.mindhub.skywalkies.models.ShoeColors;

import java.util.List;

public class NewProductDTO {
  private  String name;
  private List<ShoeColors> shoeColors;
  private  String type;
  private  boolean active;

  private  List<Integer> sizes;
  private  int stock;
  private  double price;

  public NewProductDTO() {
  }

  public NewProductDTO(Product product) {
    this.name = product.getName();
    this.active = product.isActive();
    this.sizes = product.getSize();
    this.stock = product.getStock();
    this.type = product.getType();
    this.price = product.getPrice();
  }

  public String getName() {
    return name;
  }

  public List<ShoeColors> getShoeColors() {
    return shoeColors;
  }

  public String getType() {
    return type;
  }

  public Boolean getActive() {
    return active;
  }

  public List<Integer> getSizes() {
    return sizes;
  }

  public int getStock() {
    return stock;
  }

  public double getPrice() {
    return price;
  }
}
