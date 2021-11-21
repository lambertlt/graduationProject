package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.Classify;

public interface ClassifyService {
    Object getClassifyById(Long id);

    Object getClassifyAll();

    Object newClassifySave(Classify classify);

    Object deleteClassifyById(Long id);

    Object updateClassify(Classify classify);
}
