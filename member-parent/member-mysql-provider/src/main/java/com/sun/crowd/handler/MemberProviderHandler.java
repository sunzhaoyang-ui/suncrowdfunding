package com.sun.crowd.handler;

import com.sun.crowd.constant.CrowdConstant;
import com.sun.crowd.entity.po.MemberPO;
import com.sun.crowd.service.api.MemberService;
import com.sun.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberProviderHandler {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO){

        try{

            memberService.saveMember(memberPO);

            return ResultEntity.successWithoutData();

        }catch (Exception e){

            if (e instanceof DuplicateKeyException){
                return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }

            return ResultEntity.failed(e.getMessage());

        }

    }

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){

        try {

            // 1.调用本地Service完成查询
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);

            // 2.如果没有抛异常，就返回成功的结果
            return ResultEntity.successWithData(memberPO);

        } catch (Exception e) {

            e.printStackTrace();

            // 3.如果捕获到异常就返回失败的结果
            return ResultEntity.failed(e.getMessage());

        }
    }

}
