package com.fxn.kafkastudy.bean.repository;

import com.fxn.kafkastudy.bean.PersonBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonBean,Integer>, JpaSpecificationExecutor<PersonBean> {


}
