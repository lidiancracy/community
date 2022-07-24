package com.lidian.community.service;



import com.lidian.community.DTO.questiondto;
import com.lidian.community.mapper.questionMapper;
import com.lidian.community.mapper.usermapper;
import com.lidian.community.model.question;
import com.lidian.community.model.user;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName questionservice
 * @Description 将两张表连动起来
 * @Date 2022/7/24 13:20
 */
@Service
public class questionservice {
    @Autowired
    questionMapper questionMapper;
    @Autowired
    usermapper usermapper;
    public List<questiondto> getquestions() {
//        获取quesiton数据，封装进入questiondto
        List<question> getquestions = questionMapper.getquestions();
        ArrayList<questiondto> questiondtos = new ArrayList<>();
        for (question getquestion : getquestions) {
            Integer creator = getquestion.getCreator();
            user getuserbyid = usermapper.getuserbyid(creator);
            questiondto questiondto = new questiondto();
            BeanUtils.copyProperties(getquestion,questiondto);
//            时间格式化
            questiondto.setUser(getuserbyid);
            questiondtos.add(questiondto);
        }
//
        System.out.println(questiondtos.size());
        return questiondtos;
    }

}
