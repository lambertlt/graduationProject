package com.lambert.jpa.mapper;

import com.lambert.jpa.pojo.PersonalColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface PersonalColumnMapper extends JpaRepository<PersonalColumn, Long> {
    @Query(value = "select t from t_personal_column t where t.userId = :userId")
    List<PersonalColumn> findAllByUserId(Long userId);

    @Query(value = "select t from t_personal_column t where t.classifyId = :classifyId")
    List<PersonalColumn> findAllByClassifyId(Long classifyId);

    @Transactional
    @Modifying
    @Query(value = "update t_personal_column u set u.img=:path where u.id=:id")
    Object updateImg(Long id, String path);

    //    select * from t_personal_column as p inner join t_classify as c on p.classify_id=c.id;
    //    select p.*,c.name as classifyName from t_personal_column as p join t_classify as c on p.classify_id=c.id where p.id=5;
    //    @Query(value = "select t1,t2.name as classifyName from t_personal_column t1 left join t_classify t2 on t1.classifyId=t2.id where t1.id=:id")

    // select p.*,m.path as media_path,c.name as classify_name from t_personal_column as p left join t_media as m on m.id=3 left join t_classify as c on p.classify_id=c.id where p.id=5;
    //  需要提供一个 

    @Query(nativeQuery = true, value = "select p.*,c.name as classify_name from t_personal_column as p left join t_classify as c on p.classify_id=c.id where p.id=:id")
//    Object getByPersonalColumnId(Long id);
    Map<String, Object> getByPersonalColumnId(Long id);
}
