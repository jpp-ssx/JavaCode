package com.kaikeba.provider.service;

import com.kaikeba.provider.bean.Depart;

import java.util.List;

public interface DepartService {
    boolean saveDepart(Depart depart);

    boolean deleteDepartById(Integer id);

    boolean modifyDepart(Depart depart);

    Depart getDepartById(Integer id);

    List<Depart> listAllDeparts();
}
