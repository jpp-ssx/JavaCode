package com.kaikeba.provider.service;

import com.kaikeba.provider.bean.Depart;
import com.kaikeba.provider.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    DepartRepository departRepository;

    @Override
    public boolean saveDepart(Depart depart) {
        Depart result = departRepository.save(depart);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDepartById(Integer id) {
        //若db中存在id则删除 否则抛出异常
        if (departRepository.existsById(id)) {
            departRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        Depart result = departRepository.save(depart);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public Depart getDepartById(Integer id) {
        if(departRepository.existsById(id)){
           return departRepository.getOne(id);
        }
        Depart depart=new Depart();
        depart.setName("no this depart");
        return depart;
    }

    @Override
    public List<Depart> listAllDeparts() {
        return departRepository.findAll();
    }
}
