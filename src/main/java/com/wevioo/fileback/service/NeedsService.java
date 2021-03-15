package com.wevioo.fileback.service;

import com.wevioo.fileback.model.Needs;
import com.wevioo.fileback.repository.NeedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeedsService {

    @Autowired
    private NeedsRepository needsRepository;

    public void injectNewNeed (Needs needs){
        this.needsRepository.save(needs);
    }
}
