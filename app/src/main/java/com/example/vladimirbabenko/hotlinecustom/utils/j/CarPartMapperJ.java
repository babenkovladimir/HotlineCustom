package com.example.vladimirbabenko.hotlinecustom.utils.j;

import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem;
import com.example.vladimirbabenko.hotlinecustom.entity.CarPart;
import com.example.vladimirbabenko.hotlinecustom.utils.j.BaseMapperJ;

public class CarPartMapperJ implements BaseMapperJ<CarPart, BascketItem> {

  @Override public BascketItem transform(CarPart in) {
    return new BascketItem(in.getId(), in.getName(), in.getPartPrice(), in.getPartPhotoUrl(),1);
  }
}
